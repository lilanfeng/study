package com.lilanfeng2089.study.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class MapLoopCompare {

    public static void main(String[] args){

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            map.put(i,i);
        }

        long time1 = System.currentTimeMillis();
        loopEntrySet(map);

        long time2 = System.currentTimeMillis();
        loopKeySet(map);

        long time3 = System.currentTimeMillis();
        loopIterator(map);

        long time4 = System.currentTimeMillis();
        loopLambda(map);

        long time5 = System.currentTimeMillis();

        System.out.println("loopEntrySet cost time:" + (time2 - time1));

        System.out.println("loopKeySet cost time:" + (time3 - time2));

        System.out.println("loopIterator cost time:" + (time4 - time3));

        System.out.println("loopLambda cost time:" + (time5 - time4));

    }

    /**
     *  1 entrySet遍历，在键和值都需要时候使用（最常用）
     * @param map
     */
    public static void loopEntrySet(Map<Integer,Integer> map){
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            System.out.println("key:"+entry.getKey() + " value:"+ entry.getValue());
        }
    }

    /**
     * 2, 通过keySet和values来遍历
     * @param map
     */
    public static void loopKeySet(Map<Integer,Integer> map){
        for (Integer key:map.keySet()){
            System.out.println("key:"+key + " value:"+ map.get(key));
        }
    }

    /**
     * 3,使用Iterator迭代器遍历
     * @param map
     */
    static void loopIterator(Map<Integer,Integer> map){
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            System.out.println("key:"+next.getKey() + " value:"+ next.getValue());
        }

    }

    /**
     * 4,使用lambda遍历
     * @param map
     */
    static void loopLambda(Map<Integer,Integer> map){
        map.forEach((key,value)->{
            System.out.println("key:"+key + " value:"+ value);
        });

    }


}
