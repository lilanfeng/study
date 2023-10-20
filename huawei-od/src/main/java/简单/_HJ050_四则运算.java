package 简单;

import java.util.Scanner;
import java.util.Stack;

/**
 * 描述
 * 输入一个表达式（用字符串表示），求这个表达式的值。
 * 保证字符串中的有效字符包括[‘0’-‘9’],‘+’,‘-’, ‘*’,‘/’ ,‘(’， ‘)’,‘[’, ‘]’,‘{’ ,‘}’。且表达式一定合法。
 *
 * 数据范围：表达式计算结果和过程中满足  ∣val∣≤1000  ，字符串长度满足  1≤n≤1000
 * 输入描述：
 * 输入一个算术表达式
 * 输出描述：
 * 得到计算结果
 * 示例1
 * 输入：
 * 3+2*{1+2*[-4/(8-6)+7]}
 * 复制
 * 输出：
 * 25
 */
public class _HJ050_四则运算 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            makeResult(sc.nextLine());
        }
    }

    public static void makeResult(String str){

        int n = str.length();
        int num1 = 0;
        int o1 = 1;
        int num2 = 0;
        int o2 = 1;
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
           char c = str.charAt(i);
           if(Character.isDigit(c)){
               int cur = 0;
               while (i < n && Character.isDigit(str.charAt(i))){
                   cur = cur*10 + (str.charAt(i) - '0');
                   i++;
               }
               i--;
               num2 = o2 == 1? num2 * cur:num2/cur;
           }else if(c =='*' || c == '/'){
               o2 = c=='*' ? 1: -1;
           }else if(c == '(' || c == '{' || c== '['){
               stk.push(num1);
               stk.push(o1);
               stk.push(num2);
               stk.push(o2);
               num1 = 0;
               o1 = 1;
               num2 = 1;
               o2 = 1;
           } else if(c == '+' || c == '-'){
               if(c == '-' && (i == 0|| str.charAt(i-1) == '(' ||
                       str.charAt(i-1) == '[' || str.charAt(i-1) == '{')){
                   o1 = -1;
                   continue;
               }
               num1 = num1+ o1*num2;
               o1 = (c == '+' ?1 : -1);
               num2 = 1;
               o2 = 1;
           }else {
               int cur = num1 + o1*num2;
               o2 = stk.pop();
               num2 = stk.pop();
               o1 = stk.pop();
               num1 = stk.pop();
               num2 = o2 == 1? num2 * cur : num2 / cur;
           }
        }

        System.out.println(num1 + o1*num2);
    }
}
