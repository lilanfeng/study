package com.lilanfeng2089.study.zookeeperclient;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class updateNote implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws InterruptedException, IOException {
        zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new updateNote());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

        try {
            updateNodeSync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }


    }


    private void updateNodeSync() throws InterruptedException, KeeperException {
        /**
         * path:路径
         * * *data:要修改的内容 byte[] version:为-1，表示对最新版本的数据进行修改 zooKeeper.setData(path, data,version);
        */
        String path = "/lg_persistent";
        byte[] data = zooKeeper.getData(path,false,null);
        System.out.println("修改前的值:"+new String(data));

        //修改 stat:状态信息对象 -1:最新版本
        Stat stat = zooKeeper.setData(path,"客户端修改内容".getBytes(StandardCharsets.UTF_8),-1);

        byte[] data2 = zooKeeper.getData(path,false,null);
        System.out.println("修改后的值:"+new String(data2));
    }
}
