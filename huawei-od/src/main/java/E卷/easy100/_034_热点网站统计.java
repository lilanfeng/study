package E卷.easy100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 06:19
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _034_热点网站统计 {
    /**
     * 题目描述
     * 企业路由器的统计页面，有一个功能需要动态统计公司访问最多的网页URL top N。请设计一个算法，可以高效动态统计Top N的页面。
     * 输入描述
     * 每一行都是一个URL或一个数字，如果是URL，代表一段时间内的网页访问； 如果是一个数字N，代表本次需要输出的Top N个URL。
     * 输入约束：
     *
     * 1、总访问网页数量小于5000个，单网页访问次数小于65535次；
     * 2、网页URL仅由字母，数字和点分隔符组成，且长度小于等于127字节； 3、数字是正整数，小于等于10且小于当前总访问网页数；
     * 输出描述
     * 行输入要对应一行输出，输出按访问次数排序的前N个URL，用逗号分隔。
     * 输出要求：
     * 1、每次输出要统计之前所有输入，不仅是本次输入；
     * 2、如果有访问次数相等的URL，按URL的字符串字典序升序排列，输出排序靠前的URL；
     * 示例1
     * 输入
     * news.qq.com
     * news.sina.com.cn
     * news.qq.com
     * news.qq.com
     * game.163.com
     * game.163.com
     * www.huawei.com
     * www.cctv.com
     * 3
     * www.huawei.com
     * www.cctv.com
     * www.huawei.com
     * www.cctv.com
     * www.huawei.com
     * www.cctv.com
     * www.huawei.com
     * www.cctv.com
     * www.huawei.com
     * 3
     * 输出
     * news.qq.com,game.163.com,news.sina.com.cn
     * www.huawei.com,www.cctv.com,news.qq.com
     * 说明
     *
     * 示例2
     * 输入
     * news.qq.com
     * www.cctv.com
     * 1
     * www.huawei.com
     * www.huawei.com
     * 2
     * 3

     * 输出
     * news.qq.com
     * www.huawei.com,news.qq.com
     * www.huawei.com,news.qq.com,www.cctv.com
     */
    public static void main(String[] args) {
        // 输入
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> urlMap = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.matches("^\\d+$")) {
                int n = Integer.parseInt(line);
                // 输出
                System.out.println(getTopNUrl(urlMap, n));
            } else {
                urlMap.put(line, urlMap.getOrDefault(line, 0) + 1);
            }
        }
    }

    /**
     * 获取前N个url
     * @param urlMap
     * @param n
     * @return
     */
    public static String getTopNUrl(Map<String, Integer> urlMap, int n) {
        if (urlMap.isEmpty()) {
            return "";
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(urlMap.entrySet());
        // 将哈希表中的每一项转换成一个键值对，并存入一个列表中
        list.sort((a, b) -> {
            // 对列表进行排序，按照计数从大到小排序，如果计数相同则按照字典序从小到大排序
            int res = b.getValue() - a.getValue();
            return res == 0 ? a.getKey().compareTo(b.getKey()) : res;
        });
        StringJoiner sj = new StringJoiner(",");
        for (int i = 0; i < n && i < list.size(); i++) {
            // 取出前 n 个 URL，并将它们拼接成一个字符串
            sj.add(list.get(i).getKey());
        }
        return sj.toString();
    }
}
