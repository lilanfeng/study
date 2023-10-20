package HJ2023B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述：
 * 企业路由器的统计页面，有一个功能需要动态统计公司访问最多的网页URL top N，请设计一个算法，可以高效动态统计Top N的页面
 *  输入描述：
 *    每一行都是一个URL或一个数字，如果是URl，代表一段时间内的网页访问：如果是一个数字N，代表本次需要输入的Top N 个URL
 *    输入约束：
 *    1，总访问网页数量小于5000个，单网页访问次数小于65535；
 *    2，网页URL仅由字母，数字和点分隔组成，且长度小于等于127字节；
 *    3，数字是正整数，小于等于10且小于当前总访问网页数；
 *    输出描述：
 *    每行输入要对应一行输出，输出按访问次数排序的前N个URL，用逗号分隔。
 *    输出要求：
 *    1，每次输出要统计之前所有输入，不仅是本次输入；
 *    2，如果有访问次数相等的URL，按URL的字符串字典序升序排列，输出排序靠前的URL；
 *
 *   用例1
 *   输入：news.qq.com
 *   news.sina.com.cn
 *   news.qq.com
 *   news.qq.com
 *   game.163.com
 *   game.163.com
 *   www.huawei.com
 *   www.cctv.com
 *   3
 *   www.huawei.com
 *   www.cctv.com
 *   www.huawei.com
 *   www.cctv.com
 *   www.huawei.com
 *   www.cctv.com
 *   www.huawei.com
 *   www.cctv.com
 *   www.huawei.com
 *   3
 *   输出：
 *     news.qq.com,game.163.com,news.sina.com.cn
 *     www.huawei.com,www.cctv.com,news.qq.com
 *   用例2:
 *   输入: news.qq.com
 *   www.cctv.com
 *   1
 *   www.huawei.com
 *   www.huawei.com
 *   2
 *   3
 *   输出
 *   news.qq.com
 *   www.huawei.com,news.qq.com
 *   www.huawei.com, news.qq.com,www.cctv.com
 */
public class _041_热点网站统计 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        while (sc.hasNext()){
            String str = sc.nextLine();
            list.add(str);
            if(str.matches("^\\d+$")){
                solution(list,map);
                list.clear();
            }
        }
    }

    public static void solution(List<String> list,Map<String,Integer> map){
        int number = Integer.parseInt(list.get(list.size() -1));
        for (int i = 0; i < list.size() -1 ; i++) {
            String url = list.get(i);
            map.put(url, map.getOrDefault(url,0) + 1);
        }
        List<Map.Entry<String,Integer>> sortList = new ArrayList<>(map.entrySet());
        sortList.sort((a,b)->{
            int res = b.getValue() - a.getValue();
            return res == 0? a.getKey().compareTo(b.getKey()) : res;
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < number && i < sortList.size(); i++) {
            stringBuilder.append(sortList.get(i).getKey()).append(",");
        }
        if(stringBuilder.length() > 0){
            stringBuilder.deleteCharAt(stringBuilder.length() -1);
        }
        System.out.println(stringBuilder.toString());
    }
}
