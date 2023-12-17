package HW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 140. 单词拆分 II
 *
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 *
 *
 *
 * 示例 1：
 *
 * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出:["cats and dog","cat sand dog"]
 * 示例 2：
 *
 * 输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * 输出:[]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中所有字符串都 不同
 */
public class _008_单词拆分_二 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(10);
        list.add(2,"1");
        System.out.println(list.get(0));

        /**
        float a = 3.55f;
        float b  = 5.33f;
        float c = a/b;
        System.out.println("float c="+ (3.99/5.33));
        System.out.println("float a/b="+a/b);
        System.out.println(String.format("float c = %.18f",c));

        double aa = 2.0;
        double bb  = 1.0;
        double cc = aa/bb;
        System.out.println("double cc="+cc);
        System.out.println("double aa/bb="+aa/bb);
        System.out.println(String.format("double cc = %.18f",cc));


        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            List<String> wordDict = Arrays.asList(sc.nextLine().split(" "));
            System.out.println(wordBreak(s,wordDict));
        }
         **/
    }

    /**
     * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
     * 输出:["cats and dog","cat sand dog"]
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String> wordBreak(String s,List<String> wordDict){
        Map<Integer,List<List<String>>> map = new HashMap<Integer,List<List<String>>>();
        List<List<String>> wordBreaks = backTrack(s,s.length(),new HashSet<String>(wordDict),0,map);
        List<String> breakList = new LinkedList<>();
        for(List<String> wordBreak:wordBreaks){
            breakList.add(String.join(" ",wordBreak));
        }
        return breakList;
    }

    public static List<List<String>> backTrack(String s,int length,Set<String> wordSet,int index,Map<Integer,List<List<String>>> map){
        if(!map.containsKey(index)){
            List<List<String>> wordBreaks = new LinkedList<List<String>>();
            if(index == length){
                wordBreaks.add(new LinkedList<>());
            }
            for (int i = index +1; i <= length ; i++) {
                String word = s.substring(index,i);
                if(wordSet.contains(word)){
                    List<List<String>> nextWordBreaks = backTrack(s,length,wordSet,i,map);
                    for (List<String> nextWordBreak : nextWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<>(nextWordBreak);
                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index,wordBreaks);
        }
        return map.get(index);
    }
}
