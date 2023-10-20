package 简单;

import java.util.Scanner;

/**
 *
 */
public class _HJ021_简单密码 {

    static final int[] lowerCaseMap = {2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            solution(sc.nextLine());
        }
    }

    public static void solution(String source){
        StringBuilder builder = new StringBuilder();
        for(char c :source.toCharArray()){
            if(Character.isUpperCase(c)){
                c = Character.toLowerCase(c);
                c = (char)((c -'a' +1) % 26 + 'a');
                builder.append(c);
            }else if(Character.isLowerCase(c)){
                builder.append(lowerCaseMap[c -'a']);
            }else {
                builder.append(c);
            }
        }
        System.out.println(builder.toString());
    }
}
