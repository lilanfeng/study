package 简单;


import java.util.Scanner;

/**
 * URL： https://www.nowcoder.com/practice/74c493f094304ea2bda37d0dc40dc85b
 *  描述
 * 公元五世纪，我国古代数学家张丘建在《算经》一书中提出了“百鸡问题”：鸡翁一值钱五，鸡母一值钱三，鸡雏三值钱一。百钱买百鸡，问鸡翁、鸡母、鸡雏各几何？
 * 现要求你打印出所有花一百元买一百只鸡的方式。
 * 输入描述：
 * 输入任何一个整数，即可运行程序。
 *
 * 输出描述：
 *  输出有数行，每行三个整数，分别代表鸡翁，母鸡，鸡雏的数量
 *
 * 示例1
 * 输入：
 * 1
 * 复制
 * 输出：
 * 0 25 75
 * 4 18 78
 * 8 11 81
 * 12 4 84
 */
public class _HJ72_百钱买百鸡 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()){
            int a = in.nextInt();
            buy(a);
        }

    }

    /**
     * 打印百钱买百鸡的可能情况
     *  鸡翁一值钱五，鸡母一值钱三，鸡雏三值钱一
     * 输出有数行，每行三个整数，分别代表鸡翁，母鸡，鸡雏的数量
     *   x + y + z = 100 和 5x + 3y + z/3 = 100
     *
     *   得出：7x + 4y = 100   =》 7x < 100
     *
     *
     * @param a
     */
    private static void buy(int a){

        for (int i = 0; i < 14; i++) {

            if( (100 - 7*i )% 4 == 0){
                int x = i;
                int y = (100 - 7*i )/4;
                int z = 100 - x - y;
                System.out.print(x + " ");
                System.out.print(y + " ");
                System.out.print(z + " ");
                System.out.println();

            }
        }

    }
}
