package 简单;

import java.util.Scanner;

/**
 *  描述
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
 *
 * 数据范围：
 * 1
 * ≤
 * �
 * ≤
 * 1000
 *
 * 1≤n≤1000
 * 输入描述：
 * 第一行输入一个由字母、数字和空格组成的字符串，第二行输入一个字符（保证该字符不为空格）。
 *
 * 输出描述：
 * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
 *
 * 示例1
 * 输入：
 * ABCabc
 * A
 * 复制
 * 输出：
 * 2
 */
public class _HJ002_计算某字符串出现次数 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        String first = "";
        String second = "";
        while (in.hasNextLine()){
            count ++;
            if(count == 1){
                first = in.nextLine();
            }
            if(count == 2){
                second = in.nextLine();
                if(second.length() != 1){
                    count--;
                    continue;
                }
                solution(first,second);
                count = 0;
            }
        }

    }

    public static void solution(String first,String second){
        char[] firstArr = first.toCharArray();
        int total = 0;
        for (int i = 0; i < firstArr.length; i++) {
            if(String.valueOf(firstArr[i]).equalsIgnoreCase(second)){
                total++;
            }
        }
        System.out.println(total);
    }
}
