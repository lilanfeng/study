package HJ2023B;

import java.util.Scanner;

/**
 * 题目描述
 * 输入一个单词前缀和一个字典，输出包含该前缀的单词
 *
 * 输入描述
 * 单词前缀+字典长度+字典
 * 字典是一个有序单词数组
 * 输入输出都是小写
 *
 * 输出描述
 * 所有包含该前缀的单词，多个单词换行输出
 *
 * 若没有则返回-1
 *
 * 用例
 * 输入	b 3 a b c
 * 输出	b
 * 说明	无
 * 输入	abc 4 a ab abc abcd
 * 输出	abcabcd
 * 说明	无
 * 输入	a 3 b c d
 * 输出	-1
 * 说明	无
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132328535
 */
public class _018_查字典 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            findDic(sc.nextLine());
        }
    }

    public static void findDic(String str){
        String[] dicList = str.split(" ");
        if(dicList.length <= 0){
            System.out.println(-1);
            return;
        }
        String key  = dicList[0];
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < dicList.length ; i++) {
            if(dicList[i].startsWith(key)){
                result.append(dicList[i]);
            }
        }
        if(result.length() <= 0){
            result.append(-1);
        }
        System.out.println(result.toString());
    }

}
