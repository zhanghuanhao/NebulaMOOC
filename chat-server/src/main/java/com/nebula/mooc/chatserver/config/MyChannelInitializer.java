/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.chatserver.config;

import com.nebula.mooc.chatserver.handler.MyWebSocketHandler;
import com.nebula.mooc.core.entity.ChatMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyChannelInitializer<C extends Channel> extends ChannelInitializer<C> {
    @Override
    protected void initChannel(C ch) {
        ch.pipeline()
                // HTTP请求的解码和编码
                .addLast(new HttpServerCodec())
                // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
                // 原因是HTTP解码器会在每个HTTP消息中生成多个消息对象HttpRequest/HttpResponse,HttpContent,LastHttpContent
                .addLast(new HttpObjectAggregator(65536))
                // 主要用于处理大数据流，比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的; 增加之后就不用考虑这个问题了
                .addLast(new ChunkedWriteHandler())
                // protobufDecoder仅仅负责编码，并不支持读半包，所以在之前，一定要有读半包的处理器。
                // 半包的处理
                .addLast(new ProtobufVarint32FrameDecoder())
                // 需要解码的目标类
                .addLast(new ProtobufDecoder(ChatMessage.request.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new MyWebSocketHandler())
                //用于处理websocket, /ws为访问websocket时的uri
                .addLast(new WebSocketServerProtocolHandler("/"))
                .addLast(new MyWebSocketHandler());
    }
}
