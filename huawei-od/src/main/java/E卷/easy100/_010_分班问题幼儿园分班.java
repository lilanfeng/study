package E卷.easy100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _010_分班问题幼儿园分班 {

    /**
     * 幼儿园分班问题
     * 题目描述
     * 儿园两个班的小朋友在排队时混在了一起，每位小朋友都知道自己是否与前面一位小朋友同班，请你帮忙把同班的小朋友找出来。
     * 小朋友的编号是整数，与前一位小朋友同班用Y表示，不同班用N表示。
     *
     * 输入描述
     * 输入为空格分开的小朋友编号和是否同班标志。
     *
     * 比如：6/N 2/Y 3/N 4/Y，表示4位小朋友，2和6同班，3和2不同班，4和3同班。
     *
     * 其中，小朋友总数不超过999，每个小朋友编号大于0，小于等于999。
     *
     * 不考虑输入格式错误问题。
     *
     * 输出描述
     * 输出为两行，每一行记录一个班小朋友的编号，编号用空格分开，且：
     *
     * 编号需按照大小升序排列，分班记录中第一个编号小的排在第一行。
     * 若只有一个班的小朋友，第二行为空行。
     * 若输入不符合要求，则直接输出字符串ERROR。
     * 示例1
     * 输入
     *
     * 1/N 2/Y 3/N 4/Y
     * 1
     * 输出
     * 1 2
     * 3 4
     * 说明
     *
     * 2的同班标记为Y，因此和1同班。
     * 3的同班标记为N，因此和1、2不同班。
     * 4的同班标记为Y，因此和3同班。
     * 所以1、2同班，3、4同班，输出为
     * 1 2
     * 3 4
     *
     * 示例2
     * 输入
     *
     * 1/N 2/Y 3/N 4/Y 5/Y
     * 输出
     *
     * 1 2
     * 3 4 5
     * 说明
     *
     * 无
     *
     * 解题思路
     * 题目的要求是将一组小朋友按班级进行分类。输入由小朋友的编号和他们是否与前一位小朋友同班的标志组成。任务是根据这些标志将同班的小朋友归类并输出，遵循以下规则：
     *
     * 输入是小朋友编号与他们是否与前一位小朋友同班的标志，用空格分隔。编号后面跟随一个标志：
     *
     * Y：表示与前一个小朋友同班。
     * N：表示与前一个小朋友不同班。
     * 根据这些标志，小朋友们要被分成不同的班级。
     *
     * 示例分析
     * 示例 1
     * 输入：
     * 1/N 2/Y 3/N 4/Y
     * 解释：
     *
     * 小朋友1是第一个，所以他是班级1的第一个成员。
     * 小朋友2与小朋友1同班（因为标志是Y），因此小朋友1和2同班，形成班级1。
     * 小朋友3与小朋友2不同班（标志是N），因此小朋友3在另一个班级，形成班级2。
     * 小朋友4与小朋友3同班（标志是Y），所以小朋友3和4同班，属于班级2。
     * 输出：
     * 1 2
     * 3 4
     * 示例 2
     * 输入：
     *
     * 1/N 2/Y 3/N 4/Y 5/Y
     * 解释：
     *
     * 小朋友1和2同班，属于班级1。
     * 小朋友3和4同班，属于班级2。
     * 小朋友5与4同班，属于班级2。
     * 输出：
     *
     * 1 2
     * 3 4 5
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();
            System.out.println(findClass(input));
        }
    }

    /**
     * 找出班级
     * @param input
     * @return
     */
    public static String findClass(String input){
        String[] inputArray = input.split(" ");
        List<String> class1 = new ArrayList<>();
        List<String> class2 = new ArrayList<>();
        int classIndex = 1;
        class1.add(inputArray[0].split("/")[0]);
        for (int i = 1; i < inputArray.length; i++) {
            String[] split= inputArray[i].split("/");
            String number = split[0];
            String flag = split[1];
            if ("Y".equals(flag)) {
                if (classIndex == 1) {
                    class1.add(number);
                } else {
                    class2.add(number);
                }
            } else {
                if (classIndex == 1) {
                    class2.add(number);
                    classIndex = 2;
                } else {
                    class1.add(number);
                    classIndex = 1;
                }
            }
        }
        Collections.sort(class1,(a,b)->Integer.parseInt(a) - Integer.parseInt(b));
        Collections.sort(class2,(a,b)->Integer.parseInt(a) - Integer.parseInt(b));
        return String.join(" ",class1)+"\n" + String.join(" ",class2);
    }
}
