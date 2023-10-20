package 简单;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 描述
 * 给出一个字符串，该字符串仅由小写字母组成，定义这个字符串的“漂亮度”是其所有字母“漂亮度”的总和。
 * 每个字母都有一个“漂亮度”，范围在1到26之间。没有任何两个不同字母拥有相同的“漂亮度”。字母忽略大小写。
 *
 * 给出多个字符串，计算每个字符串最大可能的“漂亮度”。
 *
 * 本题含有多组数据。
 *
 * 数据范围：输入的名字长度满足
 * 1≤n≤10000
 *
 * 输入描述：
 * 第一行一个整数N，接下来N行每行一个字符串
 *
 * 输出描述：
 * 每个字符串可能的最大漂亮程度
 *
 * 示例1
 * 输入：
 * 2
 * zhangsan
 * lisi
 * 输出：
 * 192
 * 101
 * 说明：
 * 对于样例lisi，让i的漂亮度为26，l的漂亮度为25，s的漂亮度为24，lisi的漂亮度为25+26+24+26=101.
 */
public class _HJ045_名字漂亮度 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        String[] strList = new String[number];
        for (int i = 0; i < number; i++) {
            strList[i] = sc.next();
        }

        for (int i = 0; i < strList.length; i++) {
            solution(strList[i]);
        }

    }

    public static void solution(String str){
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            String tmp = String.valueOf(str.charAt(i));
            map.put(tmp,map.getOrDefault(tmp,0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        Integer[] sortList =  list.toArray(new Integer[0]);
        Arrays.sort(sortList);
        int total = 0;
        for (int i = sortList.length -1,j = 26; i >= 0 ; i--) {
            total += j*sortList[i];
            j--;
        }
        System.out.println(total);
    }
}
