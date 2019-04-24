/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.chatserver.config;

import com.nebula.mooc.chatserver.handler.ByteToFrame;
import com.nebula.mooc.chatserver.handler.ChatHandler;
import com.nebula.mooc.chatserver.handler.FrameToByteHandler;
import com.nebula.mooc.chatserver.handler.IdleHandler;
import com.nebula.mooc.core.ChatMessage;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipeLineInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * Websocket所在路径
     */
    @Value("${websocket.path}")
    private String websocketPath;

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline()
                // HTTP请求的解码和编码
                .addLast(new HttpServerCodec())
                // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
                .addLast(new HttpObjectAggregator(8192))
                //用于处理websocket, 第一个参数为访问websocket时的uri
                .addLast(new WebSocketServerProtocolHandler(websocketPath))
                .addLast(new FrameToByteHandler())
                // 需要解码的目标类
                .addLast(new ProtobufDecoder(ChatMessage.request.getDefaultInstance()))
                .addLast(new ByteToFrame())
                .addLast(new ProtobufEncoder())
                .addLast(new IdleHandler())
                .addLast(new ChatHandler());
    }
}
