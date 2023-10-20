package 简单;

import java.util.Scanner;

public class _HJ001_字符串最后一个单词的长度 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String test = in.nextLine();
            if(test.isEmpty()){
                System.out.println(0);
                continue;
            }
            String[] testList = test.split(" ");
            System.out.println(testList[testList.length-1].length());
        }


    }
}
