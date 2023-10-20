package HJ2023B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 停车场有一横排车位，0代表没有停车，1代表有车。至少停了一辆车在车位上，也至少有一个空位没有停车。
 * 为了防剐蹭，需为停车人找到一个车位，使得距停车人的车最近的车辆的距离是最大的，返回此时的最大距离。
 *
 * 输入描述
 * 一个用半角逗号分割的停车标识字符串，停车标识为0或1，0为空位，1为已停车。
 * 停车位最多100个。
 * 输出描述
 * 输出一个整数记录最大距离。
 *
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java
 * 使用sacnner/system.out.print()
 *
 * 用例
 * 输入	1,0,0,0,0,1,0,0,1,0,1
 * 输出	2
 * 说明
 * 当车停在第3个位置上时，离其最近的的车距离为2（1到3）。
 *
 * 当车停在第4个位置上时，离其最近的的车距离为2（4到6）。
 *
 * 其他位置距离为1。
 *
 * 因此最大距离为2
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130019456
 */
public class _015_停车场最大距离 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            findLongest(str);
            //findLongest2(str);
        }
    }

    public static void findLongest(String str){
        String[] spaceArr = str.split(",");
        int left = 1;
        int right = 1;
        int max = 0;
        for (int i = 0; i < spaceArr.length; i++) {
            if("1".equals(spaceArr[i])){
                continue;
            }else {
                while (i - left >= 0){
                    if( "1".equals(spaceArr[i-left])){
                        break;
                    }
                    left++;
                }
                while (i+right < spaceArr.length){
                    if( "1".equals(spaceArr[i+right])){
                        break;
                    }
                    right++;
                }
                int min = Math.min(left,right);
                if (i == 0) {
                    min = right;
                } else if (i == spaceArr.length - 1) {
                    min = left;
                }
                max = Math.max(max,min);
                left = 1;
                right = 1;
            }
        }
        System.out.println(max);
    }

    public static void findLongest2(String str){
        List<String> inputNumList = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') {
                inputNumList.add(temp);
                temp = "";
            } else {
                temp += str.charAt(i);
            }
        }
        inputNumList.add(temp);

        String lineNum = "";
        for (int i = 0; i < inputNumList.size(); i++) {
            lineNum += inputNumList.get(i);
        }
        List<String> lineNumList = new ArrayList<>();
        temp = "";
        for (int i = 0; i < lineNum.length(); i++) {
            if (lineNum.charAt(i) == '1') {
                lineNumList.add(temp);
                temp = "";
            } else {
                temp += lineNum.charAt(i);
            }
        }
        lineNumList.add(temp);

        List<Integer> res = new ArrayList<>();
        res.add(lineNumList.get(0).length());
        res.add(lineNumList.get(lineNumList.size() - 1).length());
        int maxLen = 0;
        for (int i = 0; i < lineNumList.size(); i++) {
            maxLen = Math.max(maxLen, lineNumList.get(i).length());
        }
        res.add((maxLen + 1) / 2);

        int num = Collections.max(res);

        System.out.println(num);
    }
}
