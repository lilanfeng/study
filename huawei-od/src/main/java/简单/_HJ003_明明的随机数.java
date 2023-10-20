package 简单;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 描述
 * 明明生成了
 * �
 * N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 *
 * 数据范围：
 * 1
 * ≤
 * �
 * ≤
 * 1000
 *
 * 1≤n≤1000  ，输入的数字大小满足
 * 1
 * ≤
 * �
 * �
 * �
 * ≤
 * 500
 *
 * 1≤val≤500
 * 输入描述：
 * 第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
 * 输出描述：
 * 输出多行，表示输入数据处理后的结果
 *
 * 示例1
 * 输入：
 * 3
 * 2
 * 2
 * 1
 * 复制
 * 输出：
 * 1
 * 2
 * 复制
 * 说明：
 * 输入解释：
 * 第一个数字是3，也即这个小样例的N=3，说明用计算机生成了3个1到500之间的随机整数，接下来每行一个随机数字，共3行，也即这3个随机数字为：
 * 2
 * 2
 * 1
 * 所以样例的输出为：
 * 1
 * 2
 */
public class _HJ003_明明的随机数 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int number = 0;
        int count = 0;
        List<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) {
            number++;
            int a = in.nextInt();
            if (number == 1) {
                count = a;
            } else {
                if ((number - 1) < count) {
                    list.add(a);
                } else if ((number - 1) == count) {
                    list.add(a);
                    break;
                }
            }

        }
        solution(list);

    }

    public static void solution(List<Integer> list){
        if(list == null && list.size() <= 0){
            return;
        }
        //Collection<Integer> setArr = new Set<Integer>();
        Map<String,Integer> existMap = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            Integer temp = list.get(i);
            if(!existMap.containsKey(String.valueOf(temp))){
                existMap.put(String.valueOf(temp),temp);
            }
        }
        List<Integer> tempList = new ArrayList<>(existMap.size());
        //tempList.stream().distinct().sorted();
        existMap.forEach((key,value)->{
            tempList.add(value);
        });
        int[] unSortArr = tempList.stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(unSortArr);
        for (int i = 0; i < unSortArr.length; i++) {
            System.out.println(unSortArr[i]);
        }
    }

}
