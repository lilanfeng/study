package HJ2023B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  题目描述
 * 公司最近准备进行拔河比赛，需要在全部员工中进行挑选。
 * 选拔的规则如下：
 * 按照身高优先、体重次优先的方式准备比赛阵容；
 * 规定参赛的队伍派出10名选手。
 * 请实现一个选拔队员的小程序。
 *
 * 输入为一个数组，记录了部门人员的身高、体重信息，如[身高,体重]的方式放置；
 *
 * 部门全部成员数量为大于10的一个数组。
 *
 * 要求输出一个size为10的二维数组。
 *
 * 输入描述
 * 输入为N行员工信息，表示部门报名参加选拔的候选人信息，每行有两个数字，使用空格分隔，表示员工的身高、体重信息
 * 如
 * 181 70
 * 182 70
 * 表示两位候选员工，第一人身高181厘米，体重70公斤;第二人身高182厘米，体重70公斤
 *
 * 输出描述
 * 要求输出一个10行的已经排序的参赛员工信息数据，每行有两个数字，使用空格分隔，表示员工的身高、体重信息如
 * 182 70
 * 181 70
 *
 * 备注
 * 输入数据范围：
 *
 * 成员身高、体重为int数据类型:
 * 输入备选成员数量为N，10 ≤ N ≤ 100
 * 用例
 * 输入
 * 181 70
 * 182 70
 * 183 70
 * 184 70
 * 185 70
 * 186 70
 * 180 71
 * 180 72
 * 180 73
 * 180 74
 * 180 75
 * 输出
 * 186 70
 * 185 70
 * 184 70
 * 183 70
 * 182 70
 * 181 70
 * 180 75
 * 180 74
 * 180 73
 * 180 72
 * 说明	无
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132631079
 */
public class _043_拔河比赛 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> arrayList = new ArrayList<>();

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            try {
                int[] employee = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                if (employee.length == 2) {
                    arrayList.add(employee);
                }
            } catch (NumberFormatException e) {
                break;
            }
        }

        //arrayList.sort((a,b)-> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        arrayList.sort(
                (a,b)-> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]
        );

        for (int i = 0; i < 10 && i < arrayList.size(); i++) {
            int[] employee = arrayList.get(i);
            System.out.println(employee[0] + " " + employee[1]);
        }

    }
}
