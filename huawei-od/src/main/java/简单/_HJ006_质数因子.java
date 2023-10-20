package 简单;

import com.sun.nio.sctp.SctpChannel;

import java.util.Scanner;

public class _HJ006_质数因子 {

    /**
     * 描述
     * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
     *
     *
     * 数据范围：
     * 1
     * ≤
     * �
     * ≤
     * 2
     * ×
     * 1
     * 0
     * 9
     * +
     * 14
     *
     * 1≤n≤2×10
     * 9
     *  +14
     * 输入描述：
     * 输入一个整数
     *
     * 输出描述：
     * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。
     *
     * 示例1
     * 输入：
     * 180
     * 复制
     * 输出：
     * 2 2 3 3 5
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){

            int target = sc.nextInt();

            int y = 2;
            //因子从 2 开始
            while (target != 1){
                if(target % y == 0){
                    System.out.print(y+" ");
                    target = target/y;
                }else {
                    if( y > target/y){
                        y = target;
                    }else {
                        y++;
                    }
                }
            }
        }

    }
}
