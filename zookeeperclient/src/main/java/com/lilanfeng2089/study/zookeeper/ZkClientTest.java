package com.lilanfeng2089.study.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.common.StringUtils;

import java.util.List;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ZkClientTest {

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = createSession();
        String path = "/lg-zkClient/lg-cl";
        createNodeSample(zkClient,path);
        delDataSample(zkClient,path);

        String parentPath = "/lg-zkClient";

        getChildrenSample(zkClient,parentPath,path);

        getDataSample(zkClient,"");
    }

    public static ZkClient createSession(){
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        System.out.println("ZooKeeper session established.");
        return zkClient;
    }

    public static void createNodeSample(ZkClient zkClient,String path){
        if(zkClient == null || StringUtils.isEmpty(path)){
            return;
        }
        zkClient.createPersistent(path,true);
        System.out.println("success create persistent ZNode");
    }

    public static void delDataSample(ZkClient zkClient,String path){
        if(zkClient == null || StringUtils.isEmpty(path)){
            return;
        }
        boolean isDelete = zkClient.deleteRecursive(path);
        System.out.println("success delete ZNode : " + isDelete);
    }

    public static void getChildrenSample(ZkClient zkClient,String path,String childrenPath) throws InterruptedException {
        if(zkClient == null || StringUtils.isEmpty(path)){
            return;
        }

        List<String> children = zkClient.getChildren(path);
        System.out.println("children list:"+children);

        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(parentPath + " 's child changed, currentChilds:"+currentChilds);
            }
        });

        if(!zkClient.exists(path)){
            zkClient.createPersistent(path);
            Thread.sleep(1000);
        }
        zkClient.createPersistent(childrenPath);
        Thread.sleep(1000);
        zkClient.delete(childrenPath);
        Thread.sleep(1000);
        zkClient.delete(path);
    }

    public static void getDataSample(ZkClient zkClient,String path) throws InterruptedException {
        if(zkClient == null || StringUtils.isEmpty(path)){
            return;
        }

        boolean exists = zkClient.exists(path);
        if(!exists){
            zkClient.createEphemeral(path,"123");
        }

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println(path+"该节点内容被更新，更新后的内容"+data);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println(s+" 该节点被删除");
            }
        });

        //获取节点内容
        Object o = zkClient.readData(path);
        System.out.println(o);

        //更新
        zkClient.writeData(path,"4567");
        Thread.sleep(1000);

        //删除
        zkClient.delete(path);
        Thread.sleep(1000);

    }


}
