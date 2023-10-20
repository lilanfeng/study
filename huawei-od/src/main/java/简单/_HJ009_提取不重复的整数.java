package 简单;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 描述
 * 输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * 保证输入的整数最后一位不是 0 。
 *
 * 数据范围：
 * 1
 * ≤
 * �
 * ≤
 * 1
 * 0
 * 8
 *
 * 1≤n≤10
 * 8
 *
 * 输入描述：
 * 输入一个int型整数
 *
 * 输出描述：
 * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 *
 * 示例1
 * 输入：
 * 9876673
 * 复制
 * 输出：
 * 37689
 */
public class _HJ009_提取不重复的整数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String source = sc.nextLine();
            solution(source);
        }
    }

    public static void solution(String source){
        char[] sourceArr = source.toCharArray();
        int length = sourceArr.length;
        Map<String,String> existMap = new HashMap<>(length);
        for (int i = length-1; i >= 0; i--) {
            String temp = String.valueOf(sourceArr[i]);
            if(!existMap.containsKey(temp)){
                existMap.put(temp,temp);
                System.out.print(temp);
            }
        }
        System.out.println();
    }

}
