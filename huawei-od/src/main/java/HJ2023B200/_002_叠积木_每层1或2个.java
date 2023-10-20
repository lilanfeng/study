package HJ2023B200;

import java.util.Arrays;

import java.util.Scanner;

/**
 *  题目描述
 * 有一堆长方体积木，它们的宽度和高度都相同，但长度不一。
 * 小橙想把这堆积木叠成一面墙，墙的每层可以放一个积木，也可以将两个积木拼接起来，要求每层的长度相同。
 *
 * 若必须用完这些积木，叠成的墙最多为多少层？
 *
 * 输入描述
 * 输入为一行，为各个积木的长度，数字为正由空整数，并格分隔。积木的数量和长度都不超过5000。
 *
 * 输出描述
 * 输出一个数字，为墙的最大层数，如果无法按要求叠成每层长度一致的墙，则输出-1。
 *
 * 用例
 * 输入	3 6 6 3
 * 输出	3
 * 说明	可以每层都是长度3和6的积木拼接起来，这样每层的长度为9，层数为2；也可以其中两层直接用长度6的积木，两个长度3的积木拼接为一层，这样层数为3，故输出3。
 * 输入	1 4 2 3 6
 * 输出	-1
 * 说明	无法用这些积木叠成每层长度一致的墙，故输出-1。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129696955
 */
public class _002_叠积木_每层1或2个 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            buildHeight(sc.nextLine());
        }
    }

    public static void buildHeight(String str){
        Integer[] widths = Arrays.stream(str.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        int count = widths.length;
        int maxHeight = -1;

        if(count == 1){
            maxHeight = 1;
        } else if(count == 2){
            maxHeight = widths[0].equals(widths[1]) ? 2 : 1;
        }else {
            Arrays.sort(widths,(a,b)-> b - a);
            int minLength = widths[0];
            int maxLength = widths[0] + widths[1];
            for (int i = minLength; i <= maxLength ; i++) {
                int height = 0;
                int left = 0;
                int right = count -1;
                while (left < right){
                    if(widths[left] == i){
                        left++;
                        height++;
                    }else if(widths[left] + widths[right] == i){
                        left++;
                        right--;
                        height++;
                    }else {
                        break;
                    }
                }
                if(left >= right){
                    maxHeight = height;
                    break;
                }
            }

        }

        System.out.println(maxHeight);
    }
}
