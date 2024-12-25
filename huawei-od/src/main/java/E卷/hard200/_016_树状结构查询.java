package E卷.hard200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/8 20:11
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _016_树状结构查询 {
    /**
     * 树状结构查询
     * 题目描述
     * 通常使用多行的节点、父节点表示一棵树，比如
     * 西安 陕西
     * 陕西 中国
     * 江西 中国
     * 中国 亚洲
     * 泰国 亚洲
     * 输入一个节点之后，请打印出来树中他的所有下层节点
     * 输入描述
     * 第一行输入行数，下面是多行数据，每行以空格区分节点和父节点
     * 接着是查询节点
     * 输出描述
     * 输出查询节点的所有下层节点。以字典序排序
     * 示例1
     * 输入
     * 5
     * b a
     * c a
     * d c
     * e c
     * f d
     * c
     * 输出
     * d
     * e
     * f
     * 说明
     *
     * 解题思路
     * 这个题目描述了一棵树的结构，并要求我们找到一个给定节点的所有下层节点（即该节点的所有子节点及其后代节点）。
     *
     * 示例解释
     * 输入：
     *
     * 5
     * b a
     * c a
     * d c
     * e c
     * f d
     * c
     * 解析：
     *
     * 树结构的表示：
     * b 的父节点是 a
     * c 的父节点是 a
     * d 的父节点是 c
     * e 的父节点是 c
     * f 的父节点是 d
     * 查询节点为 c，我们需要找出所有 c 的下层节点。
     * 根据上述结构，我们可以构建以下树：
     *
     *         a
     *        / \
     *       b   c
     *          / \
     *         d   e
     *        /
     *       f
     * 查询节点：c
     *
     * 输出结果：
     *
     * c 的直接下层节点是 d 和 e。
     * d 的下层节点是 f。
     * 因此，c 的所有下层节点为：d, e, f。
     * 字典序排序后，输出结果为：
     *
     * d
     * e
     * f
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        String[] arr = new String[number];
        for (int i = 0; i < number; i++) {
            arr[i] = scanner.nextLine();
        }
        String queryNode = scanner.nextLine();
        String result = getLowerLevelNodes(arr, queryNode);
        System.out.println(result);
    }

    /**
     * 获取给定节点的所有下层节点
     * @param arr
     * @param query
     * @return
     */
    public static String getLowerLevelNodes(String[] arr, String query) {
        // 创建一个树节点的数组，用于存储树的节点和父节点
        HashMap<String, HashSet<String>> treeNodeMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String[] parts = arr[i].split(" ");
            String parentNode = parts[1];
            String childNode = parts[0];
            HashSet<String> setNode;
            if (treeNodeMap.containsKey(parentNode)) {
                setNode = treeNodeMap.get(parentNode);
                setNode.add(childNode);
            }else {
                setNode = new HashSet<>();
                setNode.add(childNode);
            }
            treeNodeMap.put(parentNode, setNode);
        }
        LinkedList<String> queue = new LinkedList<>(treeNodeMap.get(query));
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String node = queue.removeFirst();
            result.add(node);
            if (treeNodeMap.containsKey(node)) {
                queue.addAll(treeNodeMap.get(node));
            }
        }
        result.sort(String::compareTo);
        return String.join("\n", result);
    }
}
