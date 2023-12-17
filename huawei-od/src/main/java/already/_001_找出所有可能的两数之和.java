package already;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _001_找出所有可能的两数之和 {

    /**
     * 你一个数组，然后数组中任意两个数字相加后得出目标值，输出这两个数字的下标，例如[3,8,4,10] 目标值 7，
     * 因为3+4=7，所以输出下标值是[0,2]
     * 数组里数字可重复，需要输出全部下标值组合。
     * 注意：数组较大，需要复杂性较低的算法，必须低于O(n^2)
     * 例如：
     *    输入：2,2,3,3
     *       5
     *    输出：
     *      0,2
     *      0,3
     *      1,2
     *      1,3
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            int[] arr = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            int target = Integer.parseInt(sc.nextLine());
            HashSet<List<Integer>> targetMap = twoSumIndex(target,arr);
            //打印找到的结果处理
            if(targetMap.size()> 0){
                Iterator<List<Integer>> iterator = targetMap.iterator();
                while (iterator.hasNext()){
                    List<Integer> list = iterator.next();
                    System.out.println(list);
                }
            }
        }
    }

    /**
     * 可以借助Map来寻找
     * @param target
     * @param arr
     */
    public static HashSet<List<Integer>> twoSumIndex(int target,int[] arr){
        Map<Integer, List<Integer>> arrMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = null;
            if(arrMap.containsKey(arr[i])){
                list = arrMap.get(arr[i]);
                list.add(i);
                arrMap.put(arr[i],list);
            }else {
                list = new ArrayList<>();
                list.add(i);
                arrMap.put(arr[i],list);
            }
        }
        HashSet<List<Integer>> targetMap = new HashSet<>();
        //遍历寻找数据
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int result = target - temp;
            if(arrMap.containsKey(result)){
                //找到存在的两数和
                List<Integer> list = arrMap.get(result);
                for (int j = 0; j < list.size(); j++) {
                    if(!list.get(j).equals(i)){
                        targetMap.add(new ArrayList<>(Arrays.asList(Math.min(i,list.get(j)),Math.max(i,list.get(j)))));
                    }
                }
            }
        }
        return targetMap;
    }

    /**
     * @param target
     * @param arr
     * @return
     */
    public static HashSet<List<Integer>> twoSumIndex1(int target,int[] arr){
        HashSet<List<Integer>> resultSet = new HashSet<>();

        return resultSet;
    }

}
