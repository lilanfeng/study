package E卷.hard200;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/8 17:34
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _015_书籍叠放 {
    /**
     * 题目描述
     * 书籍的长、宽都是整数对应(l,w)。如果书A的长宽度都比B长宽大时，则允许将B排列放在A上面。
     * 现在有一组规格的书籍，书籍叠放时要求书籍不能做旋转，请计算最多能有多少个规格书籍能叠放在一起。
     * 输入描述
     * 输入：books = [[20,16],[15,11],[10,10],[9,10]]
     *
     * 说明：总共4本书籍，第一本长度为20宽度为16；第二本书长度为15宽度为11，依次类推，最后一本书长度为9宽度为10.
     *
     * 输出描述
     * 输出：3
     *
     * 说明: 最多3个规格的书籍可以叠放到一起, 从下到上依次为: [20,16],[15,11],[10,10]
     *
     * 示例1
     * 输入
     * [[20,16],[15,11],[10,10],[9,10]]
     * 输出
     * 3
     * 说明
     */
    public static void main(String[] args) {
        //int[][] books = {{20,16},{15,11},{10,10},{9,10}};
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.replace("[[","").replace("]]","").replace("],["," ");
        String[] books = input.split(" ");
        System.out.println(getMaxBookCount(books));

    }

    /**
     *  待完成
     * @param books
     * @return
     */
    public static int getMaxBookCount(String[] books) {
        int[][] bookArr = new int[books.length][2];
        for (int i = 0; i < books.length; i++) {
            String[] split = books[i].split(",");
            bookArr[i][0] = Integer.parseInt(split[0]);
            bookArr[i][1] = Integer.parseInt(split[1]);
        }

        Arrays.sort(bookArr, Comparator.comparingInt((int[] o) -> o[0])
                .thenComparingInt((int[] o) -> o[1]));
        int[] widthArr = Arrays.stream(bookArr).mapToInt(book->book[1]).toArray();

        // 定义一个数组用于保存最长不下降子序列（LIS）
        int[] list = new int[widthArr.length];
        int length = 0;

        // 遍历所有书籍的宽度，使用二分查找优化 LIS 计算
        for (int i = 0; i < widthArr.length; i++) {
            int max = Arrays.binarySearch(list, 0, length, widthArr[i]);
            if (max < 0) {
                max = -max - 1;
            }
            list[max] = widthArr[i];
            if (max == length) {
                length++;
            }
        }
        return 0;
    }

}
