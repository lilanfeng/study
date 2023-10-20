package HJ2023B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 题目描述
 * 双十一众多商品进行打折销售，小明想购买自己心仪的一些物品，但由于受购买资金限 制，所以他决定从众多心仪商品中购买三件，而且想尽可能的花完资金。
 * 现在请你设计一个程序帮助小明计算尽可能花费的最大资金数额
 * 输入描述
 * 输入第一行为一维整型数组 M，数组长度小于 100，数组元素记录单个商品的价格，单
 * 个商品价格小于 1000.
 * 输入第二行为购买资金的额度 R，R 小于 100000 输入格式是正确的，无需考虑格式错误的情况. 输出描述
 * 输出为满足上述条件的最大花费额度 如果不存在满足上述条件的商品，请返回-1。
 * 用例
 *  输入：23,26,36,27
 *      78
 *  输出：
 *      76
 *
 *   输入：23,30,40
 *      26
 *   输出： -1
 *
 */
public class _026_最大花费金额 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            Integer[] goods = Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);
            Integer totalPrice = Integer.parseInt(sc.nextLine());
            System.out.println(findMaxCost(goods,totalPrice));
        }
    }

    public static int findMaxCost(Integer[] goods,Integer totalPrice){
        if (goods.length < 3) {
            return -1;
        }
        Arrays.sort(goods);
        int result = -1;
        Integer sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += goods[i];
        }

        if (sum > totalPrice) {
            return -1;
        } else if (sum.equals(totalPrice)) {
            return totalPrice;
        } else {
            result = sum;
        }

        for (int i = 3; i < goods.length; i++) {
            sum = sum + goods[i] + goods[i-3];
            if(sum > totalPrice){
                return result;
            }else if(sum.equals(totalPrice)){
                return totalPrice;
            }else {
                result = sum;
            }
        }
        return result;
    }
}
