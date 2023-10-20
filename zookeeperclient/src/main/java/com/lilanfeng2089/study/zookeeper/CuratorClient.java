package com.lilanfeng2089.study.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.common.StringUtils;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class CuratorClient {

    public static void main(String[] args) throws Exception {

        CuratorFramework client1 = createSessionClient(false);
        String path = "/lg-curator";
        String childrenPath = "/lg-curator/c1";
        createNode(client1,childrenPath);

        deleteNode(client1,path);

        getNodeSample(client1,childrenPath);

        setNodeSample(client1,childrenPath);

    }

    public static CuratorFramework createSessionClient(boolean hasBase){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = null;
        if (hasBase) {
            client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                    .sessionTimeoutMs(5000000)
                    .connectionTimeoutMs(3000)
                    .retryPolicy(retryPolicy)
                    .namespace("base")
                    .build();
            client.start();
            System.out.println("Zookeeper session base established.");
        } else {
            client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", 50000000, 3000, retryPolicy);
            client.start();
            System.out.println("Zookeeper session1 established.");
        }

        return client;

    }

    /**
     *
     * @param client
     * @param path
     */
    public static void createNode(CuratorFramework client,String path) throws Exception {
        if(client == null || StringUtils.isEmpty(path)){
            return;
        }

        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path,"init".getBytes(StandardCharsets.UTF_8));

        Thread.sleep(1000);
        System.out.println("success Create znode:"+path);
    }

    /**
     *
     * @param client
     * @param path  /lg-curator
     */
    public static void deleteNode(CuratorFramework client,String path) throws Exception {
        if(client == null || StringUtils.isEmpty(path)){
            return;
        }

        client.delete().deletingChildrenIfNeeded().withVersion(-1).forPath(path);
        System.out.println("success delete znode:" + path);

    }

    public static void getNodeSample(CuratorFramework client,String path) throws Exception {
        if(client == null || StringUtils.isEmpty(path)){
            return;
        }
        client.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath(path,"init".getBytes());

        System.out.println("success create znode:" + path);

        Stat stat = new Stat();
        byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
        System.out.println(new String(bytes));
    }

    public static void setNodeSample(CuratorFramework client,String path) throws Exception {

        if(client == null || StringUtils.isEmpty(path)) {
            return;
        }

        Stat stat = new Stat();
        byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
        System.out.println(new String(bytes));

        int version = client.setData().withVersion(stat.getVersion()).forPath(path).getVersion();
        System.out.println("Success set node for :" + path + ", new version :" + version);

        try {
            client.setData().withVersion(stat.getVersion()).forPath(path).getVersion();
        }catch (KeeperException.BadVersionException e){
            System.out.println(" this set data is failed ,exception:" + e);
        }

    }



    
}
