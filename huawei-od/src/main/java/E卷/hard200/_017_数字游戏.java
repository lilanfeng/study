package E卷.hard200;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/8 20:33
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _017_数字游戏 {
    /**
     * 题目描述
     * 小明玩一个游戏。
     * 系统发1+n张牌，每张牌上有一个整数。
     * 第一张给小明，后n张按照发牌顺序排成连续的一行。
     * 需要小明判断，后n张牌中，是否存在连续的若干张牌，其和可以整除小明手中牌上的数字。
     *
     * 输入描述
     * 输入数据有多组，每组输入数据有两行，输入到文件结尾结束。
     * 第一行有两个整数n和m，空格隔开。m代表发给小明牌上的数字。
     * 第二行有n个数，代表后续发的n张牌上的数字，以空格隔开。
     * 备注
     * 1 ≤ n ≤ 1000
     * 1 ≤ 牌上的整数 ≤ 400000
     * 输入的组数，不多于1000
     * 用例确保输入都正确，不需要考虑非法情况。
     * 输出描述
     * 对每组输入，如果存在满足条件的连续若干张牌，则输出1;否则，输出0
     *
     * 示例1
     * 输入
     *
     * 6 7
     * 2 12 6 3 5 5
     * 10 11
     * 1 1 1 1 1 1 1 1 1 1
     * 输出
     * 1
     * 0
     * 说明
     *
     * 第一组小明牌的数字为7，再发了6张牌。第1、2两张牌教字和为14，可以整除7，输出1，第二组小明牌的教字为11，再发了10张牌，这10张牌数字和为10，无法整除11，输出0。
     *
     * 解题思路
     * 题目描述可以理解为，小明玩一个游戏，游戏中系统会发1+n张牌，其中第一张牌给小明，后续的n张牌排成一行。小明需要判断在这n张牌中，是否存在连续的若干张牌，其数字之和可以被小明手中牌上的数字整除。
     *
     * 具体理解：
     * 系统发1+n张牌，第一张牌上的数字是小明手中的牌上的数字，称为m。
     * 剩下的n张牌按照发牌顺序排成一行，并且每张牌上也有一个整数。
     * 任务是要找到这些n张牌中的连续若干张牌，使得它们的数字之和能够被小明手中牌上的数字m整除。
     * 示例解释：
     * 例如，输入6 7表示小明手中的牌上的数字是7，后续发了6张牌，牌上的数字依次为2 12 6 3 5 5。
     * 其中第1、2两张牌的数字和为14，可以被7整除，因此输出1。
     * 第二组输入10 11表示小明手中的牌上的数字是11，后续发了10张牌，牌上的数字均为1。
     * 这10张牌的任何连续子数组的和都不能被11整除，因此输出0。
     * 总体思路是使用累加和的余数来判断是否存在连续的若干张牌和可以整除m。通过遍历后续发的牌的数字，累加到sum中，并计算当前和的余数。如果之前已经存在相同的余数，说明存在连续的若干张牌和可以整除m，将found标记为true
     * 。最后根据found的值输出1或0，表示是否存在满足条件的连续若干张牌。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }
            System.out.println(find(m, nums));
        }
       scanner.close();
    }

    /**
     * 6 7
     * 2 12 6 3 5 5
     * @param m
     * @param nums
     * @return
     */
    public static int find(int m, int[] nums) {
        int sum = 0;
        boolean found = false;
        int[] map = new int[m];
        for (int num : nums) {
            sum += num;
            int remainder = sum % m;
            if (map[remainder] > 0) {
                found = true;
                break;
            }
            map[remainder]++;
        }
        return found ? 1 : 0;
    }
}
