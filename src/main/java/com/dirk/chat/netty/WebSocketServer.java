package com.dirk.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @author Dirk
 * @date 2020-06-07 11:22
 * @description
 */
@Component
public class WebSocketServer {

    private static class SingletionWebSocketServer {
        static final WebSocketServer instance = new WebSocketServer();
    }

    public static WebSocketServer getInstance() {
        return SingletionWebSocketServer.instance;
    }

    private EventLoopGroup parentGroup;
    private EventLoopGroup childGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;

    public WebSocketServer() {
        parentGroup = new NioEventLoopGroup();
        childGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer());
    }

    public void start() {
        this.channelFuture = serverBootstrap.bind(8088);
        System.err.println("ServerBootstrap 启动完毕...");
    }
}
