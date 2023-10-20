package com.lilanfeng2089.study.ip2region.util;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description  https://juejin.cn/post/7280118836685668367
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Ip2RegionSearchUtil {

    /**
     * ip2region 数据库地址
     */
    private final static String  dbPath = "/src/main/resources/ip2region.xdb";

    private  static byte[] vIndex ;

    private static String getDbPath(){
        return System.getProperty("user.dir") + dbPath;
    }

    public static String searchRegionByIp(String ip) throws IOException {

        Searcher searcher = null;
        String region = "";
        try{

            searcher = Searcher.newWithFileOnly(getDbPath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to create searcher with '%s' \n");
            return region;
        }
        try {
            long sTime  = System.nanoTime();
            region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() -sTime);

            System.out.printf("region:%s,iocount:%d,took:%d us\n",region,searcher.getIOCount(),cost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        searcher.close();

        return region;
    }

    public static String searchRegionByIpCache(String ip) throws IOException {
        String region = "";

        try {
            vIndex = Searcher.loadVectorIndexFromFile(getDbPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Searcher searcher = null;
        try {
            searcher = Searcher.newWithVectorIndex(getDbPath(),vIndex);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            long sTime  = System.nanoTime();
            region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() -sTime);
            System.out.printf("region:%s,iocount:%d,took:%d us\n",region,searcher.getIOCount(),cost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        searcher.close();
        return region;
    }



}
