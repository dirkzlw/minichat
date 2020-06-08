package com.dirk.chat.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author Dirk
 * @date 2020-06-07 11:50
 * @description 自定义助手类
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 客户端组
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 客户端发送的消息
        String text = msg.text();
        System.err.println(ctx.channel().remoteAddress() + " 发送[" + text + "]");
        channelGroup.writeAndFlush(new TextWebSocketFrame(text));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 将客户端加入客户端组
        System.err.println(ctx.channel().remoteAddress() + " 连接成功...");
        channelGroup.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 端口连接打印信息
        System.err.println(ctx.channel().remoteAddress() + " 断开连接...");
    }
}
