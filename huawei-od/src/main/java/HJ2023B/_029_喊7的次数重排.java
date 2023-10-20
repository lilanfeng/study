package HJ2023B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 喊 7 是一个传统的聚会游戏，N 个人围成一圈，按顺时针从 1 到 N 编号。
 * 编号为 1 的人从 1 开始喊数，下一个人喊的数字为上一个人的数字加 1，但是当将要喊
 * 出来的数字是 7 的倍数或者数字本身含有 7 的话，不能把这个数字直接喊出来，而是要喊” 过”。
 * 假定玩这个游戏的 N 个人都没有失误地在正确的时机喊了”过”，当喊到数字 K 时，可 以统计每个人喊”过”的次数。
 * 现给定一个长度为 N 的数组，存储了打乱顺序的每个人喊”过”的次数，请把它还原 成正确的顺序，即数组的第 i 个元素存储编号的人喊”过”的次数。
 * 输入描述
 * 输入为一行，为空格分隔的喊”过”的次数，注意 K 并不提供，K 不超过 200，而数字 的个数即为 N。
 *   输出描述
 *   输出为一行，为顺序正确的喊”过”的次数，也由空格分隔。
 *   用例
 *     输入：0 1 0
 *
 *     输出：1 0 0
 *
 *     说明： 一共只有一次喊过，所以最少数到了7，目前是三个人，所以只有第一个人是7了。
 *
 *     输入：0 0 0 2 1
 *
 *     输出：0 2 0 1 0
 *
 *     说明：一共有三次喊过 所以最多是数到了17
 *
 */
public class _029_喊7的次数重排 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            printOrder(Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
    }

    public static void printOrder(int[] arr){
        int number = arr.length;
        int[] result = new int[number];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += arr[i];
        }
        int currentCount = 0;
        int index = 0;
        while (currentCount < count){
            index++;
            if((index % 7 == 0) || String.valueOf(index).contains("7") ){
                int i = index % number ;
                if(i == 0){
                    i = number -1;
                }else {
                    i = i -1;
                }
               result[i] = result[i] + 1;
               currentCount++;
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
             stringBuffer.append(result[i]).append(" ");
        }
        System.out.println(stringBuffer.substring(0,stringBuffer.length()-1));
    }

}
