package com.lilanfeng2089.study.zookeeperclient;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import sun.java2d.SurfaceDataProxy;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class CreateSession implements Watcher {
    //countDownLatch这个类使得一个线程等待，主要不让main方法结束
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {

         /**
        *客户端可以通过创建一个zk实例来连接zk服务器
        *new Zookeeper(connectString,sesssionTimeOut,Wather)
          * connectString: 连接地址:IP:端口
          * sesssionTimeOut:会话超时时间:单位毫秒
          * Wather:监听器(当特定事件触发监听时，zk会通过watcher通知到客户端)
        */
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new CreateSession());
        System.out.println(zooKeeper.getState());
        countDownLatch.await();
        System.out.println("===Client Connected to zookeeper === ");

    }
    /**
     *当前类实现了Watcher接口，重写了process方法，
     * 该方法负责处理来自Zookeeper服务端的 watcher通知，
     * 在收到服务端发送过来的SyncConnected事件之后，解除主程序在CountDownLatch上 的等待阻塞，至此，会话创建完毕
   */
    @Override
    public void process(WatchedEvent watchedEvent) {
        //当连接创建了，服务端发送给客户端SyncConnected事件
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }
}
