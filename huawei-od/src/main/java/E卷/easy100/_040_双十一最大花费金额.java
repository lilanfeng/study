package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/11 17:18
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _040_双十一最大花费金额 {
    /**
     * 题目描述
     * 双十一众多商品进行打折销售，小明想购买自己心仪的一些物品，但由于受购买资金限制，所以他决定从众多心仪商品中购买三件，而且想尽可能的花完资金。
     * 现在请你设计一个程序帮助小明计算尽可能花费的最大资金数额。
     * 输入描述
     * 输入第一行为一维整型数组M，数组长度小于100，数组元素记录单个商品的价格，单个商品价格小于1000。
     * 输入第二行为购买资金的额度R，R小于100000。
     * 输入格式是正确的，无需考虑格式错误的情况。
     * 输出描述
     * 输出为满足上述条件的最大花费额度。
     * 如果不存在满足上述条件的商品，请返回-1。
     * 示例1
     * 输入
     * 23,26,36,27
     * 78
     * 输出
     * 76
     * 说明
     *
     * 金额23、26和27相加得到76，而且最接近且小于输入金额78。
     *
     * 示例2
     * 输入
     * 23,30,40
     * 26
     * 输出
     *
     * -1
     * 说明
     *
     * 因为输入的商品，无法组合出来满足三件之和小于26.故返回-1。
     *
     * 解题思路
     * 使用了暴力搜索的方法来解决：
     *
     * 三重循环:
     *
     * 通过三个嵌套循环遍历所有可能的三件商品组合。
     * 第一个循环的索引 i 从 0 到 goods.length - 3，第二个循环的索引 j 从 i + 1 到 goods.length - 2，第三个循环的索引 k 从 j + 1 到 goods.length -
     * 1。这种方式确保每次组合都是不同的三件商品。
     * 总价计算与筛选:
     *
     * 在每次内层循环中，计算三件商品的总价 tmp。
     * 如果 tmp 小于或等于预算 maxMoney，则将其加入结果列表 res。
     * 最大值查找:
     *
     * 如果结果列表 res 不为空，则遍历列表找出最大的总价并输出。
     * 如果 res 为空，说明没有找到合法的三件商品组合，输出 -1。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] costArr = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int maxMoney = Integer.parseInt(scanner.nextLine());
        System.out.println(getMaxMoney(costArr, maxMoney));
    }

    private static int getMaxMoney(int[] costArr, int maxMoney) {
        int max = -1;
        for (int i = 0; i < costArr.length - 2; i++) {
            for (int j = i + 1; j < costArr.length - 1; j++){
                for (int k = j + 1; k < costArr.length; k++){
                    int tmp = costArr[i] + costArr[j] + costArr[k];
                    if (tmp <= maxMoney){
                        max = Math.max(max, tmp);
                    }
                }
            }
        }

        return max;
    }
}
