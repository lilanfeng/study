package E卷.easy100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 17:04
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _031_免单统计 {
    /**
     * 题目描述
     * 华为商城举办了一个促销活动，如果某顾客是某一秒内最早时刻下单的顾客（可能是多个人），则可以获取免单。
     * 请你编程计算有多少顾客可以获取免单。
     * 输入描述
     * 输入为 n 行数据，每一行表示一位顾客的下单时间
     * 以（年-月-日时-分-秒.毫秒） yyyy-MM-ddHH:mm:ss.fff 形式给出。
     * 0<n<50000
     * 2000<yyyy<2020
     * 0<MM<=12
     * 0<dd<=28
     * 0<=HH<=23
     * 0<=mm<=59
     * 0<=ss<=59
     * 0<=fff<=999
     * 所有输入保证合法。
     * 输出描述
     * 输出一个整数，表示有多少顾客可以获取免单。
     * 示例1
     * 输入
     * 3
     * 2019-01-01 00:00:00.001
     * 2019-01-01 00:00:00.002
     * 2019-01-01 00:00:00.003
     * 输出
     * 1
     * 说明
     * 样例 1 中，三个订单都是同一秒内下单，只有第一个订单最早下单，可以免单。
     *
     * 示例2
     * 输入
     * 3
     * 2019-01-01 08:59:00.123
     * 2019-01-01 08:59:00.123
     * 2018-12-28 10:08:00.999
     * 输出
     * 3
     * 说明
     * 样例 2 中，前两个订单是同一秒内同一时刻（也是最早）下单，都可免单，第三个订单是当前秒内唯一一个订单（也是最早），也可免单。
     * 示例3
     * 输入
     * 5
     * 2019-01-01 00:00:00.004
     * 2019-01-01 00:00:00.004
     * 2019-01-01 00:00:01.006
     * 2019-01-01 00:00:01.006
     * 2019-01-01 00:00:01.005
     * 输出
     * 3
     * 说明
     * 样例 3 中，前两个订单是同一秒内同一时刻（也是最早）下单，第三第四个订单不是当前秒内最早下单，不可免单，第五个订单可以免单。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] orders = new String[n];
        for (int i = 0; i < n; i++) {
            orders[i] = scanner.nextLine();
        }
        scanner.close();
        int freeCount = getFreeCount(orders);
        System.out.println(freeCount);
    }
    public static int getFreeCount(String[] orders) {
        int freeCount = 0;
        Map<String, List<String>> orderMap = new HashMap<>();
        for (String order : orders) {
            String[] split = order.split("\\.");
            String time = split[1];
            String key = split[0];
            List<String> findList = null;
            if (orderMap.containsKey(key)) {
                findList = orderMap.get(key);
            } else {
                findList = new ArrayList<>();
            }
            findList.add(time);
            orderMap.put(key, findList);
        }
        for (Map.Entry<String, List<String>> entry : orderMap.entrySet()) {
            List<String> value = entry.getValue();
            if (value.size() == 1) {
                freeCount++;
                continue;
            }
            value.sort(String::compareTo);
            String time1 = value.get(0);
            freeCount++;
            for (int i = 1; i < value.size(); i++) {
                String time2 = value.get(i);
                if (time1.equals(time2)) {
                    freeCount++;
                } else {
                    break;
                }
            }
        }


        return freeCount;
    }
}
