package 全部;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description a0 = 0  a1 = 0  a2= 1   a(n) = a(n-1) + a(n-2) + a(n-3)
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _509_斐波那契数 {

    /**
     * 递归求解
     * @param n
     * @return
     */
    public Long  compulte(Long n) {
        if (n <= 1) {
            return 0L;
        }
        if (n == 2) {
            return 1L;
        }
        return compulte(n - 1) + compulte(n - 2) + compulte(n - 3);
    }

    public static void main(String[] args) {
        System.out.println(new _509_斐波那契数().compulte_one(10000000L));
    }

    public Long  compulte_one(Long n) {
        if (n <= 1) {
            return 0L;
        }
        if (n == 2) {
            return 1L;
        }
        Map<Integer,Long> map = new HashMap<>();
        map.put(0,0L);
        map.put(1,0L);
        map.put(2,1L);

        for (int i = 3; i <= n; i++) {
            Long sum = map.get(0) + map.get(1) + map.get(2);
            map.put(0, map.get(1));
            map.put(1, map.get(2));
            map.put(2, sum);
        }
       return map.get(2);
    }


}
