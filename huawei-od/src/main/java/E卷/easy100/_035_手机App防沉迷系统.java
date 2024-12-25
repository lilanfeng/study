package E卷.easy100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 11:56
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _035_手机App防沉迷系统 {
    /**
     * 题目描述
     * 智能手机方便了我们生活的同时，也侵占了我们不少的时间。“手机App防沉迷系统”能够让我们每天合理地规划手机App使用时间，在正确的时间做正确的事。
     * 它的大概原理是这样的：
     * 在一天24小时内，可以注册每个App的允许使用时段
     * 一个时间段只能使用一个App
     * App有优先级，数值越高，优先级越高。注册使用时段时，如果高优先级的App时间和低优先级的时段有冲突，则系统会自动注销低优先级的时段，
     * 如果App的优先级相同，则后添加的App不能注册。
     * 请编程实现，根据输入数据注册App，并根据输入的时间点，返回时间点使用的App名称，如果该时间点没有注册任何App，请返回字符串“NA”。
     * 输入描述
     * 第一行表示注册的App数量 N（N ≤ 100）
     * 第二部分包括 N 行，每行表示一条App注册数据
     * 最后一行输入一个时间点，程序即返回该时间点使用的App
     * 2
     * App1 1 09:00 10:00
     * App2 2 11:00 11:30
     * 09:30
     *
     * 数据说明如下：
     * N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
     * 优先级1~5，数字越大，优先级越高
     * 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
     * 起始时间需小于结束时间，否则注册不上
     * 注册信息中的时间段包含起始时间点，不包含结束时间点
     * 输出描述
     * 输出一个字符串，表示App名称，或NA表示空闲时间
     *
     * 示例1
     * 输入
     *
     * 1
     * App1 1 09:00 10:00
     * 09:30
     * 1
     * 2
     * 3
     * 输出
     *
     * App1
     * 1
     * 说明
     *
     * App1注册在9点到10点间，9点半可用的应用名是App1
     *
     * 示例2
     * 输入
     * 2
     * App1 1 09:00 10:00
     * App2 2 09:10 09:30
     * 09:20
     * 输出
     * App2
     * 说明
     *
     * APP1和App2的时段有冲突，App2优先级高，注册App2之后，App1自动注销，因此输出App2。
     *
     * 示例3
     * 输入
     * 2
     * App1 1 09:00 10:00
     * App2 2 09:10 09:30
     * 09:50
     * 输出
     * NA
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        String[] appTime = new String[count];
        for (int i = 0; i < count; i++) {
            appTime[i] = scanner.nextLine();
        }
        String time = scanner.nextLine();
        scanner.close();
        System.out.println(getAppName(appTime, covertTime(time)));
    }

    /**
     * 数据说明如下：
     *      * N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
     *      * 优先级1~5，数字越大，优先级越高
     *      * 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
     *      * 起始时间需小于结束时间，否则注册不上
     *      * 注册信息中的时间段包含起始时间点，不包含结束时间点
     *      * 输出描述
     *      * 输出一个字符串，表示App名称，或NA表示空闲时间
     * @param appTime
     * @param searchTime
     * @return
     */
    private static String getAppName(String[] appTime, int searchTime) {
        List<App> apps = new ArrayList<>();
        for (String app : appTime) {
            String[] split = app.split(" ");
            App app1 = new App(split[0], Integer.parseInt(split[1]), covertTime(split[2]), covertTime(split[3]));
            apps.add(app1);
        }

        List<App> registerApps = new ArrayList<>();
        for (App app : apps) {
            if(app.startTime > app.endTime){
                continue;
            }
            for (int i = registerApps.size()-1; i >= 0; i--) {
                App registerApp = registerApps.get(i);
                // 如果存在时间冲突
                if(Math.max(registerApp.startTime,app.startTime) < Math.min(registerApp.endTime , app.endTime)){
                    // 如果优先级相同，则后添加的App不能注册
                    if(registerApp.priority < app.priority){
                        // 注销低优先级的App
                        registerApps.remove(i);
                        return "NA";
                    }
                }
            }
            registerApps.add(app);
        }

        // 遍历已注册App列表，找到查询时间对应的App
        for (App app : registerApps) {
            if(app.startTime <= searchTime && app.endTime > searchTime){
                return app.name;
            }
        }
        return "NA";
    }

    /**
     * 将时间转化为分钟
     * @param time
     * @return
     */
    private static int covertTime(String time){
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
    static class App{
        String name;
        int priority;
        int startTime;
        int endTime;
        public App(String name, int priority, int startTime, int endTime) {
            this.name = name;
            this.priority = priority;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
