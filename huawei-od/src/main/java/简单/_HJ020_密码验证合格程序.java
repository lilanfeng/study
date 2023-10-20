package 简单;

import java.util.Scanner;

/**
 * 描述
 * 密码要求:
 * 1.长度超过8位
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
 * 数据范围：输入的字符串长度满足 1≤n≤100
 * 输入描述：
 * 一组字符串。
 * 输出描述：
 * 如果符合要求输出：OK，否则输出NG
 *
 * 示例1
 * 输入：
 * 021Abc9000
 * 021Abc9Abc1
 * 021ABC9000
 * 021$bc9000
 * 输出：
 * OK
 * NG
 * NG
 * OK
 */
public class _HJ020_密码验证合格程序 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            String str = sc.nextLine();
            if(str.length() <= 8){
                System.out.println("NG");
                continue;
            }
            if(!isValidPassWord(str)){
                System.out.println("NG");
                continue;
            }
            if(validRepeat(str,0,3)){
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }

    }

    public static boolean isValidPassWord(String str){
        boolean isValid = false;
        boolean[] count = new boolean[4];
        for (int i = 0; i < str.length(); i++) {
            Character character = str.charAt(i);
            if(Character.isLetter(character)){
                if(Character.isUpperCase(character)){
                    count[0] = true;
                } else if(Character.isLowerCase(character)){
                    count[1] = true;
                }
            }else if(Character.isDigit(character)){
                count[2] = true;
            }else {
                count[3] = true;
            }
        }
        int validCount = 0;
        for (int i = 0; i < count.length; i++) {
            if(count[i]){
                validCount++;
            }
        }
        if(validCount >= 3){
            isValid = true;
        }
        return isValid;
    }

    public static boolean validRepeat(String str,int l,int r){
        if(r >= str.length()){
            return false;
        }
        if(str.substring(r).contains(str.substring(l,r))){
            return true;
        }else {
            return validRepeat(str,l+1,r+1);
        }
    }
}
