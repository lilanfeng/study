package E卷.hard200;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/8 06:09
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _010_九宫格按键输入 {
    /**
     * 题目描述
     * 九宫格按键输入，输出显示内容，有英文和数字两个模式，默认是数字模式，数字模式直接输出数字，
     * 英文模式连续按同一个按键会依次出现这个按键上的字母，如果输入”/”或者其他字符，则循环中断。
     * 字符对应关系如图：
     *
     * 在这里插入图片描述
     *
     * 要求输入一串按键，输出屏幕显示。
     *
     * #用于切换模式，默认是数字模式，执行#后切换为英文模式；
     * /表示延迟，例如在英文模式下，输入 22/222，显示为 bc；
     * 英文模式下，多次按同一键，例如输入 22222，显示为 b；
     * 输入描述
     * 输入范围为数字 0~9 和字符’#’、’/’，输出屏幕显示，例如，
     *
     * 在数字模式下，输入 1234，显示 1234
     * 在英文模式下，输入 1234，显示,adg
     * 输出描述
     * #用于切换模式，默认是数字模式，执行#后切换为英文模式；
     * /表示延迟，例如在英文模式下，输入 22/222，显示为 bc；
     * 英文模式下，多次按同一键，例如输入 22222，显示为 b；
     * 示例1
     * 输入
     * 2222/22
     * 输出
     * 222222
     * 说明
     * 默认数字模式，字符直接显示，数字模式下/无序
     * 示例2
     * 输入
     * 123#222235/56
     * 输出
     * 123adjjm
     * 说明
     * 123,#进入英文模式，连续的数字输入会循环选择字母4个2输出a,35输出dj56输出jm
     * 示例2
     * 输入
     * #2222/22
     * 输出
     * ab
     * 说明
     * #进入英文模式，连续的数字输入会循环选择字母，直至输入/，故第一段2222输入显示a，第二段22输入显示b
     * 示例3
     * 输入
     * #222233
     * 输出
     * ae
     * 说明
     * #进入英文模式，连续的数字输入会循环选择字母，直至输入其他数字，故第一段2222输入显示a，第二段33输入显示e
     * 解题思路
     * 解题思路
     * 这个题目要求将一串按键输入转换为屏幕上显示的内容，类似于老式手机的九宫格输入法。根据题目要求，输入有两种模式：数字模式和英文模式。程序默认从数字模式开始，按下 # 键后切换为英文模式，按 # 键可以在两种模式之间切换。/
     * 表示延迟操作，在英文模式下，用于区分同一个按键的不同字符输入。
     * 映射关系：首先要将每个数字键与对应的字符集合建立映射关系。例如，键 2 对应 “abc”，键 3 对应 “def” 等。这些映射关系会用于在英文模式下解
     * 数字模式处理：
     * 如果当前是数字模式，直接将数字字符加入到结果中。
     * 英文模式处理：
     * 如果当前是英文模式，遇到数字字符时，判断连续相同的数字出现次数，并根据次数确定对应的字母。例如，连续按 2 三次表示 “c”。
     * 如果输入了 /，意味着在当前的英文模式下要延迟处理（即结束当前数字字符的连续输入），但不改变模式。
     * 模式切换：
     * 遇到 # 字符时，切换模式。如果当前是数字模式，切换到英文模式；如果当前是英文模式，切换回数字模式。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(getScreenDisplay(input));
        }

    }
    public static String getScreenDisplay(String input) {
        StringBuilder result = new StringBuilder();
        int mode = 0;
        int count = 0;
        char prevChar = '\0';
        // 初始化 数字对应的字母集合
        String[] nums = {" ",",.", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9') {
                if (mode == 0) {
                    result.append(c);
                } else if(mode == 1){
                    int num = c - '0';
                    String letters = nums[num];
                    // 计算连续次数
                    count = 1;
                    for (int j = i + 1; j < input.length(); j++) {
                        if(input.charAt(j) == c){
                            count++;
                        }else {
                            break;
                        }
                    }
                    char mappedChar = letters.charAt( (count % letters.length()) - 1);
                    result.append(mappedChar);
                    i = i + count - 1;
                }
            }else if (c == '#') {
                mode = (mode+1) % 2;
            }else if (c == '/') {
                // 处理 / 不处理
            }else {
                // 处理其他字符
                break;
            }
        }

        return result.toString();
    }
}
