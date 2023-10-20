package HJ2023B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 *
 * 张兵和王武是五子棋迷，工作之余经常切磋棋艺。这不，这会儿又下起来了。走了一会儿，轮张兵了，对着一条线思考起来了，这条线上的棋子分布如下：
 *
 * 用数组表示：-101110101-1棋了分布说明：
 * -1代表白子，0代表空位，1代表黑子
 * 数组长度L,满足1<L<40,L为奇数
 * 你得帮他写一个程序，算出最有利的出子位置。最有利定义：
 * 找到一个空位(0),用棋子(1/-1)填充该位置，可以使得当前子的最大连续长度变大
 * 如果存在多个位置，返回最靠近中间的较小的那个坐标
 * 如果不存在可行位置，直接返回-1
 * 连续长度不能超过5个(五字棋约束)
 * 第一行：当前出子颜色
 * 第二行：当前的棋局状态
 * 输出描述
 * 1个整数，表示出子位置的数组下标
 * 输入
 * 1
 * -1 0 1 1 1 0 1 -1 1
 * 输出
 * 5
 * 说明 当前为黑子(1),放置在下标为5的位置，黑子的最大连续长度，可以由3到5
 * 示例2
 * -1
 * -1 0 1 1 1 0 1 0 1 -1 1
 * 1
 * 当前为白子，唯一可以放置的位置下标为1,白子的最大长度，由1变为2
 * 示例3
 * 1
 * 0 0 0 0 1 0 0 0 0 1 0
 * 5
 * 可行的位置很多，5最接近中间的位置坐标
 */
public class _059_五子棋迷 {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        String[] arr = sc.nextLine().split(" ");
        int targetIndex = -1;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if( arr[i].equalsIgnoreCase("0")){
              int countTemp = findLength(arr,target,i);
              if(countTemp > count){
                  count = countTemp;
                  targetIndex = i;
              }
            }
        }

        System.out.println(targetIndex);
    }

    public static int findLength(String[] arr,String target,int index){
        int first = index;
        int last = index;
        int count = 0;
        first--;
        last++;
        boolean isFirst = true;
        boolean isLast = true;
        while (first >= 0 || last < arr.length){
            if(first >= 0 && target.equalsIgnoreCase(arr[first])){
                first--;
            }else {
                isFirst = false;
            }
            if(last < arr.length && target.equalsIgnoreCase(arr[last])){
                last++;
            }else {
                isLast = false;
            }
            if(!isFirst && !isLast){
                break;
            }
        }
        count = last - first;
        return count;
    }


}
