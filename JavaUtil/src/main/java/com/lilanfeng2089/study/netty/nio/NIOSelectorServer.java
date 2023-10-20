package com.lilanfeng2089.study.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 服务端实现步骤:
 * 1. 打开一个服务端通道
 * 2. 绑定对应的端口号
 * 3. 通道默认是阻塞的，需要设置为非阻塞
 * 4. 创建选择器
 * 5. 将服务端通道注册到选择器上,并指定注册监听的事件为OP_ACCEPT
 * 6. 检查选择器是否有事件
 * 7. 获取事件集合
 * 8. 判断事件是否是客户端连接事件SelectionKey.isAcceptable()
 * 9. 得到客户端通道,并将通道注册到选择器上, 并指定监听事件为OP_READ
 * 10. 判断是否是客户端读就绪事件SelectionKey.isReadable() 11. 得到客户端通道,读取数据到缓冲区
 * 12. 给客户端回写数据
 * 13. 从集合中删除对应的事件, 因为防止二次处理.
 */
public class NIOSelectorServer {

    public static void main(String[] args) throws IOException {
        //1. 打开一个服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2. 绑定对应的端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //3. 通道默认是阻塞的，需要设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //4. 创建选择器
        Selector selector = Selector.open();

        //5. 将服务端通道注册到选择器上,并指定注册监听的事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功--------");
        while (true){
            //6. 检查选择器是否有事件
            int select = selector.select(2000);
            if(select == 0){
                continue;
            }
            //7. 获取事件集合
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                //8. 判断事件是否是客户端连接事件SelectionKey.isAcceptable()
                SelectionKey selectionKey = iterator.next();
                //9. 得到客户端通道,并将通道注册到选择器上, 并指定监听事件为OP_READ
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端已经连接-----"+socketChannel);
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }

                //10. 判断是否是客户端读就绪事件SelectionKey.isReadable()
                if(selectionKey.isReadable()){
                    //11. 得到客户端通道,读取数据到缓冲区
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int read = socketChannel.read(readBuffer);

                    if(read > 0){
                        System.out.println("客户端消息:"+ new String(readBuffer.array(),0,read, StandardCharsets.UTF_8));
                        //12. 给客户端回写数据
                        socketChannel.write(ByteBuffer.wrap("没钱".getBytes(StandardCharsets.UTF_8)));
                        socketChannel.close();
                    }
                }

                //13. 从集合中删除对应的事件, 因为防止二次处理.
                iterator.remove();
            }
        }

    }
}
