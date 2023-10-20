package com.lilanfeng2089.study.netty.coder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 服务端实现步骤:
 * 1. 创建bossGroup线程组: 处理网络事件--连接事件
 * 2. 创建workerGroup线程组: 处理网络事件--读写事件 3. 创建服务端启动助手
 * 4. 设置bossGroup线程组和workerGroup线程组
 * 5. 设置服务端通道实现为NIO
 * 6. 参数设置
 * 7. 创建一个通道初始化对象
 * 8. 向pipeline中添加自定义业务处理handler
 * 9. 启动服务端并绑定端口,同时将异步改为同步
 * 10. 关闭通道和关闭连接池
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        //1. 创建bossGroup线程组: 处理网络事件--连接事件 线程数默认为：2*处理器线程数
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //2. 创建workerGroup线程组: 处理网络事件--读写事件
        EventLoopGroup workGroup = new NioEventLoopGroup();
        // 3. 创建服务端启动助手
        ServerBootstrap serverBootstrap = new ServerBootstrap();


        //4. 设置bossGroup线程组和workerGroup线程组
        serverBootstrap.group(bossGroup,workGroup)
                //5. 设置服务端通道实现为NIO
                .channel(NioServerSocketChannel.class)
                //6. 参数设置-设置线程队列中等待连接个数
                .option(ChannelOption.SO_BACKLOG,128)
                //7, 参数设置-设置活跃状态,child是设置workerGroup
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                //8. 创建一个通道初始化对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        //添加解码器
                        socketChannel.pipeline().addLast(new MessageDecoder());
                        //添加编码器
                        socketChannel.pipeline().addLast(new MessageEncoder());

                        //9. 向pipeline中添加自定义业务处理handler
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                    }
                });
        //10. 启动服务端并绑定端口,异步方式处理
        ChannelFuture channelFuture = serverBootstrap.bind(9999);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {

                if(channelFuture.isSuccess()){
                    System.out.println("端口绑定成功");
                }else {
                    System.out.println("端口绑定失败");
                }
            }
        });

        System.out.println("服务器启动成功......");
        //11. 关闭通道和关闭连接池
        channelFuture.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

}
