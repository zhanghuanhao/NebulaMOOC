/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.chatserver.handler;

import com.nebula.mooc.core.entity.ChatMessage;
import com.nebula.mooc.core.entity.UserInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ChannelHandler.Sharable
public class ChatHandler extends SimpleChannelInboundHandler<ChatMessage.request> {

    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);

    // 存储Channel组
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<Channel, UserInfo> UserMap = new ConcurrentHashMap<>();

    /**
     * 构建群发信息
     *
     * @param content  内容
     * @param nickName 昵称
     * @param color    颜色的整数表示
     * @return 已构建号的信息
     */
    private ChatMessage.response buildResponse(String content, String nickName, int color) {
        ChatMessage.response.Builder builder = ChatMessage.response.newBuilder();
        builder.setTimestamp(System.currentTimeMillis());   //默认为系统当前时间
        builder.setColor(color);
        builder.setNickname(nickName);
        builder.setContent(content);
        return builder.build();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, ChatMessage.request msg) {
        Channel channel = ctx.channel();
        logger.info("Received Message from " + channel.remoteAddress());
        // 通过ChannelGroup群发信息
        channelGroup.writeAndFlush(buildResponse(msg.getContent(), "nickname", msg.getColor()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.add(channel);
        logger.info("Add channel" + channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        logger.info("Remove channel" + channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.channel().close();
        logger.error(cause.getMessage(), cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * 主要用于处理空闲状态的Channel
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            ctx.channel().close();
        }
    }
}
