/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.liveserver.handler;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.liveserver.core.ChatMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@ChannelHandler.Sharable
public class ChatHandler extends SimpleChannelInboundHandler<ChatMessage.request> {

    // 存储Channel组
    private final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final ConcurrentMap<Channel, UserInfo> userMap = new ConcurrentHashMap<>();

    private static final Whitelist whitelist = Whitelist.none().preserveRelativeLinks(true);

    /**
     * 清除XSS信息
     */
    private static String cleanMsg(String content) {
        return Jsoup.clean(content, whitelist);
    }

    /**
     * 构建群发信息
     *
     * @param msg      内容
     * @param nickName 昵称
     * @param color    颜色的整数表示
     * @return 已构建号的信息
     */
    private ChatMessage.response buildResponse(int code, String msg, int color, String nickName, int size) {
        ChatMessage.response.Builder builder = ChatMessage.response.newBuilder();
        builder.setCode(code);
        builder.setMsg(msg);
        builder.setColor(color);
        builder.setNickname(nickName);
        builder.setSize(size);
        return builder.build();
    }

    /**
     * 收到信息时执行
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ChatMessage.request msg) {
        Channel channel = ctx.channel();
        if (msg.getMsg().length() <= 30 && msg.getSize() > 0 && msg.getSize() < 4) {
            // 通过ChannelGroup群发信息
            UserInfo userInfo = userMap.get(channel);
            if (userInfo == null) {
                // 用户未登录
                ctx.writeAndFlush(buildResponse(Constant.CLIENT_NOT_LOGIN,
                        "用户未登录！", 0, "", 0));
            } else {
                String cleanMsg = cleanMsg(msg.getMsg());
                if (cleanMsg.equals(msg.getMsg())) {
                    // 干净的信息
                    channelGroup.writeAndFlush(buildResponse(Constant.SUCCESS_CODE,
                            msg.getMsg(), msg.getColor(), userInfo.getNickName(), msg.getSize()));
                } else {
                    // 含有非法信息
                    ctx.writeAndFlush(buildResponse(Constant.CLIENT_ILLEGAL,
                            "警告：存在非法操作！", 0, "", 0));
                }
            }
        }
    }

    /**
     * WebSocket握手成功时被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        UserInfo userInfo = (UserInfo) channel.attr(AttributeKey.valueOf(Constant.USERINFO)).get();
        if (userInfo != null)
            userMap.put(channel, userInfo);
        channelGroup.add(channel);
    }

    /**
     * Channel不活跃时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        userMap.remove(channel);
    }

}
