package E卷.easy100;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _006_磁盘容量排序 {
    /**
     * 题目描述
     * 磁盘的容量单位常用的有 M，G，T 这三个等级，它们之间的换算关系为：
     * 1T = 1024G
     * 1G = 1024M
     * 现在给定 n 块磁盘的容量，请对它们按从小到大的顺序进行稳定排序。
     *
     * 例如给定5块盘的容量：
     *
     * 1T，20M，3G，10G6T，3M12G9M
     *
     * 排序后的结果为：
     *
     * 20M，3G，3M12G9M，1T，10G6T
     *
     * 注意单位可以重复出现，上述 3M12G9M 表示的容量即为：3M+12G+9M，和 12M12G 相等。
     *
     * 输入描述
     * 输入第一行包含一个整数 n，表示磁盘的个数
     * 2 ≤ n ≤ 100
     * 接下的 n 行，每行一个字符串（长度大于2，小于30），表示磁盘的容量，由一个或多个格式为mv的子串组成，其中 m 表示容量大小，v 表示容量单位，例如：20M，1T，30G，10G6T，3M12G9M。
     *
     * 磁盘容量 m 的范围为 1 到 1024 的正整数
     * 容量单位 v 的范围只包含题目中提到的 M，G，T 三种，换算关系如题目描述
     * 输出描述
     * 输出 n 行，表示 n 块磁盘容量排序后的结果。
     *
     * 示例1
     * 输入
     *
     * 3
     * 1G
     * 2G
     * 1024M
     * 输出
     *
     * 1G
     * 1024M
     * 2G
     * 说明
     *
     * 1G和1024M容量相等，稳定排序要求保留它们原来的相对位置，故1G在1024M之前。
     *
     * 示例2
     * 输入
     *
     * 3
     * 2G4M
     * 3M2G
     * 1T
     * 输出
     * 3M2G
     * 2G4M
     * 1T
     * 说明
     *
     * 1T的容量大于2G4M，2G4M的容量大于3M2G。
     *
     * 解题思路
     * 题目描述要求对磁盘容量进行稳定排序，磁盘容量可以用M（兆）、G（千兆）、T（太字节）表示，它们之间的换算关系为：1T = 1024G，1G = 1024M。磁盘的容量以格式为mv的子串表示，其中m表示容量大小，v表示容量单位。
     *
     * 换算规则：
     * 1T = 1024G，1G = 1024M，所以所有磁盘容量可以统一转换为M单位来方便比较。例如：
     * 1T = 1024 * 1024M = 1048576M
     * 1G = 1024M
     * 20M就是20M。
     * 排序要求：
     * 题目要求对这些磁盘容量从小到大排序。
     * 稳定排序：如果两个磁盘的容量大小相同，保持它们原输入中的顺序不变。例如，1G和1024M容量相等，但1G在输入时位于1024M之前，故在输出时1G仍应位于1024M之前。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            String[] strings = new String[n];
            for (int i = 0; i < n; i++) {
                strings[i] = scanner.nextLine();
            }
            List<String> result = sortDiskCapacity(strings);
            for (String s : result) {
                System.out.println(s);
            }
        }
    }

    /**
     * 排序磁盘容量
     * @param diskCapacityList
     * @return
     */
    public static List<String> sortDiskCapacity(String[] diskCapacityList) {

        List<Map.Entry<Integer, String>> result = new ArrayList<>();
        for (int i = 0; i < diskCapacityList.length; i++) {
            String diskCapacity = diskCapacityList[i];
            int sum = 0;
            int left = 0;
            int right = 1;
            while (right < diskCapacity.length()) {
                if (diskCapacity.charAt(right) == 'M') {
                    sum += Integer.parseInt(diskCapacity.substring(left, right));
                    left = right + 1;
                } else if (diskCapacity.charAt(right) == 'G') {
                    sum += Integer.parseInt(diskCapacity.substring(left, right)) * 1024;
                    left = right + 1;
                }else if(diskCapacity.charAt(right) == 'T'){
                    sum += Integer.parseInt(diskCapacity.substring(left, right)) * 1024 * 1024;
                    left = right + 1;
                }
               right++;
            }
            result.add(new AbstractMap.SimpleEntry<>(sum, diskCapacity));
        }

        result.sort(Comparator.comparing(Map.Entry::getKey));
        List<String> list = new ArrayList<>();
        for (Map.Entry<Integer, String> item : result){
            list.add(item.getValue());
        }
        return list;
    }

}
