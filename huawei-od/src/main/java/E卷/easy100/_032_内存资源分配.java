package E卷.easy100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 17:35
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _032_内存资源分配 {
    /**
     * 题目描述
     * 有一个简易内存池，内存按照大小粒度分类，每个粒度有若干个可用内存资源，用户会进行一系列内存申请，
     * 需要按需分配内存池中的资源返回申请结果成功失败列表。
     * 分配规则如下：
     * 分配的内存要大于等于内存的申请量，存在满足需求的内存就必须分配，优先分配粒度小的，但内存不能拆分使用；
     * 需要按申请顺序分配，先申请的先分配，有可用内存分配则申请结果为true；
     * 没有可用则返回false。
     * 注意：不考虑内存释放
     * 输入描述
     * 输入为两行字符串
     * 第一行为内存池资源列表，包含内存粒度数据信息，粒度数据间用逗号分割
     * 一个粒度信息内用冒号分割，冒号前为内存粒度大小，冒号后为数量
     * 资源列表不大于1024
     * 每个粒度的数量不大于4096
     * 第二行为申请列表，申请的内存大小间用逗号分割
     *
     * 申请列表不大于100000
     * 如:
     * 64:2,128:1,32:4,1:128
     * 50,36,64,128,127
     * 输出描述
     * 输出为内存池分配结果
     * 如true,true,true,false,false
     * 示例1
     * 输入
     * 64:2,128:1,32:4,1:128
     * 50,36,64,128,127
     * 输出
     * true,true,true,false,false
     * 说明
     * 内存池资源包含：64K共2个、128K共1个、32K共4个、1K共128个的内存资源；
     * 针对50,36,64,128,127的内存申请序列，分配的内存依次是：64,64,128,NULL,NULL,
     * 第三次申请内存时已经将128分配出去，因此输出结果是：
     * true,true,true,false,false
     * 解题思路
     * 规则总结：
     * 按需分配内存，不能拆分。
     * 优先分配最小的满足条件的内存块。
     * 内存池中的资源一旦分配出去，就无法再次使用。
     * 若没有可用内存块满足需求，返回 false。
     */
    public static void main(String[] args) {
        //String[] pool = {"64:2,128:1,32:4,1:128"};
        //String[] requests = {"50,36,64,128,127"};
        Scanner scanner = new Scanner(System.in);
        String[] pool = scanner.nextLine().split(",");
        String[] requests = scanner.nextLine().split(",");
        scanner.close();
        System.out.println(allocateMemory(pool, requests));
    }

    /**
     * 内存池分配内存
     * @param pool
     * @param requests
     * @return
     */
    public static String allocateMemory(String[] pool, String[] requests) {

        StringJoiner result = new StringJoiner(",");
        int index = 0;
        List<Integer> poolList = new ArrayList<>();
        for (int i = 0; i < pool.length; i++) {
            String[] poolInfo = pool[i].split(":");
            //一个粒度信息内用冒号分割，冒号前为内存粒度大小，冒号后为数量
            int size = Integer.parseInt(poolInfo[0]);
            int count = Integer.parseInt(poolInfo[1]);
            for (int j = 0; j < count; j++) {
                poolList.add(size);
            }
        }

        for (int i = 0; i < requests.length; i++) {

            if (i > poolList.size()) {
                result.add("false");
                continue;
            }
            int requestSize = Integer.parseInt(requests[i]);
            if(requestSize > poolList.get(index)){
                result.add("false");
            }else {
                result.add("true");
            }
        }
        return result.toString();
    }
}
