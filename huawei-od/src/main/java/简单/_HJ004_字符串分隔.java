package 简单;

import java.util.Scanner;

/**
 * 描述
 * •输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
 *
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述：
 * 连续输入字符串(每个字符串长度小于等于100)
 *
 * 输出描述：
 * 依次输出所有分割后的长度为8的新字符串
 *
 * 示例1
 * 输入：
 * abc
 * 复制
 * 输出：
 * abc00000
 * 复制
 */
public class _HJ004_字符串分隔 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String source = in.nextLine();
            solution(source);
        }
    }

    public static void solution(String source) {

        char[] sourceArr = source.toCharArray();
        int length = source.length();
        int size = length / 8;
        for (int i = 0; i < size; i++) {
            System.out.println(source.substring(8*i,8*i+8));
        }
        if (length % 8 > 0) {
            int count = length % 8;
            String target = "";
            for (int i = count; i < 8; i++) {
                target += "0";
            }
            System.out.println(source.substring(length - count,length)+target);
        }
    }
}
