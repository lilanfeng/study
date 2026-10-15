package org.kevin.demo.netty.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2026/7/12 12:05
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class AioServer  extends Thread {

    private AsynchronousServerSocketChannel serverSocketChannel;

    @Override
    public void run(){
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open(AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(),10));
            serverSocketChannel.bind(new InetSocketAddress(7397));

            System.out.println("服务器启动，等待连接...");
            CountDownLatch latch = new CountDownLatch(1);
            serverSocketChannel.accept(this, new AioServerChannelInitializer());
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public AsynchronousServerSocketChannel serverSocketChannel(){
        return serverSocketChannel;
    }

    public static void main(String[] args) {
        new AioServer().start();
    }
}
