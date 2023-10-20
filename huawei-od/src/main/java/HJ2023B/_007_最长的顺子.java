package HJ2023B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 *  斗地主起源与湖北十堰房县，据说是一位叫吴修全的年轻人根据当地流行的扑克玩法"跑的快"改编的，如今已风靡整个中国，并流行与互联网上。
 *  牌型：单顺，又称顺子，最少5张牌，最多12张牌（3。。。A）不能有2，也不能有大小王，不计花色。
 *  例如：3-4-5-6-7-8，7-8-9-10-J-Q
 *  可用的牌： 3 4 5 6 7 8 9 10 J Q K A 2 B（小王）  C（大王） 共有 13* 4 + 2 = 54
 *  输入：
 *   1，手上有的牌
 *   2，已经出过的牌（包括对手出的和自己出的牌）
 *   输出：
 *     对手可能构成的最长的顺子（如果有相同长度的顺子，输出牌面最大的那一个）
 *     如果无法构成顺子，则输出NO-CHAIN
 *
 *  输入描述：
 *  输入第一行为当前手中的牌
 *  输入的第二行为已经出过的牌
 *
 *  输出描述：
 *    最长的顺子
 *
 *    用例
 *
 *    输入： 3-3-3-3-4-4-5-5-6-7-8-9-10-J-Q-K-A
 *         4-5-6-7-8-8-8
 *    输出： 9-10-J-Q-K-A
 *
 *    输入： 3-3-3-3-8-8-8-8
 *            K-K-K-K
 *
 *    输出： NO-CHAIN
 */
public class _007_最长的顺子 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        getMaxChain(sc.next(),sc.next());
    }

    public static void getMaxChain(String myCard,String usedCard){
        int[] cardCount = {0,0,0,4,4,4,4,4,4,4,4,4,4,4,4,0};
        List<String> myCardList = Arrays.asList(myCard.split("-"));
        List<String> usedCardList = Arrays.asList(usedCard.split("-"));
        for(String card:myCardList){
            int idx = Integer.parseInt(mapToNumber(card));
            cardCount[idx]--;
        }
        for(String card:usedCardList){
            int idx = Integer.parseInt(mapToNumber(card));
            cardCount[idx]--;
        }

        List<String> result =  new ArrayList<>();
        List<String> path = new ArrayList<>();

        for (int i = 3; i < cardCount.length; i++) {
            if(cardCount[i] > 0){
                path.add(String.valueOf(i));
            }else {
                if(path.size() >= 5){
                    StringBuilder stringBuilder = new StringBuilder();
                    for(String index:path){
                        stringBuilder.append(mapToNumber(index)).append("-");
                    }
                    stringBuilder.deleteCharAt(stringBuilder.length() -1);
                    result.add(stringBuilder.toString());
                }
                path.clear();
            }
        }

        if(result.size() ==0){
            System.out.println("NO-CHAIN");
            return;
        }
        Collections.sort(result,new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                return o1.length() != o2.length()? o1.length() - o2.length(): o1.compareTo(o2);
            }
        });
        System.out.println(result.get(0));

    }

    private static String mapToNumber(String pai){
        switch (pai){
            case "J":
                return "11";
            case "11":
                return "J";
            case "Q":
                return "12";
            case "12":
                return "Q";
            case "K":
                return "13";
            case "13":
                return "K";
            case "A":
                return "14";
            case "14":
                return "A";
            default:
                return pai;
        }
    }

}
