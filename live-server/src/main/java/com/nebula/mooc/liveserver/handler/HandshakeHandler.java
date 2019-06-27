/*
 * Copyright 2013-2018 Lilinfeng.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nebula.mooc.liveserver.handler;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.liveserver.service.UserService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Component
@ChannelHandler.Sharable
public class HandshakeHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 请求类型常量
     */
    private static final String WEBSOCKET_UPGRADE = "websocket";
    private static final String WEBSOCKET_HEAD = "Upgrade";

    /*
     * 握手工厂
     */
    private static WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
            null, null, false);

    @Value("${websocket.path}")
    private String path;

    @Autowired
    private UserService userService;

    /**
     * 获取UserInfo
     *
     * @return 如果存在则返回，不存在返回null
     */
    private UserInfo checkLogin(FullHttpRequest req) {
        try {
            if (req.headers() != null) {
                Set<Cookie> cookies = ServerCookieDecoder.LAX.decode(req.headers().get("Cookie"));
                for (Cookie cookie : cookies) {
                    if (Constant.TOKEN.equals(cookie.name())) {
                        if (userService.loginCheck(cookie.value()))
                            return userService.getUserInfo(cookie.value());
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        // 处理WebSocket协议的信息
        if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
        // 处理HTTP协议升级为WebSocket
        else if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 不自动执行
    }

    /*
     * 完整发送完消息后关闭通道
     */
    private void closeFuture(ChannelHandlerContext ctx, Object msg) {
        ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE);
    }

    /*
     * 发送400 Bad Request
     */
    private void sendBadRequest(ChannelHandlerContext ctx) {
        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST);
        ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),
                CharsetUtil.UTF_8);
        res.content().writeBytes(buf);
        buf.release();
        HttpUtil.setContentLength(res, res.content().readableBytes());
        closeFuture(ctx, res);
    }

    private void handleHttpRequest(ChannelHandlerContext ctx,
                                   FullHttpRequest req) {
        // 如果HTTP解码失败，返回HTTP异常
        if (!req.decoderResult().isSuccess()
                || !WEBSOCKET_UPGRADE.equals(req.headers().get(WEBSOCKET_HEAD))
                || !path.equals(req.uri())) {
            sendBadRequest(ctx);
            return;
        }
        WebSocketServerHandshaker webSocketServerHandshaker = wsFactory.newHandshaker(req);
        if (webSocketServerHandshaker != null) {
            webSocketServerHandshaker.handshake(ctx.channel(), req);
            // 设置UserInfo
            ctx.channel().attr(AttributeKey.valueOf(Constant.USERINFO))
                    .set(checkLogin(req));
            // 握手成功才添加到ChannelGroup
            ctx.fireChannelActive();
        } else {
            sendBadRequest(ctx);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx,
                                      WebSocketFrame frame) {
        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            // 将其移出ChannelGroup
            closeFuture(ctx, frame.retain());
        }
        // 判断是否是Ping消息
        else if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
        } else {
            ctx.fireChannelRead(frame.retain());
        }
    }

}
