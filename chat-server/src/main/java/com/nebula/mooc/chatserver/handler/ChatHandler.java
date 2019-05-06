/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.chatserver.handler;

import com.nebula.mooc.chatserver.core.ChatMessage;
import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.service.UserService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@ChannelHandler.Sharable
public class ChatHandler extends SimpleChannelInboundHandler<ChatMessage.request> {

    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);

    // 存储Channel组
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<Channel, UserInfo> userMap = new ConcurrentHashMap<>();

    @Autowired
    private UserService userService;

    /**
     * 构建群发信息
     *
     * @param msg      内容
     * @param nickName 昵称
     * @param color    颜色的整数表示
     * @return 已构建号的信息
     */
    private ChatMessage.response buildResponse(int code, String msg, int color, String nickName) {
        ChatMessage.response.Builder builder = ChatMessage.response.newBuilder();
        builder.setCode(code);
        builder.setMsg(msg);
        builder.setColor(color);
        builder.setNickname(nickName);
        builder.setTimestamp(System.currentTimeMillis());   //默认为系统当前时间
        return builder.build();
    }

    /**
     * 收到信息时执行
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ChatMessage.request msg) {
        Channel channel = ctx.channel();
        logger.info("Received Message from " + channel.remoteAddress());
        if (msg.getCode() == 1) {
            // 通过ChannelGroup群发信息
            UserInfo userInfo = userMap.get(channel);
            if (userInfo == null) {
                // 用户未登录
                ctx.writeAndFlush(buildResponse(Constant.CLIENT_NOT_LOGIN,
                        "用户未登录！", 0, ""));
                return;
            }
            channelGroup.writeAndFlush(buildResponse(Constant.SUCCESS_CODE,
                    msg.getMsg(), msg.getColor(), userInfo.getNickName()));
        } else if (msg.getCode() == 2) {
            // 登录信息
            String token = msg.getMsg();    // 获取token
            System.out.println("token:" + token);
            if (userService.loginCheck(token)) {
                // 如果已经登录
                UserInfo userInfo = userService.getUserInfo(msg.getMsg());
                userMap.put(channel, userInfo);
            } else {
                ctx.writeAndFlush(buildResponse(Constant.CLIENT_TOKEN_EXCEED,
                        "Token已过期！", 0, ""));
            }
        } else {
            ctx.writeAndFlush(buildResponse(Constant.CLIENT_ERROR_CODE,
                    "不支持的数据类型！", 0, ""));
        }
    }

    /**
     * WebSocket握手成功时被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.add(channel);
        logger.info("Add channel" + channel);
    }

    /**
     * Channel不活跃时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        userMap.remove(channel);
        logger.info("Remove channel" + channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        logger.error(cause.getMessage(), cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
