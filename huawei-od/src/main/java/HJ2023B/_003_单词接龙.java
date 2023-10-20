package HJ2023B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 单词接龙的规则是：
 *  1，可用于接龙的单词首字母必须下要前一个单词的尾字母相同；
 *  2，当存在多个首字母相同的单词时，取长度最长的单词，如果长度也相等，则取字典序最小的单词；已经参与接龙的单词不能重复使用。
 *  3，现给定一组全部由小写字母组成单词数组，并指定其中的一个单词作为起始单词，进行单词接龙；
 *  4，请输出最长的单词串，单词串是单词拼接而成，中间没有空格
 *
 *  输入描述：
 *   1，输入的第一行为一个非负整数，表示起始单词在数组中的索引K， 0《=k《N
 *   2，输入的第二行为一个非负整数，表示单词的个数N
 *   3，接下来的N行，分别表示单词数组中的单词。
 *   备注：
 *      单词个数N的取值范围为【1，20】
 *      单个单词的长度的取值范围为【1，30】
 *
 *  输出描述：
 *  输出一个字符串，表示最终平均的单词串
 *  用例：
 *  输入：0
 *       6
 *       word
 *       dd
 *       da
 *       dc
 *       dword
 *       d
 *   输出：
 *   worddwordda
 *    说明： 先确定起始单词word，再接以d开头的且最长的单词dword，剩余以d开头且长度最长的有dd，da，dc，则取字典序最小的da，所以输出如下
 */
public class _003_单词接龙 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = Integer.parseInt(sc.nextLine());
        int number = Integer.parseInt(sc.nextLine());
        String[] strArr = new String[number];
        for (int i = 0; i < number; i++) {
            strArr[i] = sc.nextLine();
        }
        solution(start,strArr);
    }

    public static void solution(int start,String[] strArr){
        Map<String, List<String>> startMap = new HashMap<>();
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if(i == start){
                resultBuilder.append(strArr[i]);
            }else{
                String key = String.valueOf(strArr[i].charAt(0));
                List<String> findList = null;
                if(startMap.containsKey(key)){
                    findList = startMap.get(key);
                }else {
                    findList = new ArrayList<>();
                }
                findList.add(strArr[i]);
                startMap.put(key,findList);
            }
        }
        findWord(resultBuilder,startMap);
        System.out.println(resultBuilder.toString());
    }

    public static void findWord(StringBuilder result,Map<String,List<String>> startMap){
        String key = String.valueOf(result.toString().charAt(result.toString().length() -1));
        if(!startMap.containsKey(key)){
           return ;
        }
        List<String> tempList = startMap.get(key);
        if(tempList == null || tempList.isEmpty()){
            return;
        }

        int maxLength = 0;
        String findWord = "";
        for(String word:tempList){

            if(word.length() > maxLength ||
                    (word.length() == maxLength && word.compareTo(findWord) < 0)
            ){
                maxLength = word.length();
                findWord = word;
            }
        }
        if(findWord.isEmpty()){
            return;
        }
        result.append(findWord);
        //使用过的单词需要剔除
        tempList.remove(findWord);
        startMap.put(key,tempList);
        findWord(result,startMap);
    }



}
