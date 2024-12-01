package already;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 食堂供餐
 * 知识点编程基础循环
 * 时间限制：1s 空间限制：32MB 限定语言：不限
 * 题目描述：
 * 某公司员工食堂以盒饭方式供餐。为将员工取餐排队时间降低为0，食堂的供餐速度必须要足够快。现在需要根据以往员工取餐的统计信息，计算出一个刚好能达成排队时间为0的最低供餐速度。即，食堂在每个单位时间内必须至少做出多少份盒饭才能满足要求。
 * 输入描述：
 * 第1行为一个正整数N，表示食堂开餐时长。1 <= N <= 1000。
 * 第2行为一个正整数M，表示开餐前食堂已经准备好的盒饭份数。P1 <= M <= 1000。
 * 第3行为N个正整数，用空格分隔，依次表示开餐时间内按时间顺序每个单位时间进入食堂取餐的人数Pi。1 <= i <= N，0 <= Pi <= 100。
 *
 * 输出描述：
 * 一个整数，能满足题目要求的最低供餐速度（每个单位时间需要做出多少份盒饭）。
 * 示例1
 * 输入	3
 * 14
 * 10 4 5
 * 输出	3
 *
 * 补充说明：
 * 文章知识点与官方知识档案匹配，可进一步学习相关知识
 * 算法技能树首页概览52105 人正在系统学习中
 *
 * ————————————————
 * 版权声明：本文为CSDN博主「若博豆」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_34465338/article/details/130807463
 */
public class _057_食堂供餐 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 第一行输入就餐的批次
            int count = Integer.parseInt(sc.nextLine());
            // 第二行输入初始库存数
            int stockQty = Integer.parseInt(sc.nextLine());
            // 第三行输入每批次所需的午餐份数
            // 此处dinners中的元素都是String，后面需要转换成int处理
            String strDinners = sc.nextLine();
            String[] dinners = strDinners.split(" ");
            // dinners.length == count

            processDinners(count, stockQty, dinners);
        }

    }

    public static void processDinners(int  count,int stockQty,String[] dinners){
        int currentSumNeeded = 0;
        int minNeeded = 0;
        int currentStock = stockQty;
        for (int i = 0; i < count; i++) {
            currentStock = stockQty + i * minNeeded;
            currentSumNeeded += Integer.parseInt(dinners[i]);
            if (currentStock < currentSumNeeded) {
                // 计算最小的 x（新值必定比当前的x大），确保 currentStock >= currentSumNeeded
                int diff = currentSumNeeded - stockQty;
                int tmpX = diff / i;
                if (diff % i != 0) {
                    tmpX++;
                }
                minNeeded = tmpX;
            }
        }
        System.out.println(minNeeded);
    }

    /**
     * 3
     * 14
     * 10 4 5
     *
     * 3
     * @param time
     * @param alreadyCount
     * @param costArr
     */
    public static void findMin(int time,int alreadyCount,int[] costArr){

        int add = 0;
        int totalCost = 0;
        int minAdd = Integer.MAX_VALUE;
        for (int i = 0; i < time; i++) {
            int totalCount = alreadyCount + i*add;
            totalCost += costArr[i];
            if(totalCost >  totalCount){
                add = (totalCost - totalCount) /i;
                if((totalCost -totalCount) % i > 0){
                    add++;
                }
                minAdd = add;
            }
        }

        System.out.println(minAdd);

    }


}
