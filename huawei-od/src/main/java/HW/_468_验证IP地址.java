package HW;

import java.util.Collections;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 *
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址，
 * “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 *
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 *
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而
 * "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 *
 *
 *
 * 示例 1：
 *
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 * 示例 2：
 *
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 * 示例 3：
 *
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 *
 *
 * 提示：
 *
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 *
 */
public class _468_验证IP地址 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            System.out.println(validIPAddress(sc.nextLine()));
        }
    }
    public static String validIPAddress(String queryIP) {
        if(queryIP == null || queryIP.length() <= 0){
            return "Neither";
        }
        if(queryIP.contains(":")){
            if(isValidIP6(queryIP)){
                return "IPv6";
            }
        }else {
            if(isValidIP4(queryIP)){
                return "IPv4";
            }
        }
        return "Neither";
    }

    /**
     * 1.1.1.1.
     * @param queryIP
     * @return
     */
    public static boolean isValidIP4(String queryIP){
        String[] ip4List = queryIP.split("\\.");
        if(ip4List.length != 4){
            return false;
        }
        if(queryIP.startsWith(".") || queryIP.endsWith(".")){
            return false;
        }
        for (int i = 0; i < ip4List.length; i++) {
            String strNumber = ip4List[i];
            if(strNumber.startsWith("0") && strNumber.length() > 1){
                return false;
            }
            try {
                int number = Integer.parseInt(strNumber);
                if(number < 0 || number > 255){
                    return false;
                }
            }catch (NumberFormatException exception){
                return false;
            }
        }
        return true;

    }

    /**
     * 2001:0db8:85a3:0:0:8A2E:0370:7334:
     * @param queryIP
     * @return
     */
    public static boolean isValidIP6(String queryIP){

        String[] ip6List = queryIP.split(":");
        if(queryIP.startsWith(":") || queryIP.endsWith(":")){
            return false;
        }
        if(ip6List.length != 8){
            return false;
        }
        for (int i = 0; i < ip6List.length; i++) {
            String strNumber = ip6List[i];
            int length = strNumber.length();
            if(length > 4 || length <= 0){
                return false;
            }
            for (int j = 0; j < length; j++) {
                char ch = strNumber.charAt(j);
                if(!Character.isDigit(ch) && !('a' <= Character.toLowerCase(ch) && Character.toLowerCase(ch) <= 'f' )  ){
                    return false;
                }
            }

        }
        return true;
    }
}
