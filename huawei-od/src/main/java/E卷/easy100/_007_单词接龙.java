package E卷.easy100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _007_单词接龙 {
    /**
     * 单词接龙的规则是：
     * 可用于接龙的单词首字母必须要前一个单词的尾字母相同；
     * 当存在多个首字母相同的单词时，取长度最长的单词，如果长度也相等，则取字典序最小的单词；已经参与接龙的单词不能重复使用。
     * 现给定一组全部由小写字母组成单词数组，并指定其中的一个单词作为起始单词，进行单词接龙，
     * 请输出最长的单词串，单词串是单词拼接而成，中间没有空格。
     *
     * 输入描述
     * 输入的第一行为一个非负整数，表示起始单词在数组中的索引K，0 <= K < N ；
     * 输入的第二行为一个非负整数，表示单词的个数N；
     * 接下来的N行，分别表示单词数组中的单词。
     * 备注：
     * 单词个数N的取值范围为[1, 20]；
     * 单个单词的长度的取值范围为[1, 30]；
     * 输出描述
     * 输出一个字符串，表示最终拼接的单词串。
     * 示例1
     * 输入
     * 0
     * 6
     * word
     * dd
     * da
     * dc
     * dword
     * d
     * 输出
     * worddwordda
     * 说明
     * 先确定起始单词word，再接以d开头的且长度最长的单词dword，剩余以d开头且长度最长的有dd、da、dc，则取字典序最小的da，所以最后输出worddwordda。
     *
     * 示例2
     * 输入
     * 4
     * 6
     * word
     * dd
     * da
     * dc
     * dword
     * d
     * 输出
     * dwordda
     * 说明
     * 先确定起始单词dword，剩余以d开头且长度最长的有dd、da、dc，则取字典序最小的da，所以最后输出dwordda。
     * 解题思路
     * 接龙规则：
     * 下一个接龙的单词必须以前一个单词的尾字母开头。
     * 当有多个符合条件的单词时：
     * 优先选择长度最长的单词；
     * 如果长度相等，选择字典序最小的单词。
     * 已经使用的单词不能重复使用。
     * 解题步骤
     * 确定起始单词：从输入的 K 索引中找到起始单词。
     * 根据接龙规则选择后续单词：
     * 选择以当前单词尾字母开头的单词。
     * 先选长度最长的单词；若长度相等，选字典序最小的单词。
     * 将这个单词接到当前的结果串中，重复这个过程，直到没有可以接龙的单词为止。
     * 输出结果：将最终拼接的单词串输出。
     * 示例解析
     * 示例1：
     *
     * 输入：
     * 0
     * 6
     * word
     * dd
     * da
     * dc
     * dword
     * d
     * 步骤：
     * 起始单词为 word，其尾字母是 d。
     * 从剩下的单词中，选择以 d 开头的且长度最长的单词，得到 dword。
     * 再次寻找以 d 开头的单词，此时剩下的有 dd、da、dc，长度相等，选字典序最小的 da。
     * 最终输出为：worddwordda。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            int startIndex = Integer.parseInt(scanner.nextLine());
            int wordCount = Integer.parseInt(scanner.nextLine());
            String[] words = new String[wordCount];
            for (int i = 0; i < wordCount; i++) {
                words[i] = scanner.nextLine();
            }
            System.out.println(findLongestSequence(words, startIndex));
        }
        scanner.close();
    }

    /**
     *  寻找拼接接龙的单词
     * @param words
     * @param startIndex
     * @return
     */
    public static String findLongestSequence(String[] words, int startIndex) {
        StringBuilder sb = new StringBuilder();
        HashSet<String> usedWordSet = new HashSet<>();
        String findWord = words[startIndex];
        while (findWord != null && !findWord.isEmpty()) {
            usedWordSet.add(findWord);
            sb.append(findWord);
            //todo 优化 删除已经使用的单词
            findWord = findNextWord(findWord.substring(findWord.length() - 1), words, usedWordSet);
        }
        return sb.toString();
    }

    /**
     * 寻找下一个接龙的单词
     * @param lastWord
     * @param words
     * @param usedWordSet
     * @return
     */
    private static String findNextWord(String lastWord, String[] words,HashSet<String> usedWordSet) {
        String nextWord = null;
        for (String word : words) {
            if (!word.startsWith(lastWord) || usedWordSet.contains(word)) {
                continue;
            }
            if (nextWord == null) {
                nextWord = word;
                continue;
            }
            if (word.length() > nextWord.length()) {
                nextWord = word;
            } else if (word.length() == nextWord.length() && word.compareToIgnoreCase(nextWord) < 0) {
                nextWord = word;
            }
        }
        return nextWord;
    }


}
