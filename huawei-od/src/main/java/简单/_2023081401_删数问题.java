package 简单;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 1.
 * 删数
 * 有一个数组 a[N] 顺序存放 0 ~ N-1 ，要求每隔两个数删掉一个数，到末尾时循环至开头继续进行，求最后一个被删掉的数的原始下标位置。以 8 个数 (N=7) 为例 :｛ 0，1，2，3，4，5，6，7 ｝，0 -> 1
 * -> 2 (删除) -> 3 -> 4 -> 5 (删除) -> 6 -> 7 -> 0 (删除),如此循环直到最后一个数被删除。
 *
 * 数据范围：
 * 1
 * ≤
 * �
 * ≤
 * 1000
 *
 * 1≤n≤1000
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 * 输入描述：
 * 每组数据为一行一个整数n(小于等于1000)，为数组成员数
 * 输出描述：
 * 一行输出最后一个被删掉的数的原始下标位置。
 * 示例1
 * 输入例子：
 * 8
 * 输出例子：
 * 6
 * 示例2
 * 输入例子：
 * 1
 * 输出例子：
 * 0
 */
public class _2023081401_删数问题 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()){
            int size = in.nextInt();
            solution(size);
        }
    }


    /**
     *
     * @param size
     */
    public static void solution(int size){
        LinkedList<Integer> linkedList = new LinkedList<>();
        List<Boolean> booleanList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
            booleanList.add(true);
        }

        int count = 0;
        while (linkedList.size() > 1){
            for (int j = 0; j < size; j++) {
                if(booleanList.get(j)) {
                    count++;
                    if(count == 3){
                        booleanList.set(j,false);
                        count = 0;
                        linkedList.remove(Integer.valueOf(j));
                        if(linkedList.size() == 1){
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(linkedList.get(0));
    }


}
