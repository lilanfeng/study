package HJ2023B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 题目描述:文件目录大小
 * 一个文件目录的数据格式为：目录id，本目录中文件大小，(子目录id列表）。
 * 其中目录id全局唯一，取值范围[1, 200]，本目录中文件大小范围[1, 1000]，子目录id列表个数[0,10]例如 : 1 20 (2,3) 表示目录1中文件总大小是20，有两个子目录，id分别是2和3
 * 现在输入一个文件系统中所有目录信息，以及待查询的目录 id ，返回这个目录和及该目录所有子目录的大小之和。
 *
 * 输入描述
 * 第一行为两个数字M，N，分别表示目录的个数和待查询的目录id,
 * 1 ≤ M ≤ 100
 * 1 ≤ N ≤ 200
 * 接下来M行，每行为1个目录的数据：
 * 目录id 本目录中文件大小 (子目录id列表)
 * 子目录列表中的子目录id以逗号分隔。
 *
 * 输出描述
 * 待查询目录及其子目录的大小之和
 *
 * 用例1
 * 输入
 *
 * 3 1
 * 3 15 ()
 * 1 20 (2)
 * 2 10 (3)
 * 输出
 *
 * 45
 * 说明
 *
 * 目录1大小为20，包含一个子目录2 (大小为10)，子目录2包含一个子目录3(大小为15)，总的大小为20+10+15=45
 *
 * 用例2
 * 输入
 *
 * 4 2
 * 4 20 ()
 * 5 30 ()
 * 2 10 (4,5)
 * 1 40 ()
 * 输出
 *
 * 60
 * 说明
 *
 * 目录2包含2个子目录4和5，总的大小为10+20+30 = 60
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131692437
 */
public class _023_文件目录大小 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int findId = sc.nextInt();
        Map<Integer,Integer> mapSize = new HashMap<>();
        Map<Integer, List<Integer>> mapChild = new HashMap<>();
        for (int i = 0; i < number; i++) {
            int key = sc.nextInt();
            int size = sc.nextInt();
            String str = sc.next();
            mapSize.put(key,size);
            str = str.replace("(","").replace(")","");
            List<Integer> list = null;
            if(str.isEmpty()){
                list = new ArrayList<>();
            }else {
                list = Arrays.stream(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            }
            mapChild.put(key,list);
        }

        int totalSize = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(findId);
        while (!stack.isEmpty()){
            Integer id = stack.pop();
            totalSize += mapSize.get(id);
            stack.addAll(mapChild.get(id));
        }

        System.out.println(totalSize);
    }
}
