package 简单;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 2.
 * 字符集合
 * 输入一个字符串，求出该字符串包含的字符集合，按照字母输入的顺序输出。
 *
 * 数据范围：输入的字符串长度满足
 * 1
 * ≤
 * �
 * ≤
 * 100
 *
 * 1≤n≤100  ，且只包含大小写字母，区分大小写。
 *
 * 本题有多组输入
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 * 输入描述：
 * 每组数据输入一个字符串，字符串最大长度为100，且只包含字母，不可能为空串，区分大小写。
 * 输出描述：
 * 每组数据一行，按字符串原有的字符顺序，输出字符集合，即重复出现并靠后的字母不输出。
 * 示例1
 * 输入例子：
 * abcqweracb
 * 输出例子：
 * abcqwer
 * 示例2
 * 输入例子：
 * aaa
 * 输出例子：
 * a
 */
public class _2023081402_字符集合去重排序问题 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String input = in.next();
            solution(input);
        }
    }

    public static void solution(String input){
        char[] charInputArr = input.toCharArray();
        Arrays.sort(charInputArr);
        StringBuilder resultStr = new StringBuilder();
        Map<String,String> existKeyMap = new HashMap();
        for (int i = 0; i < charInputArr.length; i++) {
            String temp = String.valueOf(charInputArr[i]);
            if(!existKeyMap.containsKey(temp)){
                resultStr.append(temp);
                existKeyMap.put(temp,temp);
            }
        }
        System.out.println(resultStr.toString());

    }
}
