package com.lilanfeng2089.study.netty.coder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 客户端实现步骤:
 * 1. 创建线程组
 * 2. 创建客户端启动助手
 * 3. 设置线程组
 * 4. 设置客户端通道实现为NIO
 * 5. 创建一个通道初始化对象
 * 6. 向pipeline中添加自定义业务处理handler
 * 7. 启动客户端,等待连接服务端,同时将异步改为同步 8. 关闭通道和关闭连接池
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //1. 创建线程组
        EventLoopGroup clientGroup = new NioEventLoopGroup();

        //2. 创建客户端启动助手
        Bootstrap bootstrap = new Bootstrap();

        //3. 设置线程组
        bootstrap.group(clientGroup)
                //4. 设置客户端通道实现为NIO
                .channel(NioSocketChannel.class)
                //5. 创建一个通道初始化对象
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        socketChannel.pipeline().addLast(new MessageCoder());;
                        //6. 向pipeline中添加自定义业务处理handler
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        //7. 启动客户端,等待连接服务端,同时将异步改为同步
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
        // 8. 关闭通道和关闭连接池
        channelFuture.channel().closeFuture().sync();
        clientGroup.shutdownGracefully();
    }
}
