package E卷.hard200;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 17:45
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _005_仿LISP运算 {

    /**
     *  题目描述
     * LISP 语言唯一的语法就是括号要配对。
     * 形如 (OP P1 P2 …)，括号内元素由单个空格分割。
     * 其中第一个元素 OP 为操作符，后续元素均为其参数，参数个数取决于操作符类型。
     * 注意：
     * 参数 P1, P2 也有可能是另外一个嵌套的 (OP P1 P2 …) ，当前 OP 类型为 add / sub / mul / div（全小写）
     *  分别代表整数的加减乘除法，简单起见，所有 OP 参数个数均为 2 。
     * 举例：
     * 输入：(mul 3 -7)输出：-21
     * 输入：(add 1 2) 输出：3
     * 输入：(sub (mul 2 4) (div 9 3)) 输出 ：5
     * 输入：(div 1 0) 输出：error
     * 题目涉及数字均为整数，可能为负；
     * 不考虑 32 位溢出翻转，计算过程中也不会发生 32 位溢出翻转，
     * 除零错误时，输出 “error”，
     * 除法遇除不尽，向下取整，即 3/2 = 1
     * 输入描述
     * 输入为长度不超过512的字符串，用例保证了无语法错误
     * 输出描述
     * 输出计算结果或者“error”
     * 示例1
     * 输入
     * (div 12 (sub 45 45))
     * 输出
     * error
     * 说明
     *
     * 示例2
     * 输入
     * (add 1 (div -7 3))
     * 输出
     * -2
     * 说明
     *
     * 输入：(mul 3 -7)
     * 输出：-21
     * 输入：(add 1 2)
     * 输出：3
     * 输入：(sub (mul 2 4) (div 9 3))
     * 输出：5
     * 输入：(div 1 0)
     * 输出：error
     * 解题思路
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            calculateResult(str);
        }
    }

    public static void calculateResult(String str) {

        Stack<Integer> numStack = new Stack<>();
        // 数字栈
        Stack<String> operaStack = new Stack<>();
        // 操作符栈

        //标记数字的开始位置
        int mark = 0;

        for (int i = 0; i < str.length(); i++) {
            String ch  = str.charAt(i) + "";
            if(ch.equals("(")){
                //左括号处理场景 1，操作符入操作符栈
                operaStack.push(str.substring(i+1,i + 4));
                i += 4;
                // 标记数字的开始位置 +1 空格存在
                mark = i + 1;
            } else if(ch.equals(")")){
                // 右括号处理场景 1，数字串入数字栈   2，操作符出栈后做运算处理
                if(mark < i){
                    // 存在数字串的情况
                    numStack.push(Integer.parseInt(str.substring(mark,i)));
                    i += 1;
                    mark = i + 1;
                }
                if(operaStack.size() > 0){
                    String opera = operaStack.pop();
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    calculate(num1, num2, opera,numStack);
                }
            } else {
                // 空格处理
                if(ch.equals(" ")){
                    if(mark < i){
                        // 存在数字串的情况
                        numStack.push(Integer.parseInt(str.substring(mark,i)));
                        mark = i + 1;
                    }
                }
            }
        }

        while (operaStack.size() > 0){
            String opera = operaStack.pop();
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            calculate(num1, num2, opera,numStack);
        }
        System.out.println(numStack.pop());
    }

    private static void  calculate(int num1, int num2, String opera,Stack<Integer> numStack) {
        int result = 0;
        switch (opera){
            case "add":
                result = num1 + num2;
                break;
            case "sub":
                result = num1 - num2;
                break;
            case "mul":
                result = num1 * num2;
                break;
            case "div":
                if(num2 == 0){
                    System.out.println("error");
                    System.exit(0);
                }else {
                    result = num1 / num2;
                    if(num1 % num2 != 0){
                        if(result < 0){
                            result--;
                        }else {
                            result++;
                        }
                    }
                }
                break;
            default:
                break;
        }
        numStack.push(result);
   }
}
