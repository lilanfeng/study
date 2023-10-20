package 简单;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 描述
 * 数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
 *
 *
 * 提示:
 * 0 <= index <= 11111111
 * 1 <= value <= 100000
 *
 * 输入描述：
 * 先输入键值对的个数n（1 <= n <= 500）
 * 接下来n行每行输入成对的index和value值，以空格隔开
 *
 * 输出描述：
 * 输出合并后的键值对（多行）
 *
 * 示例1
 * 输入：
 * 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 复制
 * 输出：
 * 0 3
 * 1 2
 * 3 4
 * 示例2
 * 输入：
 * 3
 * 0 1
 * 0 2
 * 8 9
 * 输出：
 * 0 3
 * 8 9
 */
public class _HJ008_合并表记录 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TreeMap<Integer,Integer> map = new TreeMap<>();
        while (sc.hasNextInt()){
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                map.put(a,map.getOrDefault(a,0)+b);
            }
        }
        map.forEach((integer, integer2) -> {
            System.out.println(integer + " " + integer2);
        });

    }

    public static void solution() {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        boolean isFirst = true;
        int number = 0;
        Map<String, Integer> tableMap = new HashMap<>();
        List<String> indexList = new ArrayList<>();
        while (sc.hasNextLine()) {
            if (isFirst) {
                count = Integer.parseInt(sc.nextLine());
                isFirst = false;
            } else {

                String target = sc.nextLine();
                if (target.indexOf(" ") < 0) {
                    continue;
                }

                String index = target.substring(0, target.indexOf(" "));
                int value = Integer.parseInt(target.substring(target.indexOf(" ") + 1));
                if (tableMap.containsKey(index)) {
                    value += tableMap.get(index);
                    tableMap.put(index, value);
                } else {
                    indexList.add(index);

                }
                tableMap.put(index, value);
                number++;
                if (number == count) {
                    isFirst = true;
                    count = 0;
                    number = 0;
                    indexList.forEach(temp -> {
                        System.out.println(temp + " " + tableMap.get(temp));
                    });
                }
            }
        }
    }


}
