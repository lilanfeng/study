package com.lilanfeng2089.study.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 实现步骤
 * 1. 打开通道
 * 2. 设置连接IP和端口号
 * 3. 写出数据
 * 4. 读取服务器写回的数据
 * 5. 释放资源
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {

         // 1. 打开通道
        SocketChannel socketChannel = SocketChannel.open();

        // 2. 设置连接IP和端口号
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
        // 3. 写出数据
        socketChannel.write(ByteBuffer.wrap("老板，该还钱啦！".getBytes(StandardCharsets.UTF_8)));
        // 4. 读取服务器写回的数据
        ByteBuffer bytebuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(bytebuffer);
        System.out.println("服务端消息:" + new String(bytebuffer.array(), 0, read,
                StandardCharsets.UTF_8));
        // 5. 释放资源
        socketChannel.close();
    }
}
