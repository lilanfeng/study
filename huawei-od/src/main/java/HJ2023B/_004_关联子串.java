package HJ2023B;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  题目描述
 *  给定两个字符串str1和str2，如果字符串str1中的字符，经过排列组合后的字符串中，只要有一个字符串是str2的子串，则认为str1是str2的关联子串
 *  若str1是str2的关联子串，请返回子串在str2的起始位置；
 *  若不是关联子串，则返回-1。
 *  输入描述：
 *   输入两个字符串，分别为题目中描述的str1，str2
 *
 *  输出描述：
 *  若str1是str2的关联子串，请返回子串在str2的起始位置；
 *  若不是关联子串，则返回-1。
 *  若str2中有多个str1的组合子串，请返回最小的起始位置
 *
 *  备注：
 *  输入的字符串只包含小写字母
 *  两个字符串的长度范围【1，100000】之间
 *  用例：
 *  输入：
 *  abc efghicbaii
 *
 *  输出：
 *  5
 *
 *  输入：
 *  abc efghiccaii
 *
 *  输出：
 *     -1
 *
 */
public class _004_关联子串 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        List<String> wordList = permutation(str1);

        int minIndex = -1;
        for (int i = 0; i < wordList.size(); i++) {
            if(str2.contains(wordList.get(i))){
                int currentIndex = str2.indexOf(wordList.get(i));
                if(minIndex < 0){
                    minIndex = currentIndex;
                }
                minIndex = Math.min(minIndex,currentIndex);
            }
        }
        System.out.println(minIndex);
    }

    /**
     * 返回字符串的所有排列的类别情况数据
     *   从字符串中选择一个字符作为排列的第一个字符，然后对剩余的字符进行全排列，如此递归处理，从而得到所有字符的全排列。
     * @param s
     * @return
     */
    public static List<String> permutation(String s){
        List<String> res = new ArrayList<>();
        if(s == null ){
            return  null;
        }
        if(s.isEmpty()){
            res.add("");
            return res;
        }
        for (int i = 0; i < s.length(); i++) {
            //获取其中一个字符串作为首位
            char c = s.charAt(i);
            String start = s.substring(0,i);
            String end = s.substring(i + 1);

            //得到剩余字母的所有排列情况
            List<String> words = permutation(start + end);

            //首位字符跟剩余字母的所有排列情况进行组合
            if(words != null && words.size() > 0){
                for (String word:words) {
                    String newStr = c + word;
                    res.add(newStr);
                }
            }
        }
        return res;
    }

}
