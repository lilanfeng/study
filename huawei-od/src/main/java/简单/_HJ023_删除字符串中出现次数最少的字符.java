package 简单;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 描述
 * 实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 *
 * 数据范围：输入的字符串长度满足
 * 1
 * ≤
 * �
 * ≤
 * 20
 *
 * 1≤n≤20  ，保证输入的字符串中仅出现小写字母
 * 输入描述：
 * 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
 *
 * 输出描述：
 * 删除字符串中出现次数最少的字符后的字符串。
 *
 * 示例1
 * 输入：
 * aabcddd
 * 复制
 * 输出：
 * aaddd
 */
public class _HJ023_删除字符串中出现次数最少的字符 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            solution(sc.nextLine());
        }
    }

    public static void solution(String source) {

        Map<String,Integer> countMap = new HashMap<>(source.length());
        for(char c:source.toCharArray()){
            String key = String.valueOf(c);
            countMap.put(key,countMap.getOrDefault(key,0) + 1);
        }

        Integer minCount = Collections.min(countMap.values());
        for(String key:countMap.keySet()){
            if(countMap.get(key).equals(minCount)){
               source = source.replace(key,"");
            }
        }

        System.out.println(source);
    }
}
