package 简单;

import java.util.Scanner;

/**
 * 描述
 * Jessi初学英语，为了快速读出一串数字，编写程序将数字转换成英文：
 *
 * 具体规则如下:
 * 1.在英语读法中三位数字看成一整体，后面再加一个计数单位。从最右边往左数，三位一单位，例如12,345 等
 * 2.每三位数后记得带上计数单位 分别是thousand, million, billion.
 * 3.公式：百万以下千以上的数 X thousand X, 10亿以下百万以上的数：X million X thousand X, 10 亿以上的数：X billion X million X thousand X.
 * 每个X分别代表三位数或两位数或一位数。
 * 4.在英式英语中百位数和十位数之间要加and，美式英语中则会省略，我们这个题目采用加上and，百分位为零的话，这道题目我们省略and
 *
 * 下面再看几个数字例句：
 * 22: twenty two
 * 100:  one hundred
 * 145:  one hundred and forty five
 * 1,234:  one thousand two hundred and thirty four
 * 8,088:  eight thousand (and) eighty eight (注:这个and可加可不加，这个题目我们选择不加)
 * 486,669:  four hundred and eighty six thousand six hundred and sixty nine
 * 1,652,510:  one million six hundred and fifty two thousand five hundred and ten
 *
 * 说明：
 * 数字为正整数，不考虑小数，转化结果为英文小写；
 * 保证输入的数据合法
 * 关键字提示：and，billion，million，thousand，hundred。
 *
 * 数据范围：1≤n≤2000000
 *
 *
 * 输入描述：
 * 输入一个long型整数
 *
 * 输出描述：
 * 输出相应的英文写法
 *
 * 示例1
 * 输入：
 * 22
 * 输出：
 * twenty two
 */
public class _HJ042_学英语 {

    public static  String[] smallNum = {"zero", "one","two","three","four","five","six",
                                        "seven","eight","nine","ten","eleven","twelve",
    "thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen","twenty"};
    public static String[] bigNum = {"zero","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
    public static String[] unit = {"","thousand","million","billion"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            solution(sc.nextLine());
        }
    }

    public static void solution(String str) {
        int index = str.length() / 3;
        int lastCount = str.length() % 3;
        if(lastCount > 0){
            index++;
        }
        String[] nameList = new  String[index];

        for (int i = 0; i < index; i++) {
            String numberStr = str.substring(Math.max(0,str.length()  - 3 - 3*i),str.length() - 3*i);
            nameList[i] =  getReader(i,numberStr);
        }

        for (int i = nameList.length - 1; i >= 0  ; i--) {
            System.out.print(nameList[i] + " ");
        }

    }

    public static String getReader(int index,String numberStr){
        int number = Integer.parseInt(numberStr);
        StringBuilder result = new StringBuilder();
        if(number >= 100){
            result.append(smallNum[number / 100]).append(" hundred and ");
        }

        int small = number % 100;
        if ( small <= 20){
            result.append(smallNum[small]);
        }else {
            result.append(bigNum[small / 10]);
            if ( small % 10 >= 1){
                result.append(smallNum[small %10]);
            }
        }
        result.append(" " + unit[index]);
        return small == 0 ?result.toString().replace("and ",""):result.toString();
    }

}
