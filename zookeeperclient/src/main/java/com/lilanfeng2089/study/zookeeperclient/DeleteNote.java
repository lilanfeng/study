package com.lilanfeng2089.study.zookeeperclient;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DeleteNote implements Watcher {
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new DeleteNote());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            deleteNodeSync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private void deleteNodeSync() throws InterruptedException, KeeperException {

        /**
        * zooKeeper.exists(path,watch) :判断节点是否存在
         * zookeeper.delete(path,version) : 删除节点
        */
        String path = "/lg_persistent/lg-children";

        Stat stat = zooKeeper.exists(path,false);
        System.out.println(stat == null ? "该节点不存在":"该节点存在");

        zooKeeper.delete(path,-1);
        Stat stat1 = zooKeeper.exists(path,false);
        System.out.println(stat1 == null ? "该节点不存在":"该节点存在");


    }

}
