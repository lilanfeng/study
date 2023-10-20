package HJ2023B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 一个荒岛上有若干人，岛上只有一条路通往岛屿两端的港口，大家需要逃往两端的港口才可逃生。
 * 假定每个人移动的速度一样，且只可选择向左或向右逃生。
 * 若两个人相遇，则进行决斗，战斗力强的能够活下来，并损失掉与对方相同的战斗力；若战斗力相同，则两人同归于尽。
 *
 * 输入描述
 * 给定一行非 0 整数数组，元素个数不超过30000；
 * 正负表示逃生方向（正表示向右逃生，负表示向左逃生），绝对值表示战斗力，越左边的数字表示里左边港口越近，逃生方向相同的人永远不会发生决斗。
 *
 * 输出描述
 * 能够逃生的人总数，没有人逃生输出0，输入异常时输出-1。
 *
 * 用例
 * 输入	5 10 8 -8 -5
 * 输出	2
 * 说明	第3个人和第4个人同归于尽，第2个人杀死第5个人并剩余5战斗力，第1个人没有遇到敌人。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132591835
 */
public class _024_荒岛求生 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            List<Integer> list = Arrays.stream(str.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            if(list.size() > 30000){
                System.out.println(-1);
                return;
            }
            System.out.println(solution(list));
        }

    }

    public static int solution(List<Integer> people){
        List<Integer> aliveList = new ArrayList<>();

        for (int person:people) {
            if(person == 0){
                return -1;
            }
            boolean alive = true;
            while (alive && person < 0 && !aliveList.isEmpty() && aliveList.get(aliveList.size() -1) > 0) {
                alive = aliveList.get(aliveList.size() -1) < -person;
                if(aliveList.get(aliveList.size() -1) <= -person){
                    aliveList.remove(aliveList.size() -1);
                }
            }
            if(alive){
                aliveList.add(person);
            }
        }

        return aliveList.size();
    }

}
