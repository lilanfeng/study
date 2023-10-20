package HJ2023B;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一个字符串，表示用','分开的人名。
 * 然后给定一个字符串，进行快速人名查找，符合要求的输出。
 * 快速人名查找要求：人名的每个单词的连续前几位能组成给定字符串，一定要用到每个单词。
 *
 * 输入描述：
 * 第一行是人名，用'，'分开的人名
 * 第二行是查找字符串
 *
 * 输出描述：
 *  输出满足要求的人名
 *
 *  用例：
 *    输入：
 *    zhang san,zhang san san
 *    zs
 *    输出：
 *     zhang san
 *
 *   输入：
 *    zhang san san,zhang an sa,zhang hang,zhang seng,zhang sen a
 *    zhas
 *   输出:
 *    zhang an sa,zhang seng
 */
public class _006_快速人名查找 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String source = sc.nextLine();
        String target = sc.next();
        findName(source,target);
    }

    public static void findName(String source,String target){
        List<String> nameList = Arrays.asList(source.split(","));
        String result = "";
        for(String name:nameList){
            List<String> personList = Arrays.asList(name.split(" "));
            if(dfs(personList,0,target,0)){
                result = result.isEmpty()? name : result+"," + name;
            }
        }
        System.out.println(result);
    }

    /**
     *
     * @param personList  待查找的名字列表
     * @param pi  第几个名字
     * @param target  目标的简称
     * @param ej  简称到了第几个
     * @return
     */
    private static boolean dfs(List<String> personList,int pi,String target,int ej){
        if (pi == personList.size() || ej == target.length()) {
            return (pi == personList.size() && ej == target.length());
        }
        String name = personList.get(pi);

        if(name.charAt(0) != target.charAt(ej)){
            return false;
        }

        int count = 1;
        while (count < name.length() &&
                ej + count < target.length() &&
                name.charAt(count) == target.charAt(ej+count)){

            if(dfs(personList,pi+1,target,ej+count + 1)){
                return true;
            }
            count++;
        }
        return dfs(personList,pi + 1,target,ej + 1);
    }
}
