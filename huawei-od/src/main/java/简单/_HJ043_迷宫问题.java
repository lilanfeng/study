package 简单;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 描述
 * 定义一个二维数组 N*M ，如 5 × 5 数组下所示：
 *
 *
 * int maze[5][5] = {
 * 0, 1, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 1, 0,
 * };
 *
 *
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。
 *
 *
 * 数据范围：
 * 2≤n,m≤10  ， 输入的内容只包含 0≤val≤1
 *
 * 输入描述：
 * 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
 *
 * 输出描述：
 * 左上角到右下角的最短路径，格式如样例所示。
 *
 * 示例1
 * 输入：
 * 5 5
 * 0 1 0 0 0
 * 0 1 1 1 0
 * 0 0 0 0 0
 * 0 1 1 1 0
 * 0 0 0 1 0
 * 输出：
 * (0,0)
 * (1,0)
 * (2,0)
 * (2,1)
 * (2,2)
 * (2,3)
 * (2,4)
 * (3,4)
 * (4,4)
 * 示例2
 * 输入：
 * 5 5
 * 0 1 0 0 0
 * 0 1 0 1 0
 * 0 0 0 0 1
 * 0 1 1 1 0
 * 0 0 0 0 0
 * 输出：
 * (0,0)
 * (1,0)
 * (2,0)
 * (3,0)
 * (4,0)
 * (4,1)
 * (4,2)
 * (4,3)
 * (4,4)

 * 说明：
 * 注意：不能斜着走！！
 */
public class _HJ043_迷宫问题 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        List<Pos> path = new ArrayList<>();
        dfs(map,0,0,path);
       for(Pos pos:path){
           System.out.println("(" + pos.x + "," + pos.y + ")");
       }

    }

    public static boolean dfs(int[][] map,int x,int y,List<Pos> path){
        path.add(new Pos(x,y));
        map[x][y] = 1;
        if(x == map.length -1 && y == map[0].length -1){
            return true;
        }
        if(x + 1 < map.length && map[x+1][y] == 0){
            if(dfs(map,x+1,y,path)) {
                return true;
            }
        }

        if(y + 1 < map[0].length && map[x][y+1] == 0){
            if(dfs(map,x,y+1,path)) {
                return true;
            }
        }
        if(x - 1 > -1 && map[x -1][y] == 0){
            if(dfs(map,x-1,y,path)) {
                return true;
            }
        }

        if(y -1 > -1  && map[x][y-1] == 0){
            if(dfs(map,x,y-1,path)) {
                return true;
            }
        }
        path.remove(path.size() -1);
        map[x][y] = 0;
        return false;

    }


    public static class Pos{
        int x;
        int y;

        public Pos(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}
