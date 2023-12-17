package HW;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 小华负责公司 知识图谱 产品，现在要通过新词挖掘完善知识图谱
 * 新词挖掘: 给出一个待挖掘问题内容字符串 Content 和一个词的字符串 word，找到
 * content 中所有 word 的新词
 * .新词: 使用词 word 的字符排列形成的字符串 请帮小华实现新词挖掘，返回发现的新词的数量。
 * 输入描述 第一行输入为待挖掘的文本内容 content; 第二行输入为词 word;
 * 输出描述
 * 在 content 中找到的所有 word 的新词的数量 备注
 * 。0≤content 的长度≤10000000
 * 。1 ≤word 的长度≤2000
 * 用例
 *  输入：qweebaewqd
 *      qwe
 *  输出：2
 *
 *  输入：abab
 *      ab
 *  输出：3
 */
public class _005_识图谱新词挖掘 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String content = sc.nextLine();
        String word = sc.nextLine();
        System.out.println(findNumber(content,word));
    }

    public static int findNumber(String content,String word){
        if(content.length() < word.length()){
            return  0;
        }
        int number = 0;

        Map<Character,Integer> countMap = new HashMap<>(word.length());
        for (int i = 0; i < word.length(); i++) {
            countMap.put(word.charAt(i), countMap.getOrDefault(word.charAt(i),0)+1);
        }
        int unMatchCount = word.length();

        for (int i = 0; i < word.length(); i++) {
            Character c = content.charAt(i);
            if(countMap.containsKey(c)){
                if(countMap.get(c) > 0){
                    unMatchCount--;
                }
            }
        }
        if(unMatchCount == 0){
            number++;
        }
        int maxSize = content.length() - word.length();
        for (int i = 1; i < maxSize; i++) {
            Character removeC = content.charAt(i-1);
            Character addC = content.charAt(i+word.length() -1);
            if(countMap.containsKey(removeC)){
               if(countMap.get(removeC) >=0 ){
                   unMatchCount++;
               }
               countMap.put(removeC,countMap.get(removeC)+1);
            }

            if(countMap.containsKey(addC)){
                if(countMap.get(addC) > 0){
                    unMatchCount--;
                }
                countMap.put(addC,countMap.get(addC) -1);
            }

            if(unMatchCount == 0){
                number++;
            }
        }


        return number;
    }
}
