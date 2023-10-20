package 简单;

import java.util.Scanner;

/**
 *
 */
public class _HJ013_句子逆转 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            solution(sc.nextLine());
        }
    }

    public static void solution(String source){
        String[] sourceArr = source.split(" ");
        for (int i = sourceArr.length -1; i >= 0; i--) {
            System.out.print(sourceArr[i] + " ");
        }
        System.out.println();
    }
}
