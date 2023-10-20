package HJ2023B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * 题目描述
 * 对一个数据a进行分类，分类方法为：
 *
 * 此数据a（四个字节大小）的四个字节相加对一个给定的值b[取模]如果得到的结果小于一个给定的值c，则数据a为有效类型，其类型为取模的值；如果得到的结果大于或者等于c，则数据a为无效类型。
 *
 * 比如一个数据a=0x01010101，b=3，按照分类方法计算（0x01+0x01+0x01+0x01）%3=1，
 *
 * 所以如果c=2，则此a为有效类型，其类型为1，如果c=1，则此a为无效类型；
 *
 * 又比如一个数据a=0x01010103，b=3，按照分类方法计算（0x01+0x01+0x01+0x03）%3=0，
 *
 * 所以如果c=2，则此a为有效类型，其类型为0，如果c=0，则此a为无效类型。
 *
 * 输入12个数据，第一个数据为c，第二个数据为b，剩余10个数据为需要分类的数据，
 *
 * 请找到有效类型中包含数据最多的类型，并输出该类型含有多少个数据。
 *
 * 输入描述
 * 输入12个数据，用空格分隔，第一个数据为c，第二个数据为b，剩余10个数据为需要分类的数据。
 *
 * 输出描述
 * 输出最多数据的有效类型有多少个数据。
 *
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 *
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java
 * 使用sacnner/system.out.print()
 *
 * 用例
 * 输入	3 4 256 257 258 259 260 261 262 263 264 265
 * 输出	3
 * 说明
 * 10个数据4个字节相加后的结果分别为1 2 3 4 5 6 7 8 9 10，
 *
 * 故对4取模的结果为1 2 3 0 1 2 3 0 1 2，c为3，所以0 1 2都是有效类型，类型为1和2的有3个数据，类型为0的只有2个数据，故输出3。
 *
 * 输入	1 4 256 257 258 259 260 261 262 263 264 265
 * 输出	2
 * 说明
 * 10个数据4个字节相加后的结果分别为1 2 3 4 5 6 7 8 9 10，
 *
 * 故对4取模的结果为1 2 3 0 1 2 3 0 1 2，c为1，
 *
 * 所以只有0是有效类型，类型为0的有2个数据，故输出2。
 *
 *
 */
public class _010_数据分类 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int b = sc.nextInt();
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(sc.nextInt());
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                int temp = data.get(i)>>(j*8);
                int result = temp & 0xFF;
                sum += (data.get(i) >> (j * 8)) & 0xFF;
            }
            int t = sum % b;
            if (t < c) {
                count.put(t, count.getOrDefault(t, 0) + 1);
            }
        }
        int maxCount = 0;
        for (int val : count.values()) {
            maxCount = Math.max(maxCount, val);
        }
        System.out.println(maxCount);
    }
}
