package already;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 *  输入：
 *  第一行，输入的是隔几个进行合并处理
 *  第二行，存在的数字列表行
 *
 *  输出：
 *    合并后的列表
 *
 */
public class _002_数字列表合并 {

    /**
     * 数字合并处理
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int mergeCount = Integer.parseInt(sc.nextLine());
        int number = Integer.parseInt(sc.nextLine());
        Map<String, List<String>> mapLists = new HashMap<>();
        int maxLen = 0;
        for (int i = 0; i < number; i++) {
            String[] temp = sc.nextLine().split(",");
            List<String> tempList = Arrays.stream(temp).collect(Collectors.toList());
            mapLists.put(String.valueOf(i),tempList);
            maxLen = Math.max(maxLen,temp.length);
        }
        List<String> resultList = megList(mergeCount,number,maxLen,mapLists);
        System.out.println(String.join(",",resultList));

    }

    /**
     * 输入：
     * 3
     * 2
     * 2,5,6,7,9,5,7
     * 1,7,4,3,4
     *
     * 输出：
     *  2,5,6,1,7,4,7,9,5,3,4,7
     *
     *  输入：
     *  3
     *  1
     *  2，5，6，7，9，5，7
     */
    public static List<String> megList(int mergeCount,int number,int maxLen,Map<String,List<String>> mapLists){
        List<String> resultList = new ArrayList<>();
        int index = 0;
        while (index < maxLen){
            for (int i = 0; i < number;i++){
                List<String> tempList = mapLists.get(String.valueOf(i));
                if(tempList.size() >= index) {
                    int tempIndex = index;
                    int size = Math.min(mergeCount,tempList.size() - index);
                    for (int j = 0; j < size; j++) {
                        resultList.add(tempList.get(tempIndex));
                        tempIndex++;
                    }
                }
            }
            index += mergeCount;
        }
        return resultList;
    }
}
