package 堆栈;

import java.util.Scanner;

public class _塔子哥喝茶问题 {

     public static void main(String[] args) {
         final int maxn = 1010;
         long[] stack = new long[maxn];        int top = 0;
         Scanner scanner = new Scanner(System.in);
         while (scanner.hasNextLong()) {
             long x = scanner.nextLong();
             // 读入数据
              while (true) {
                  boolean flag = false;
                  long tmp = 0;
                  for (int i = top; i > 0; i--) {
                      // 暴力查找是否和为x的数
                      tmp += stack[i];
                      if (tmp == x) {
                          // 存在一段和为x，合并，并更新栈
                          x = x + tmp;
                          top = i - 1;
                          flag = true;
                          break;
                      }
                  }
                  if (!flag) {
                      break;
                  }
              }
              top++;
              stack[top] = x;
         }
         scanner.close();
         while (top > 0) {
             System.out.print(stack[top] + " ");
             top--;
         }
     }
}
