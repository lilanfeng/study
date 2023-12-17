package HW;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * Jungle 生活在美丽的蓝鲸城，大马路都是方方正正，但是每天马路的封闭情况都不一 样地图由以下元素组成:
 * 1)”.”一 空地，可以达到
 * 2)”*”一 路障，不可达到:
 * 3)”S”- Jungle 的家
 * 4)”T”一公司.
 * 其中我们会限制 Jungle 拐弯的次数，同时 Jungle 可以清除给定个数的路障，现在你的
 * 任务是计算 Jungle 是否可以从家里出发到达公司。 输入描述
 * 输入的第一行为两个整数 t,c (0 <=t,c <= 100)代表可以拐的次数，c 代表可以清除的路障 个数。
 * 输入的第二行为两个整数 n,m (1 <=n,m <= 100) ,代表地图的大小。 接下来是 n 行包含 m 个字符的地图。n 和 m 可能不一样大。 我们保证地图里有 S 和 T。
 * 输出描述
 * 输出是否可以从家里出发到达公司，是则输出 YES，不能则输出 NO。 用例
 *  输入：2 0
 *  5 5
 *  ..S..
 *  ***.
 *  T....
 *  ****.
 *  .....
 *  输出：
 *      YES
 *
 * 输入：1 2
 * 5 5
 * .*S*.
 * *****
 * ..*..
 * *****
 * T....
 *
 * 输出：
 *      NO
 */
public class _002_上班之路 {

    static int[][] offsets = {{-1,0,1},{1,0,2},{0,-1,3},{0,1,4}};

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int c = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[][] targetArr = new String[n][m];
        for (int i = 0; i < n; i++) {
            targetArr[i] = sc.next().split("");
        }
        System.out.println(findResult(t,c,n,m,targetArr));
    }

    public static String findResult(int t,int c,int n,int m,String[][] targetArr){

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if("S".equals(targetArr[i][j])){
                    HashSet<Integer> path = new HashSet<>();
                    path.add(i*m+j);
                    return dfs(i,j,0,0,0,path,targetArr,n,m,t,c) ? "YES":"NO";
                }
            }

        }
        return "NO";

    }

    /**
     *
     * @param si 当前位置横坐标
     * @param sj 当前位置纵坐标
     * @param ut  已拐弯次数
     * @param uc  已破壁次数
     * @param lastDirect 上一次运动方向，初始为 0，
     * @param path  行动路径， 用于记录走过的位置，避免走老路
     * @return
     */
    public static boolean dfs(int si, int sj, int ut, int uc, int lastDirect, HashSet<Integer> path,String[][] targetArr,int n,int m,int t,int c){
        if("T".equals(targetArr[si][sj])){
            return true;
        }

        for (int[] offset:offsets) {
            int direct = offset[2];
            int newi = si + offset[0];
            int newj = sj + offset[1];

            boolean flagt = false;
            boolean flagc = false;

            if(newi >= 0 && newi < n && newj >= 0  && newj<m) {
                int pos = newi * m + newj;
                if(path.contains(pos)){
                    continue;
                }

                if(lastDirect != 0 && lastDirect != direct){
                    if(ut  + 1 > t){
                        continue;
                    }
                    flagt = true;
                }
                if("*".equals(targetArr[newi][newj])){
                    if(uc + 1 > c){
                        continue;
                    }
                    flagc = true;
                }

                path.add(pos);

                boolean res = dfs(newi,newj,ut + (flagt ? 1:0),uc + (flagc?1:0),direct,path,targetArr,n,m,t,c);
                if(res){
                    return true;
                }
                path.remove(pos);
            }
        }
        return false;
    }





}
