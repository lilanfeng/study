package HJ2023B;

import java.util.Scanner;
import java.util.function.LongBinaryOperator;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 题目描述
 * 存在一种虚拟 IPv4 地址，由 4 小节组成，每节的范围为 0~255，以#号间隔，虚拟 IPv4 地址可以转换为一个 32 位的整数，例如:
 * 128#0#255#255，转换为 32 位整数的结果为 2147549183 (0x8000FFFF)
 * 1#0#0#0，转换为 32 位整数的结果为 16777216 (0x01000000)
 * 现以字符串形式给出一个虚拟 IPv4 地址，限制第 1 小节的范围为 1~128，即每一节范围 分别为(1~128)#(0~255 )#(0~255)#(0-255)，要求每个 IPV4 地址只能对应到唯一的整数上。如 果是非法
 * IPV4，返回 invalid IP
 * 输入描述
 * 输入一行，虚拟 IPv4 地址格式字符串 输出描述 输出一行，按照要求输出整型或者特定字符
 *  用例
 *     输入：100#101#1#5
 *     输出：1684340997
 *     说明
 *
 *     输入：1#2#3
 *     输出：invalid IP
 *     说明
 */
public class _034_IPv4地址转换成整数 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){

            int result = changeIpv4(sc.nextLine().split("#"));
            if(result <= 0 ){
                System.out.println("invalid IP");
            }else {
                System.out.println(result);
            }
        }
    }

    public static int changeIpv4(String[] ipv4){
       if(ipv4.length != 4){
           return 0;
       }

       StringBuilder hexStr = new StringBuilder();
       boolean isMax = false;
        for (int i = 0; i < ipv4.length; i++) {
            int temp = Integer.valueOf(ipv4[i]);
            if(temp < 0 || temp > 255){
                isMax = true;
            }
            String hexTemp = Integer.toHexString(temp);
            if(hexTemp.length() == 1){
                hexStr.append("0");
            }
            hexStr.append(hexTemp);
        }
        if(isMax){
            return 0;
        }
        int value = Integer.parseUnsignedInt(hexStr.toString(),16);

        return value;
    }

}
