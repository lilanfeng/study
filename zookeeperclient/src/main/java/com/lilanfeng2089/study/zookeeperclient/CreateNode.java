package com.lilanfeng2089.study.zookeeperclient;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class CreateNode implements Watcher {
    //countDownLatch这个类使一个线程等待,主要不让main方法结束
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {
        //当连接创建了，服务端发送给客户端SyncConnected事件
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
        countDownLatch.countDown();
        }
        //调用创建节点方法
        try {
        createNodeSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("10.211.55.4:2181", 5000, new CreateNode());
        countDownLatch.await();
    }

/**
 world:anyone
 * * * * *
 path
 data[]
 acl
 :节点创建的路径 :节点创建要保存的数据，是个byte类型的 :节点创建的权限信息(4种类型)
 ANYONE_ID_UNSAFE : 表示任何人
 AUTH_IDS :此ID仅可用于设置ACL。它将被客户机验证的ID替
 OPEN_ACL_UNSAFE :这是一个完全开放的ACL(常用)--> CREATOR_ALL_ACL :此ACL授予创建者身份验证ID的所有权限
 :创建节点的类型(4种类型) PERSISTENT:持久节点 PERSISTENT_SEQUENTIAL:持久顺序节点 EPHEMERAL:临时节点 EPHEMERAL_SEQUENTIAL:临时顺序节点
 */
    private static void createNodeSync() throws UnsupportedEncodingException, InterruptedException, KeeperException {
        String node_PERSISTENT = zooKeeper.create("/lg_persistent", "持久节点内 容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        String node_PERSISTENT_SEQUENTIAL = zooKeeper.create("/lg_persistent_sequential", "持久节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        String node_EPERSISTENT = zooKeeper.create("/lg_ephemeral", "临时节点内 容".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println("创建的持久节点是:"+node_PERSISTENT);
        System.out.println("创建的持久顺序节点是:"+node_PERSISTENT_SEQUENTIAL);
        System.out.println("创建的临时节点是:"+node_EPERSISTENT);

    }


}
