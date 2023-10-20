package 简单;

import java.util.Scanner;

public class _HJ034_图片整理 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()){
            int a[] = new int[128];
            String str = sc.next();
            for (int i = 0; i < str.length(); i++) {
                int k = str.charAt(i);
                a[k]++;
            }

            for (int i = 48; i < a.length; i++) {
                if(a[i] != 0){
                    for (int j = 0; j < a[i]; j++) {
                        System.out.print((char)i);
                    }
                }
            }
            System.out.println();
        }
    }
}
