package 简单;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 描述
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 *
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 *
 * 如，输入： Type 输出： epTy
 *
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 *
 * 如，输入： BabA 输出： aABb
 *
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 *
 *
 * 如，输入： By?e 输出： Be?y
 *
 * 数据范围：输入的字符串长度满足
 * 1
 * ≤
 * �
 * ≤
 * 1000
 *
 * 1≤n≤1000
 *
 * 输入描述：
 * 输入字符串
 * 输出描述：
 * 输出字符串
 * 示例1
 * 输入：
 * A Famous Saying: Much Ado About Nothing (2012/8).
 * 复制
 * 输出：
 * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
 */
public class _HJ026_字符串排序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String source = sc.nextLine();
            solution(source);
        }
    }

    public static void solution(String source){
        char[] sourceArr = source.toCharArray();
        List<Character> letters = new ArrayList<>();
        for (int i = 0; i < sourceArr.length; i++) {
            if(Character.isLetter(sourceArr[i]) ){
                letters.add(sourceArr[i]);
            }
        }
        letters.sort(Comparator.comparingInt(Character::toLowerCase));
        StringBuilder result = new StringBuilder();
        for (int i = 0,j= 0; i < source.length(); i++) {
            if(Character.isLetter(source.charAt(i))){
                result.append(letters.get(j++));
            }else {
                result.append(source.charAt(i));
            }
        }
        System.out.println(result.toString());
    }
}
