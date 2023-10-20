package HJ2023B200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 篮球(5V5)比赛中，每个球员拥有一个战斗力，每个队伍的所有球员战斗力之和为该队 伍的总体战斗力。现有 10 个球员准备分为两队进行训练赛，教练希望 2 个队伍的战斗力差 值能够尽可能的小，以达到最佳训练效果。
 * 给出 10 个球员的战斗力，如果你是教练，你该如何分队，才能达到最佳训练效果?请说 出该分队方案下的最小战斗力差值。
 * 输入描述
 * 10 个篮球队员的战斗力(整数，范围[1，10000])战斗力之间用空格分隔，如:10987654321 不需要考虑异常输入的场景
 * 输出描述 最小的战斗力差值，如:1
 *   用例：
 *      输入：10 9 8 7 6 5 4 3 2 1
 *      输出： 1
 * 题目解析
 * 本题由于只有十个数，并且平均分为两队，即每队五个数，因此只需要在 10 个数中选 5 个数组合即可模拟一队能力值。
 * 有了一队的能力值之和 A，我们用十个数的总和减去 A，即得到了另一队的能力值之和 B，最后求解 Math.abs(A-B)，即为两队能力值之差。
 * 因此，有多少个 10 选 5 组合，就有多少组能力值之差，我们取其中最小的即为题解.
 *
 */
public class _012_篮球比赛 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            getMinStrength(sc.nextLine());
        }

    }

    public static void getMinStrength(String strength){
        int[] arr = Arrays.stream(strength.split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        ArrayList<Integer> res = new ArrayList<>();
        dfs(arr,0,0,0,res);
        int sum = Arrays.stream(arr).reduce(Integer::sum).orElse(0);
        // 某队实力为 subSum，则另一队实力为 sum - subSum，则两队实力差为 abs((sum - subSum) - subSum)，先求最小实力差
        int result = Integer.MAX_VALUE;
        for (Integer re : res) {
            result = Math.min(result,Math.abs(sum - 2* re));
        }
        System.out.println(result);

    }

    // 求解去重组合

    /**
     *  排列组合的所有可能
     * @param arr
     * @param index
     * @param level
     * @param sum
     * @param res
     */
    public static void dfs(int[] arr, int index, int level, int sum, ArrayList<Integer> res) {
        if (level == 5) { res.add(sum); return;
        }
        for (int i = index; i < 10; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue; // arr 已经升序，这里进行树层去重
            }
             dfs(arr, i + 1, level + 1, sum + arr[i], res);
        }
    }


}
