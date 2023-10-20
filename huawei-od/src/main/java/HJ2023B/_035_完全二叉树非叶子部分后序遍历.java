package HJ2023B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 给定一个以顺序储存结构存储整数值的 完全二叉树 序列(最多 1000 个整数)，请找出 此完全二又树的所有非叶子节点部分，然后采用后序遍历方式将此部分树 (不包含叶子) 输 出。
 * 只有一个节点的树，此节点认定为根节点(非叶子)。2、此完全二叉树并非 满二叉树， 可能存在倒数第二层出现叶子或者无右叶子的情况其他说明: 二又树的后序遍历 Q 是基于 根来说的，遍历顺序为: 左-右-根
 * 输入描述
 * 个通过空格分割的整数序列字符串
 * 输出描述
 * 非叶子部分树结构。备注: 输出数字以空格分隔
 * 用例
 * 输入：1 2 3 4 5 6 7
 * 输出： 2 3 1
 *  说明：完全二叉树用数组表示有如下规律：arr[i] 的   左孩子是 arr[2*i+1]  右孩子是arr[2*i+2]
 *    需要输出后序遍历；左，右，根
 *
 *
 * 输入：
 * 输出：
 */
public class _035_完全二叉树非叶子部分后序遍历 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            System.out.println(getResult(Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new)));
        }

    }

    public static String getResult(Integer[] arr){

        if(arr.length == 1){
            return arr[0] + "";
        }
        ArrayList<Integer> res = new ArrayList<>();
        dfs(arr,0,res);
        StringJoiner sj = new StringJoiner("");
        for (Integer re:res){
            sj.add(re+" ");
        }
        return sj.toString();
    }

    public static void dfs(Integer[] arr,int root,ArrayList<Integer> res){
        int left = root * 2 + 1;
        int right = root*2 + 2;
        if(arr.length > left){
            dfs(arr,left,res);
            if(arr.length > right){
                dfs(arr,right,res);
            }
            res.add(arr[root]);
        }
    }


}
