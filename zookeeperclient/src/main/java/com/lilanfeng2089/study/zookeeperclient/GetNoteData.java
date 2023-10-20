package com.lilanfeng2089.study.zookeeperclient;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class GetNoteData implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private  static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("127.0.0.1:2181",10000,new GetNoteData());
        countDownLatch.await();
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        //子节点列表发生变化时，服务器会发出NodeChildrenChanged通知，但不会把变化情况告 诉给客户端
        // 需要客户端自行获取，且通知是一次性的，需反复注册监听
        if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
            //再次获取节点数据
            try {
                List<String> children = zooKeeper.getChildren(watchedEvent.getPath(),true);
                System.out.println(children);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }

        //当连接创建了，服务端发送给客户端SyncConnected事件
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){

        }
    }
    /**
     * path
     * watch
     * stat
     *
     *  zk.getData(path, watch, stat);
     */
    private static void  getNoteData() throws InterruptedException, KeeperException, UnsupportedEncodingException {
        byte[] data = zooKeeper.getData("/lg_persistent/lg-children",true,null);
        System.out.println(new String(data,"utf-8"));
    }

    private static void getChildrens() throws InterruptedException, KeeperException {
        List<String> children = zooKeeper.getChildren("/lg_persistent",true);
        System.out.println(children);
    }

}
