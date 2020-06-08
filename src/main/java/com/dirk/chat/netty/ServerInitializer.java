package com.dirk.chat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Dirk
 * @date 2020-06-07 11:45
 * @description 初始化器
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 获取管道
        ChannelPipeline pipeline = ch.pipeline();

        /****************基于http协议***************/
        // http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 支持大数据流
        pipeline.addLast(new ChunkedWriteHandler());
        // 对HttpMessage进行聚合
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        /**
         * websocket服务器处理的协议，指定路由为"/ws"
         * 处理握手动作：handshaking（close,ping,pong） ping+pong=心跳
         * 以frames数据类型进行传输
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 自定义handler
        pipeline.addLast(new ChatHandler());

    }
}
