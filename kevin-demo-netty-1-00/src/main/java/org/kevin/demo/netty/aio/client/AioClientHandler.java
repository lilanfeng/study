package org.kevin.demo.netty.aio.client;

import org.kevin.demo.netty.aio.ChannelAdapter;
import org.kevin.demo.netty.aio.ChannelHandler;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2026/7/9 07:08
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class AioClientHandler extends ChannelAdapter {
    public AioClientHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("链接报告信息：" + ctx.channel().getRemoteAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("客户端收到消息：" + msg);
        ctx.writeAndFlush("客户端已收到：" + msg);

    }
}
