package com.lilanfeng2089.study.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ArrayListTest {

    public static void main(String[] args) {
        remove20To50();
    }


    /**
     *  一个List中有100个元素，删除20-50的元素
     */
    public static void remove20To50(){

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        int number = 1;
        while(iterator.hasNext()){
            iterator.next();
            if(number >= 20 && number <= 50){
             iterator.remove();
            }
            number++;
        }
        System.out.println(list);

        List<Integer> tempList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            tempList.add(i);
        }

        for (int i = 49; i >= 19; i--) {
            tempList.remove(i);
        }
        System.out.println(tempList);

        List<Integer> tempList1 = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            tempList1.add(i);
        }

        for (int i = 0; i <= 30; i++) {
            tempList1.remove(19);
        }
        System.out.println(tempList1);

        List<Integer> tempList2 = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            tempList2.add(i);
        }
        tempList2.stream().filter(index->{
            if(index >= 20 &&index <= 50){
            return false;
        } return true;}).collect(Collectors.toList());

    }
}
