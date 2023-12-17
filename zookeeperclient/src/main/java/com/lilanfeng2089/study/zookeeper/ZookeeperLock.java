package com.lilanfeng2089.study.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description
 * @author lilanfeng2089，微信：lilanfeng2089
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ZookeeperLock implements Lock{
    private CuratorFramework client;
    private final String zkPath;
    private Integer count; // 记录获取锁的次数，实现可重入锁
    private final String subNodePathPrefix;
    private String lockedPath;
    private String preNodePath;
    private String subShortPath;
    private Thread thread;

    public ZookeeperLock(CuratorFramework client, String zkPath) throws Exception {
        this.client = client;
        this.zkPath = zkPath;
        subNodePathPrefix = "node-";
        count = 0;
        init();
    }

    private void init() throws Exception {
        synchronized (ZookeeperLock.class) {
            Stat stat;
            stat = client.checkExists().forPath(zkPath);
            if(stat == null)
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(zkPath);
        }
    }



    @Override
    public void lock() {
        if (thread == Thread.currentThread()) { // 实现可重入锁
            count += 1;
            return;
        }
        if(lockInternal()){
            thread = Thread.currentThread();
            count += 1;
        }
    }

    /**
     * 上锁函数
     * */
    private boolean lockInternal() {
        try {
            boolean locked = tryLock(); // 尝试上锁
            if (locked){
                return true;
            }

            // 上锁失败，阻塞等待
            while (!locked) {
                locked = await();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean tryLock() throws Exception {
        // 为此上锁请求建立临时有序节点
        lockedPath = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(zkPath + "/" + subNodePathPrefix);
        if (lockedPath == null){
            throw new Exception();
        }

        // 获取锁节点下面所有的上锁请求节点，判断该上锁请求节点的位置，若为首位则获得锁，否则尝试上锁失败
        subShortPath = getShortPath(lockedPath);
        List<String> waiters = getWaiters();
        if(checkIsHeadNode(waiters))
            return true;
        int index = Collections.binarySearch(waiters, subShortPath);
        if (index < 0)
            throw new Exception();
        preNodePath = zkPath + "/" + waiters.get(index - 1);
        return false;
    }

    private String getShortPath(String path) {
        int index = path.lastIndexOf(zkPath + "/");
        if (index >= 0) {
            index += zkPath.length() + 1;
            return index <= path.length() ? path.substring(index) : "";
        }
        return null;
    }

    private boolean checkIsHeadNode(List<String> waiters) {
        Collections.sort(waiters);
        return subShortPath.equals(waiters.get(0));
    }

    private List<String> getWaiters() throws Exception {
        return client.getChildren().forPath(zkPath);
    }

    private boolean await() throws Exception {
        if (preNodePath == null){
            throw new Exception();
        }

        CountDownLatch latch = new CountDownLatch(1);
        client.getData().usingWatcher((Watcher) watchedEvent -> latch.countDown()).forPath(preNodePath);
        latch.await();
        return true;
    }

    /**
     * 解锁函数
     * */
    @Override
    public void unlock() {
        if(!thread.equals(Thread.currentThread())) {
            return;
        }

        // 检查重入次数
        count -= 1;
        if (count > 0){
            return;
        }
        try {
            client.delete().forPath(lockedPath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
