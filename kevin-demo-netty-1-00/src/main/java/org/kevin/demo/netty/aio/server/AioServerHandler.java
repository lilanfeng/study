package org.kevin.demo.netty.aio.server;

import org.kevin.demo.netty.aio.ChannelAdapter;
import org.kevin.demo.netty.aio.ChannelHandler;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2026/7/12 15:10
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class AioServerHandler extends ChannelAdapter {

    public AioServerHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("链接报告信息：" + ctx.channel().getRemoteAddress());
            System.out.println("通知服务器链接建立成功：" + " " + new Date() + "" + ctx.channel().getRemoteAddress() + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void channelInactive(ChannelHandler ctx) {
        try {
            System.out.println("客户端断开：" + ctx.channel().getRemoteAddress() + " " + new Date() + "\r\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("服务端收到消息：" + msg + " " + new Date() + "\r\n");
        ctx.writeAndFlush("服务器已收到：" + msg + " " + new Date() + "\r\n");
    }
}
