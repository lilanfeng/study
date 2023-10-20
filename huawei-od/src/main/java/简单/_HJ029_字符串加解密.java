package 简单;

import java.util.Locale;
import java.util.Scanner;

/**
 * 描述
 * 对输入的字符串进行加解密，并输出。
 *
 * 加密方法为：
 *
 * 当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
 *
 * 当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；
 *
 * 其他字符不做变化。
 *
 * 解密方法为加密的逆过程。
 * 数据范围：输入的两个字符串长度满足 1≤n≤1000  ，保证输入的字符串都是只由大小写字母或者数字组成
 * 输入描述：
 * 第一行输入一串要加密的密码
 * 第二行输入一串加过密的密码
 * 示例1
 * 输入：
 * abcdefg
 * BCDEFGH
 * 输出：
 * BCDEFGH
 * abcdefg
 */
public class _HJ029_字符串加解密 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String a = sc.nextLine();
            String b = sc.nextLine();
            encode(a);
            decode(b);
        }
    }

    public static void encode(String str){
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if(Character.isLetter(character)){
                if(character == 'z' ) {
                    character = 'a';
                }else if(character == 'Z'){
                    character = 'A';
                }else {
                    character = (char)(character + 1);
                }

                if(Character.isUpperCase(character)){
                    System.out.print(String.valueOf(character).toLowerCase());
                }else {
                    System.out.print(String.valueOf(character).toUpperCase());
                }
            }else if(Character.isDigit(character)){
                try {
                    int number = Integer.parseInt(String.valueOf(character));
                    System.out.print((number +1)%10);
                }catch (NumberFormatException e){
                }
            }else {
                System.out.print(character);
            }
        }
    }

    public static void decode(String str){
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if(Character.isLetter(character)){
                if(character == 'a' ) {
                    character = 'z';
                }else if(character == 'A'){
                    character = 'Z';
                }else {
                    character = (char)(character  - 1);
                }

                if(Character.isUpperCase(character)){
                    System.out.print(String.valueOf(character).toLowerCase());
                }else {
                    System.out.print(String.valueOf(character).toUpperCase());
                }
            }else if(Character.isDigit(character)){
                try {
                    int number = Integer.parseInt(String.valueOf(character));
                    if(number == 0){
                        System.out.print((9));
                    }else {
                        System.out.print((number - 1));
                    }

                }catch (NumberFormatException e){
                }
            }else {
                System.out.print(character);
            }
        }
    }

}
