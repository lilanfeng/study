package 简单;

import java.util.Scanner;

/**
 * 描述
 * 输入一行字符，分别统计出包含英文字母、空格、数字和其它字符的个数。
 *
 * 数据范围：输入的字符串长度满足 1≤n≤1000
 *
 * 输入描述：
 * 输入一行字符串，可以有空格
 *
 * 输出描述：
 * 统计其中英文字符，空格字符，数字字符，其他字符的个数
 *
 * 示例1
 * 输入：
 * 1qazxsw23 edcvfr45tgbn hy67uj m,ki89ol.\\/;p0-=\\][
 * 输出：
 * 26
 * 3
 * 10
 * 12
 */
public class _HJ040_统计字符 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            countTotal(sc.nextLine());
        }
    }

    public static void countTotal(String str){
        int englishCount = 0;
        int spaceCount = 0;
        int intCount = 0;
        int otherCount = 0;
        for (int i = 0; i < str.length(); i++) {
            Character temp = str.charAt(i);
            if(Character.isLetter(temp)){
                englishCount++;
                continue;
            }
            if(Character.isSpaceChar(temp)){
                spaceCount++;
                continue;
            }
            if(Character.isDigit(temp)){
                intCount++;
                continue;
            }
            otherCount++;
        }
        System.out.println(englishCount);
        System.out.println(spaceCount);
        System.out.println(intCount);
        System.out.println(otherCount);

    }
}
