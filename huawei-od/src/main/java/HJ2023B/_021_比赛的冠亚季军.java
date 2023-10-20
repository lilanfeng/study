package HJ2023B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目描述
 * 有N（3 ≤ N < 10000）个运动员，他们的id为0到N-1,
 * 他们的实力由一组整数表示。他们之间进行比赛，需要决出冠亚军。比赛的规则是0号和1号比赛，2号和3号比赛，以此类推，每一轮，相邻的运动员进行比赛，获胜的进入下一轮；实力值大的获胜，实力值相等的情况，id
 * 小的情况下获胜；轮空的直接进入下一轮。
 *
 * 输入描述
 * 输入一行N个数字代表N的运动员的实力值(0<=实力值<=10000000000)。
 *
 * 输出描述
 * 输出冠亚季军的id，用空格隔开。
 *
 * 用例
 * 输入
 *
 * 2 3 4 5
 * 输出
 *
 * 3 1 2
 * 说明
 *
 * 第一轮比赛，
 *
 * id为0实力值为2的运动员和id为1实力值为3的运动员比赛，1号胜出进入下一轮争夺冠亚军，
 *
 * id为2的运动员和id为3的运动员比赛，3号胜出进入下一轮争夺冠亚军，
 *
 * 冠亚军比赛，3号胜1号，故冠军为3号，亚军为1号，
 *
 * 2号与0号，比赛进行季军的争夺，2号实力值为4，0号实力值2，故2号胜出，得季军。
 *
 * 冠亚季军为3 1 2。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132008139
 */
public class _021_比赛的冠亚季军 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            findWin(sc.nextLine());
        }
    }

    public static  void findWin(String str){
        long[] strenghts  = Arrays.stream(str.split(" ")).mapToLong(Long::parseLong).toArray();
        LinkedList<ArrayList<Integer>> rounds = new LinkedList<>();
        ArrayList<Integer> plays = new ArrayList<>();
        for (int i = 0; i < strenghts.length; i++) {
            plays.add(i);
        }

        play(plays,strenghts,rounds);
        while (rounds.getFirst().size() > 1){
            play(rounds.removeFirst(),strenghts,rounds);
        }
        //冠军
        int first = rounds.get(0).get(0);
        //亚军
        int second = rounds.get(1).get(0);
        rounds.get(2).sort((a,b)->strenghts[b] != strenghts[a] ?Long.compare(strenghts[b],strenghts[a]) :a - b );
        //季军
        int third = rounds.get(2).get(0);
        System.out.println(first+" " + second +" "+ third);
    }

    /**
     * 进行一轮比赛处理
     * @param plays
     * @param strengths
     * @param rounds
     */
    public static void play(ArrayList<Integer> plays, long[] strengths, LinkedList<ArrayList<Integer>> rounds){
        ArrayList<Integer> win = new ArrayList<>();
        ArrayList<Integer> lost = new ArrayList<>();
        for (int i = 1; i < plays.size(); i += 2) {
            int number1 = plays.get(i-1);
            int number2 = plays.get(i);
            if(strengths[number1] >strengths[number2]){
                win.add(number1);
                lost.add(number2);
            }else {
                win.add(number2);
                lost.add(number1);
            }
        }

        if(plays.size() % 2 != 0){
            win.add(plays.get(plays.size() -1));
        }
        rounds.addFirst(lost);
        rounds.addFirst(win);
        while (rounds.size() > 3){
            rounds.removeLast();
        }

    }

}
