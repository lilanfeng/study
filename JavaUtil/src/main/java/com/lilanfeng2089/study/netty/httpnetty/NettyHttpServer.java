package com.lilanfeng2089.study.netty.httpnetty;

import com.lilanfeng2089.study.netty.chat.NettyChatServer;
import com.lilanfeng2089.study.netty.chat.NettyChatServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class NettyHttpServer {

    //聊天室端口
    private int port;

    public NettyHttpServer(int port){
        this.port = port;
    }

    public void run(){
        //1 创建bossGroup线程组；处理网络事件---连接事件
        EventLoopGroup bossGroup = null;

        //2 创建workGroup线程组：处理网络事件---读写事件 2*处理线程数
        EventLoopGroup workGroup = null;

        try {

            bossGroup = new NioEventLoopGroup(1);
            workGroup = new NioEventLoopGroup();

            //3,创建服务端启动助手
            ServerBootstrap bootstrap = new ServerBootstrap();
            //4. 设置bossGroup线程组和workerGroup线程组
            bootstrap.group(bossGroup,workGroup)
                    //5. 设置服务端通道实现为NIO
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,Boolean.TRUE)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //8. 向pipeline中添加自定义业务处理handler
                            // 添加编解码器
                            socketChannel.pipeline().addLast(new HttpServerCodec());
                            socketChannel.pipeline().addLast(new NettyHttpServerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(port);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("端口绑定成功!");
                    } else {
                        System.out.println("端口绑定失败!");
                    }
                }
            });
            System.out.println("Http聊天室服务端启动成功.");
            //10. 关闭通道(并不是真正意义上关闭,而是监听通道关闭的状态)和关闭连接池
            channelFuture.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyHttpServer(8080).run();
    }
}
