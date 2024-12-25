package 链表;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * https://leetcode.cn/problems/happy-number/
 *  编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * 示例 1：
 *
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 1*1 + 9*9 = 82
 * 8*8 + 2*2 = 68
 * 6*6 + 8*8 = 100
 * 1*1 + 0*0 + 0*0 = 1
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：false
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 */
public class _202_快乐数 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            System.out.println(isHappy2(Integer.parseInt(sc.nextLine())));
        }

    }

    /**
     * 快慢指针 判断是否是快乐数
     * @param n
     * @return
     */
    public static boolean isHappy(int n){
        int slow = getNext(n);
        int fast = getNext(getNext(n));

        while (fast != slow ){
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        if(fast == 1){
            return true;
        }else {
            return false;
        }

    }

    /**
     * 循环判断，判断是否是快乐数
     * @param n
     * @return
     */
    public static boolean isHappy2(int n){
        HashSet<Integer> existSet = new HashSet<>();
        while (n != 1){
            existSet.add(n);
            n = getNext(n);
            if(existSet.contains(n)){
                return false;
            }
        }
        return true;
    }

    private static int getNext(int n){
        int sum = 0;
        while (n > 0){
            sum += (n%10 ) * (n%10);
            n = n/10;
        }
        return  sum;
    }
}
