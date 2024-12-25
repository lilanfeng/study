package E卷.easy100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _015_勾股数元组 {

    /**
     * 题目描述
     * 如果3个正整数(a,b,c)满足a^2 + b^2 = c^2的关系，则称(a,b,c)为勾股数（著名的勾三股四弦五），
     * 为了探索勾股数的规律，我们定义如果勾股数(a,b,c)之间两两互质（即a与b，a与c，b与c之间均互质，没有公约数），
     * 则其为勾股数元组（例如(3,4,5)是勾股数元组，(6,8,10)则不是勾股数元组）。
     * 请求出给定范围[N,M]内，所有的勾股数元组。
     *
     * 输入描述
     * 起始范围N，1 <= N <= 10000
     * 结束范围M，N < M <= 10000
     *
     * 输出描述
     * a,b,c请保证a < b < c,输出格式：a b c；
     *
     * 多组勾股数元组请按照a升序，b升序，最后c升序的方式排序输出；
     *
     * 给定范围中如果找不到勾股数元组时，输出”NA“。
     *
     * 示例1
     * 输入
     *
     * 1
     * 20
     * 输出
     *
     * 3 4 5
     * 5 12 13
     * 8 15 17
     * 说明
     * [1,20]范围内勾股数有：(3 4 5)，(5 12 13)，(6 8 10)，(8 15 17)，(9 12 15)，(12 16 20)；
     * 其中，满足(a,b,c)之间两两互质的勾股数元组有：(3 4 5)，(5 12 13)，(8 15 17);
     *
     * 按输出描述中顺序要求输出结果。
     * 示例2
     * 输入
     * 5
     * 10
     * 输出
     * NA
     * 说明
     *
     * [5,10]范围内勾股数有：(6 8 10)；
     *
     * 其中，没有满足(a,b,c)之间两两互质的勾股数元组；
     *
     * 给定范围中找不到勾股数元组，输出”NA“
     * 解题思路
     * 勾股数的定义
     * 如果3个正整数 (a,b,c) 满足  a^2 + b^2 = c^2
     *  ，则称 (a,b,c) 为勾股数。例如，(3,4,5) 是一个典型的勾股数，因为 3^2 + 4^2 = 5^2。
     *
     * 勾股数元组的定义
     * 题目进一步要求：如果勾股数 (a,b,c) 之间任意两数互质（即 a aa 与 b bb、a aa 与 c cc、b bb 与 c cc
     * 之间都没有公约数，除了1以外），则称其为勾股数元组。例如： (3,4,5) 是勾股数元组，因为 gcd(3,4)=gcd(3,5)=gcd(4,5)=1。
     * (6,8,10) 不是勾股数元组，因为 g c d ( 6 , 8 ) = 2 gcd(6, 8) = 2  gcd(6,8)=2，它们之间有公约数。
     * 该题的关键在于：
     *
     * 需要遍历范围内的所有三元组，检查是否满足勾股数关系  a^2 + b^2 = c^2。
     * 对每个满足勾股数的三元组，进一步检查它们是否互质（通过 gcd 函数）。
     * 最后根据要求进行排序输出，或者在找不到符合条件的情况下输出 “NA”。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            int m = Integer.parseInt(scanner.nextLine());
            List<String> resultList = findTriplets(n, m);
            if (resultList.isEmpty()) {
                System.out.println("NA");
            } else {
                resultList.forEach(System.out::println);
            }

        }

    }

    /**
     * 找出所有满足条件的勾股数元组
     * @param n
     * @param m
     * @return
     */
    private static List<String> findTriplets(int n, int m) {
        List<String> resultList = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = n; i <= m; i++) {
            map.put(i * i, i);
        }

        for (int a = n; a <= m; a++) {
            for (int b = a + 1; b <= m; b++) {
               int sum = a * a + b * b;
               if (map.containsKey(sum)) {
                   int c = map.get(sum);
                   if (gcd(a, b) == 1 && gcd(a, c) == 1 && gcd(b, c) == 1) {
                       resultList.add(a + " " + b + " " + c);
                   }
               }
            }
        }

        return resultList;
    }

    /**
     * 欧几里得算法求最大公约数
     * @param a
     * @param b
     * @return
     */
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
