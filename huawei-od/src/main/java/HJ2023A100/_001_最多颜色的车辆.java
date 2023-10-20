package HJ2023A100;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * 题目描述
 * 在一个狭小的路口，每秒只能通过一辆车，假设车辆的颜色只有 3 种，找出 N 秒内经过的最多颜色的车辆数量。
 *
 * 三种颜色编号为0 ，1 ，2
 *
 * 请注意：这里是假设3中颜色，实际考试中不止3中颜色！！！！！！
 *
 * 输入描述
 * 第一行输入的是通过的车辆颜色信息
 *
 * [0,1,1,2] 代表4 秒钟通过的车辆颜色分别是 0 , 1 , 1 , 2
 *
 * 第二行输入的是统计时间窗，整型，单位为秒
 *
 * 输出描述
 * 输出指定时间窗内经过的最多颜色的车辆数量。
 *
 * 用例
 * 输入	0 1 2 1
 * 3
 * 输出	2
 * 说明	在 3 秒时间窗内，每个颜色最多出现 2 次。例如：[1,2,1]
 * 输入	0 1 2 1
 * 2
 * 输出	1
 * 说明	在 2 秒时间窗内，每个颜色最多出现1 次。
 * 代码思路
 * 这道题目可以使用滑动窗口的思想来解决。首先读入车辆颜色信息，然后读入时间窗大小。接着，我们可以维护一个时间窗，从左到右滑动，统计每种颜色的数量，并记录最多颜色的车辆数量。具体来说，我们可以使用一个哈希表来存储每种颜色的数量，然后在滑动时间窗的过程中，每次新增一个车辆颜色，同时移除一个车辆颜色，更新哈希表中每种颜色的数量，最后记录最多颜色的车辆数量即可。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129306664
 */
public class _001_最多颜色的车辆 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int seconds = sc.nextInt();
        findMaxColor(seconds,str);
        findMaxColor1(seconds,str);
    }

    /**
     *
     * @param number
     * @param str
     */
    public static void findMaxColor(int number,String str){
        if(str.isEmpty()){
            System.out.println(0);
        }
        String[] colors = str.split(" ");
        Queue<String> colorQueue = new ArrayDeque<>();
        Map<String,Integer> colorCountMap = new HashMap();
        int max = 0;
        for (int i = 0; i < colors.length; i++) {
            String key = colors[i];

            if(colorQueue.size() < number){
                colorQueue.add(key);
                colorCountMap.put(key,colorCountMap.getOrDefault(key,0)+1);
                max = Math.max(max,Collections.max(colorCountMap.values()));
            }else {
                String oldKey = colorQueue.peek();
                if(!key.equals(oldKey)){
                    colorQueue.poll();
                    colorQueue.add(oldKey);
                    colorCountMap.put(oldKey,colorCountMap.getOrDefault(oldKey,0) - 1);
                    colorCountMap.put(key,colorCountMap.getOrDefault(key,0) + 1);
                    max = Math.max(max,Collections.max(colorCountMap.values()));
                }
            }
        }

        System.out.println(max);
    }

    public static void findMaxColor1(int number,String str){
        if(str.isEmpty()){
            System.out.println(0);
        }
        String[] colors = str.split(" ");
        Map<String,Integer> colorCountMap = new HashMap();
        int max = 0;
        for (int i = 0; i < colors.length; i++) {
            String key = colors[i];
            int oldIndex = i -number+1;
            colorCountMap.put(key,colorCountMap.getOrDefault(key,0)+1);
            if(oldIndex  >= 0){
                String oldKey = colors[oldIndex];
                if(colorCountMap.containsKey(oldKey)){
                    colorCountMap.put(oldKey,colorCountMap.getOrDefault(oldKey,0) - 1);
                }
            }
            max = Math.max(max,Collections.max(colorCountMap.values()));
        }

        System.out.println(max);
    }



}
