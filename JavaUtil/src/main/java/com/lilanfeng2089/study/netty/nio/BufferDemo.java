package com.lilanfeng2089.study.netty.nio;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class BufferDemo {

    public static void main(String[] args) {
        createBufferDemo();
        putBufferDemo();
        getBufferDemo();
    }

    public static void createBufferDemo(){
        //1,创建一个指定长度的缓冲区，以ByteBuffer为例
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuffer.get());
        }

        //在此继续调用读取数据会报错，BufferUnderflowException
        //System.out.println(byteBuffer.get());


        //2,创建一个有内容的缓冲区

        ByteBuffer wrap = ByteBuffer.wrap("boss".getBytes(StandardCharsets.UTF_8));

        for (int i = 0; i < 4; i++) {
            System.out.println(wrap.get());
        }

    }


    public static void putBufferDemo(){
        //1.创建一个指定长度的缓冲区, 以ByteBuffer为例
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.position());//0 获取当前索引所在位置
        System.out.println(byteBuffer.limit());//10 最多能操作到哪个索引
        System.out.println(byteBuffer.capacity());//10 返回缓冲区总长度
        System.out.println(byteBuffer.remaining());//10 还有多少个能操作
        //修改当前索引位置
        //byteBuffer.position(1);
        //修改最多能操作到哪个索引位置
        //byteBuffer.limit(9);
        // System.out.println(byteBuffer.position());//1 获取当前索引所在位置
        // System.out.println(byteBuffer.limit());//9 最多能操作到哪个索引
        // System.out.println(byteBuffer.capacity());//10 返回缓冲区总长度
        //System.out.println(byteBuffer.remaining());//8 还有多少个能操作

        //添加一个字节
        byteBuffer.put((byte)97);
        System.out.println(byteBuffer.position());//1 获取当前索引所在位置
        System.out.println(byteBuffer.limit());//10 最多能操作到哪个索引
        System.out.println(byteBuffer.capacity());//10 返回缓冲区总长度
        System.out.println(byteBuffer.remaining());//9 还有多少个能操作

        //添加一个字节数组
        byteBuffer.put("abc".getBytes(StandardCharsets.UTF_8));
        System.out.println(byteBuffer.position());//4 获取当前索引所在位置
        System.out.println(byteBuffer.limit());//10 最多能操作到哪个索引
        System.out.println(byteBuffer.capacity());//10 返回缓冲区总长度
        System.out.println(byteBuffer.remaining());//6 还有多少个能操作

        //当添加超过缓冲区的长度时会报错
        byteBuffer.put("012345".getBytes(StandardCharsets.UTF_8));
        System.out.println(byteBuffer.position());//10 获取当前索引所在位置
        System.out.println(byteBuffer.limit());//10 最多能操作到哪个索引
        System.out.println(byteBuffer.capacity());//10 返回缓冲区总长度
        System.out.println(byteBuffer.remaining());//0 还有多少个能操作
        System.out.println(byteBuffer.hasRemaining()); //false 是否还有能操作的数组

        // 如果缓存区存满后, 可以调整position位置可以重复写,这样会覆盖之前存入索引的对应的值
        byteBuffer.position(0);
        byteBuffer.put("012345".getBytes(StandardCharsets.UTF_8));
    }

    public static void getBufferDemo(){
        //创建一个指定长度的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.put("0123".getBytes(StandardCharsets.UTF_8));

        System.out.println("position:" + allocate.position());//4
        System.out.println("limit:" + allocate.limit());//10
        System.out.println("capacity:" + allocate.capacity());//10
        System.out.println("remaining:" + allocate.remaining());//6

        //切换读模式
        System.out.println("读取数据----------");
        allocate.flip();
        System.out.println("position:" + allocate.position());//4
        System.out.println("limit:" + allocate.limit());//10
        System.out.println("capacity:" + allocate.capacity());//10
        System.out.println("remaining:" + allocate.remaining());//6

        for (int i = 0; i < allocate.limit(); i++) {
            System.out.println(allocate.get());
        }

        //读取完毕后继续读取会报错，超过limit值的限制
        //System.out.println(allocate.get());
        //读取指定索引字节
        System.out.println("读取指定索引字节-------");
        System.out.println(allocate.get(1));

        System.out.println("读取多个字节--------");
        allocate.rewind(); //重复读取设置 position重置到读的开始位置
        byte[] bytes = new byte[4];
        allocate.get(bytes);
        System.out.println(new String(bytes));

        //将缓冲区转化字节数组返回
        System.out.println("将缓冲区转化字节数组返回-------");
        byte[] arrays = allocate.array();
        System.out.println(new String(arrays));

        // 切换写模式,覆盖之前索引所在位置的值
        System.out.println("写模式--------------");
        allocate.clear();
        allocate.put("abc".getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(allocate.array()));

    }
}


