package E卷.hard200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/8 10:39
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _013_模拟目录管理功能 {
    /**
     * 模拟目录管理功能
     * 题目描述
     * 实现一个模拟目录管理功能的软件，输入一个命令序列，输出最后一条命令运行结果。
     * 支持命令：
     * 创建目录命令：mkdir 目录名称，如 mkdir abc 为在当前目录创建abc目录，如果已存在同名目录则不执行任何操作。此命令无输出。
     * 进入目录命令：cd 目录名称，如 cd abc 为进入abc目录，特别地，cd … 为返回上级目录，如果目录不存在则不执行任何操作。此命令无输出。
     * 查看当前所在路径命令：pwd，输出当前路径字符串。
     * 约束：
     * 目录名称仅支持小写字母；mkdir 和 cd 命令的参数仅支持单个目录，如：mkdir abc 和 cd abc；不支持嵌套路径和绝对路径，
     * 如 mkdir abc/efg，cd abc/efg，mkdir /abc/efg，cd /abc/efg 是不支持的。
     * 目录符号为/，根目录/作为初始目录。
     * 任何不符合上述定义的无效命令不做任何处理并且无输出。
     * 输入描述
     * 输入 N 行字符串，每一行字符串是一条命令。
     *
     * 命令行数限制100行以内，目录名称限制10个字符以内。
     *
     * 输出描述
     * 输出最后一条命令运行结果字符串。
     *
     * 用例
     * 输入
     *
     * mkdir abc
     * cd abc
     * pwd
     * 输出
     * /abc/
     * 说明
     *
     * 在根目录创建一个abc的目录并进入abc目录中查看当前目录路径，输出当前路径/abc/。
     *
     * 解题思路
     * 定义一个节点类（Node），用于表示文件系统中的每个目录。该类包含路径信息和一个映射，映射存储子目录和对应的节点对象。
     *
     * 创建一个根节点实例，代表文件系统的根目录。根目录没有父目录。
     *
     * 读取用户输入，根据输入的命令和参数执行相应的操作。
     * 如果输入的是创建目录的命令（例如，“mkdir”），检查目录名是否有效，然后在当前节点下创建新的子目录节点。
     * 如果输入的是切换目录的命令（例如，“cd”），检查目标目录是否存在，如果存在，则更新当前节点为目标节点。
     * 如果输入的是打印当前目录路径的命令（例如，“pwd”），则输出当前节点的路径信息。
     * 循环读取输入直到结束，并在结束时输出最后的路径信息。
     */


    /**
     * 定义一个内部类Node，用于表示文件系统中的每个目录
     */
    static class Node {
        String path; // 目录的路径
        Map<String, Node> next = new HashMap<>();
        // 存储当前目录下的子目录，键为目录名，值为对应的Node对象

        // Node类的构造方法ac
        Node(String path, Node parent) {
            this.path = path; // 设置当前节点的路径
            // 如果存在父目录，则在子目录映射中添加一个指向父目录的条目
            if (parent != null) {
                this.next.put("..", parent);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> commandList = new ArrayList<>();
        while (sc.hasNextLine()){
            String command = sc.nextLine();
            if (command.trim().isEmpty()) {
                break;
            }
            commandList.add(command);
        }
        sc.close();
        System.out.println(executeCommands(commandList));;
    }

    /**
     * 执行命令列表
     * @param commandList
     * @return
     */
    public static String executeCommands(List<String> commandList) {
        Node root = new Node("/", null);
        Node current = root;
        StringJoiner result = new StringJoiner(" ");
        for (String command : commandList) {
            String[] parts = command.split(" ");
            String cmd = parts[0];
            String arg = parts.length > 1 ? parts[1] : "";

            if (cmd.equals("mkdir") && parts.length == 2 && isValidDirName(arg)) {
                // 创建目录
                if (!current.next.containsKey(arg)) {
                    current.next.put(arg, new Node(current.path + arg + "/", current));
                }
            } else if (cmd.equals("cd") && parts.length == 2 && isValidChangeDirName(arg)) {
               // 切换目录
                if (arg.equals("..")) {
                    // 返回上级目录
                    current = current.next.get("..");
                } else if (current.next.containsKey(arg)) {
                    // 移动到目标目录
                    current = current.next.get(arg);
                }
            } else if (cmd.equals("pwd") && parts.length == 1) {
                // 打印当前目录路径
                result.add( current.path);
            }
        }
        return result.toString();
    }
    private static boolean isValidDirName(String dirName) {
        return dirName.matches("[a-z]+");
    }
    private static boolean isValidChangeDirName(String dirName) {
        return dirName.equals("..") || isValidDirName(dirName);
    }
}
