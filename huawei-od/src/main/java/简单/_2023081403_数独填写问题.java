package 简单;


import java.util.Scanner;

/**
 * 3.
 * 数独
 * 数独是一个我们都非常熟悉的经典游戏，运用计算机我们可以很快地解开数独难题，现在有一些简单的数独题目，请编写一个程序求解。
 * 如有多解，输出一个解
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 * 输入描述：
 * 输入9行，每行为空格隔开的9个数字，为0的地方就是需要填充的。
 * 输出描述：
 * 输出九行，每行九个空格隔开的数字，为解出的答案。
 * 输入：
 *
 * 0 0 8 0 7 0 6 0 0
 * 0 1 0 5 6 0 0 0 0
 * 0 2 0 0 0 0 0 0 1
 * 0 6 0 0 0 0 0 0 0
 * 5 7 1 0 3 0 4 9 8
 * 0 0 0 0 0 0 0 7 0
 * 4 0 0 0 0 0 0 6 0
 * 0 0 0 0 4 3 0 5 0
 * 0 0 3 0 5 0 2 0 0
 *
 * 输出：
 *
 * 1 3 8 2 7 5 6 4 9
 * 2 1 4 5 6 7 9 8 3
 * 6 2 5 4 8 9 7 3 1
 * 3 6 7 1 9 4 8 2 5
 * 5 7 1 6 3 2 4 9 8
 * 8 4 9 3 2 1 5 7 6
 * 4 5 2 9 1 8 3 6 7
 * 7 9 6 8 4 3 1 5 2
 * 9 8 3 7 5 6 2 1 4
 * ————————————————
 * 版权声明：本文为CSDN博主「ZSYL」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_46092061/article/details/117002192
 */
public class _2023081403_数独填写问题 {

    static int[][] matrix;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        matrix = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        dfs(0,0);

    }

    public static void dfs(int i,int j){
        if(i == 9){
            printResult();
            System.exit(0);
        }

        if(matrix[i][j] == 0){
            for (int k = 1; k < 10; k++) {
                if(!check(i,j,k)) {
                    matrix[i][j] = k;
                    dfs(i+ (j+1)/9,(j+1)%9);
                }
            }

            matrix[i][j] = 0;

        }else {
            dfs(i+(j+1)/9,(j+1)%9);
        }
    }

    public static boolean check(int i,int j,int num){
        for (int k = 0; k < 9; k++) {
            if(matrix[i][k] == num || matrix[k][j] == num) {
                return true;
            }
        }
        return false;
    }

    public static void printResult(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] +" ");
            }
            System.out.println();
        }
    }

}
