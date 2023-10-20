package 简单;

import java.util.Scanner;

/**
 * 描述
 * 假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半; 再落下, 求它在第5次落地时，共经历多少米?第5次反弹多高？
 * 数据范围：输入的小球初始高度满足 1≤n≤1000  ，且保证是一个整数
 *
 * 输入描述：
 * 输入起始高度，int型
 *
 * 输出描述：
 * 分别输出第5次落地时，共经过多少米以及第5次反弹多高。
 * 注意：你可以认为你输出保留六位或以上小数的结果可以通过此题。
 * 示例1
 * 输入：
 * 1
 * 输出：
 * 2.875
 * 0.03125
 */
public class _HJ038_求小球落地5次后所经历的路程和第五次反弹的高度 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()){
            solution(sc.nextInt(),5);
        }
    }

    public static void solution(int height,int count){

        float total = 0F;
        float reHeight = 0F;
        for (int i = 1; i <= count; i++) {
            total += (height*3)/(float) Math.pow(2,i);
            reHeight = height/ (float)Math.pow(2,i);
        }
        System.out.println(total-reHeight);
        System.out.println(reHeight);
    }

}
