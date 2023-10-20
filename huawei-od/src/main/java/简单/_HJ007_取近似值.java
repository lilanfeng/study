package 简单;

import java.util.Scanner;

/**
 * 描述
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
 *
 * 数据范围：保证输入的数字在 32 位浮点数范围内
 * 输入描述：
 * 输入一个正浮点数值
 *
 * 输出描述：
 * 输出该数值的近似整数值
 *
 * 示例1
 * 输入：
 * 5.5
 * 输出：
 * 6
 * 说明：
 * 0.5>=0.5，所以5.5需要向上取整为6
 * 示例2
 * 输入：
 * 2.499
 * 输出：
 * 2
 * 说明：
 * 0.499<0.5，2.499向下取整为2
 *
 */
public class _HJ007_取近似值 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String target = sc.nextLine();
            int index = target.indexOf(".");
            if(index >= 0){
                String result = target.substring(0,index);
                try {
                    int number = Integer.parseInt(target.substring(index+1,index +2));
                    int resultNumber = Integer.parseInt(result);
                    if(number >= 5){
                        resultNumber++;
                    }
                    System.out.println(resultNumber);
                }catch (NumberFormatException e) {
                    continue;
                }
            }else {
                System.out.println(target);
            }
        }
    }
}
