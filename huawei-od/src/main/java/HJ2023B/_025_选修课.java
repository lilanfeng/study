package HJ2023B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 现有两门选修课，每门选修课都有一部分学生选修，每个学生都有选修课的成绩，需要你找出同时选修了两门选修课的学生，先按照班级进行划分，班级编号小的先输出，每个班级按照两门选修课成绩和的降序排序，成绩相同时按照学生的学号升序排序。
 *
 * 输入
 * 第一行为第一门选修课学生的成绩，
 * 第二行为第二门选修课学生的成绩，每行数据中学生之间以英文分号分隔，每个学生的学号和成绩以英文逗号分隔，学生学号的格式为8位数字(2位院系编号+入学年份后2位+院系内部1位专业编号+所在班级3位学号),学生成绩的取值范围为 [0,
 * 100] 之间的整数，两门选修课选修学生数的取值范围为 [1-2000] 之间的整数。
 *
 * 输出
 * 同时选修了两门选修课的学生的学号，如果没有同时选修两门选修课的学生输出 NULL, 否则，先按照班级划分，班级编号小的先输出，每个班级先输出班级编号(学号前五位),
 * 然后另起一行输出这个班级同时选修两门选修课的学生学号，学号按照要求排序(按照两门选修课成绩和的降序，成绩和相同时按照学号升序),学生之间以英文分号分隔。
 *
 * 用例：
 * 输入：
 *  01202021,75;01201033,95;01202008,80;01203006,90;01203088,100
 *  01202008,70;01203088,85;01202111,80;01202021,75;01201100,88
 * 输出
 * 01202
 * 01202008;01202021
 * 01203
 * 01203088
 *
 *  * 输入：
 *  *  01203088,10;01202021,75;01201033,95;01202008,80;01203006,90;
 *  *  01202008,70;01203088,85;01202111,80;01202021,75;01201100,88
 *  * 输出
 *  * 01202
 *  * 01202008;01202021
 *  * 01203
 *  * 01203088
 *
 * 输入:
 * 01201022,75;01202033,95;01202018,80;01203006,90;01202066,100
 * 01202008,70;01203102,85;01202111,80;01201021,75;01201100,88
 * 输出:
 * NULL
 *
 * 输入：
 *  *  01202021,75;01201033,95;01202008,80;01203006,90;01203088,100;01202007,60
 *  *  01202008,70;01203088,85;01202111,80;01202021,75;01201100,88;01202007,50
 *  * 输出
 *  * 01202
 *  * 01202008;01202021;01202007
 *  * 01203
 *  * 01203088
 *
 * 文章知识点与官方知识档案匹配，可进一步学习相关知识
 * Java技能树首页概览130503 人正在系统学习中
 *
 * ————————————————
 * 版权声明：本文为CSDN博主「codereasy」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/wtswts1232/article/details/132401983
 */
public class _025_选修课 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            Map<String,String> chooseFirstMap = new HashMap<>();
            getChoose(chooseFirstMap,sc.nextLine());
            Map<String,String> chooseSecondMap = new HashMap<>();
            getChoose(chooseSecondMap,sc.nextLine());
            findSortMap(chooseFirstMap,chooseSecondMap);
        }
    }

    public static void findSortMap(Map<String,String> chooseFirstMap,Map<String,String> chooseSecondMap){

        Map<String,Integer> resultMap = new HashMap<>();
        for (Map.Entry<String,String> entry :chooseFirstMap.entrySet()){
            if(chooseSecondMap.containsKey(entry.getKey())){
                resultMap.put(entry.getKey(),Integer.parseInt(entry.getValue())+Integer.parseInt(chooseSecondMap.get(entry.getKey())));
            }
        }

        if(resultMap.size() <= 0){
            System.out.println("NULL");
            return;
        }
        TreeMap<String,Map<String,Integer>> resultPrintMap = new TreeMap<>();
        for (Map.Entry<String,Integer> entry:resultMap.entrySet()){
            String key = entry.getKey();
            Integer point = entry.getValue();
            String classKey = key.substring(0,5);

            Map<String,Integer> tempMap = null;
            if(resultPrintMap.containsKey(classKey)){
                tempMap = resultPrintMap.get(classKey);
            }else {
                tempMap = new HashMap<>();
            }
            tempMap.put(key,point);
            resultPrintMap.put(classKey,tempMap);
        }

        for (String key:resultPrintMap.keySet()){
            System.out.println(key);
            Map<String,Integer> map = resultPrintMap.get(key);
            printPoint(map);
        }
    }

    public static void printPoint(Map<String,Integer> map){
        List<Map.Entry<String,Integer> > entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue().equals(o2.getValue())){
                    Integer student1 = Integer.parseInt(o1.getKey().substring(6));
                    Integer student2 = Integer.parseInt(o2.getKey().substring(6));
                    //升序的直接比较
                    return student1.compareTo(student2);
                }
                //降序需要两者反过来
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        StringBuffer str = new StringBuffer();
        for (Map.Entry<String,Integer> entry:entryList){
            str.append(entry.getKey()).append(";");
        }
        System.out.println(str.substring(0,str.length()-1));
    }


    /**
     *
     * @param chooseMap
     * @param chooseStr
     */
    public static void getChoose(Map<String,String> chooseMap,String chooseStr){
        String[] chooseFirst = chooseStr.split(";");
        for (int i = 0; i < chooseFirst.length; i++) {
            String choose = chooseFirst[i];
            String[] chooseArr = choose.split(",");
            if(chooseArr.length == 2){
                chooseMap.put(chooseArr[0],chooseArr[1]);
            }
        }
    }


}
