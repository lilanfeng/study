package HJ2023B200;

import java.lang.ref.PhantomReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 某购物城有m个商铺，现决定举办一场活动选出人气最高店铺。
 * 活动共有n位市民参与，每位市民只能投一票，但1号店铺如果给该市民发放 q 元的购物补贴，该市民会改为投1号店铺。
 * 请计算1号店铺需要最少发放多少元购物补贴才能成为人气最高店铺（即获得的票数要大于其他店铺），如果1号店铺本身就是票数最高店铺，返回0。
 *
 * 输入描述
 * 第一行为小写逗号分割的两个整数n，m，其中：
 * 第一个整数n表示参与的市民总数
 * 第二个整数m代表店铺总数
 * 1 ≤ n,m ≤ 3000
 * 第2到n+1行，每行为小写逗号分割的两个整数p，q，表示市民的意向投票情况，其中每行的：
 * 第一个整数p表示该市民意向投票给p号店铺
 * 第二个整数q表示其改投1号店铺所需给予的q元购物补贴
 *
 * 1 ≤ p ≤ m
 * 1 ≤ g ≤ 10^9
 * 不考虑输入的格式问题
 *
 * 输出描述
 * 1号店铺需要最少发放购物补贴金额
 *
 * 用例1
 * 输入
 *
 * 5,5
 * 2,10
 * 3,20
 * 4,30
 * 5,40
 * 5,90
 * 输出
 * 50
 * 说明
 * 有5个人参与，共5个店铺。
 * 如果选择发放 10元+20元+30元=60元 的补贴来抢2,3.4号店铺的票，总共发放了60元补贴（5号店铺有2票，1号店铺要3票才能胜出）
 * 如果选择发放 10元+40元=50元 的补贴来抢2,5号店铺的票，总共发放了50元补贴（抢了5号店铺的票后，现在1号店铺只要2票就能胜出）
 * 所以最少发放50元补贴
 *
 * 用例2
 * 输入
 * 5,5
 * 2,10
 * 3,20
 * 4,30
 * 5,80
 * 5,90
 * 输出
 * 60

 * 说明
 * 有5个人参与，共5个店铺。
 * 如果选择发放 10元+20元+30元=60元 的补贴来抢2,3,4号店铺的票，总共发放了60元补贴(5号店铺有2票，1号店铺要3票才能胜出)
 * 如果选择发放 10元+80元=90元 的补贴来抢2,5号店铺的票，总共发放了90元补贴(抢了5号店铺的票后，现在1号店铺只要2票就能胜出)
 *
 * 所以最少发放60元补贴
 *
 * 题目解析
 * 上面的 Java 代码和转换后的 Node.js 代码是为了解决一个问题：给定一组市民对商店的投票信息，计算使得1号商店成为票数最高商店所需的最小补贴金额。
 * 下面是详细的解题思路和解题方法：
 *
 * 输入处理：首先，我们需要处理输入。 我们需要读取两个整数 n 和 m，分别表示参与的市民总数和店铺总数。接下来，我们需要读取 n 行输入，
 * 每行包含两个整数，分别表示市民投票的店铺 ID 和改投 1 号店铺所需的补贴金额。
 *
 * 初始化数据结构：我们需要初始化两个数据结构来存储投票信息。一个是 voters 列表，用于存储市民投票信息；另一个是 shopVotes 映射，用于存储每个商店的票数。我们还需要初始化一个变量
 * minSubsidy，用于存储最小补贴金额，初始值为 Integer.MAX_VALUE（Java）或 Number.MAX_VALUE（Node.js）。
 *
 * 读取投票信息：遍历输入的每一行，将市民投票信息添加到 voters 列表中，并更新 shopVotes 映射中的票数。注意，如果市民已经投票给 1 号店铺，我们不需要将其添加到 voters 列表中，因为我们不需要为这些市民发放补贴。
 *
 * 计算最小补贴金额：我们使用递归函数 calculateSubsidy 来计算最小补贴金额。这个函数有四个参数：voters 列表、当前处理的市民索引 index、当前已发放的补贴金额 currentSubsidy 和
 * shopVotes 映射。我们使用回溯法来尝试所有可能的补贴发放方案，并在每个方案中检查 1 号店铺是否成为票数最高的店铺。如果是，则更新 minSubsidy 变量。
 *
 * 递归函数的实现：在 calculateSubsidy 函数中，我们首先检查边界条件：如果所有市民都已处理（即 index === voters.length），则检查 1 号店铺是否是票数最高的店铺，如果是且当前补贴金额小于
 * minSubsidy，则更新 minSubsidy。接下来，我们处理当前市民的投票信息。我们尝试两种方案：不发放补贴和发放补贴。在不发放补贴的方案中，我们直接递归处理下一个市民；在发放补贴的方案中，我们更新 shopVotes
 * 映射中的票数，递归处理下一个市民，然后回溯恢复 shopVotes 映射中的票数。
 *
 * 判断 1 号店铺是否是票数最高的店铺：我们使用一个辅助函数 isTop1 来判断 1 号店铺是否是票数最高的店铺。这个函数遍历 shopVotes 映射，找到除 1 号店铺外的最高票数，然后比较 1 号店铺的票数是否大于这个最高票数。
 *
 * 输出结果：最后，我们输出计算得到的最小补贴金额 minSubsidy。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132612602
 */
public class _006_人气最高的店铺 {

    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        String[] firsts = first.split(",");
        int n = Integer.parseInt(firsts[0]);
        int m = Integer.parseInt(firsts[1]);
        //需要改票的花费金额
        List<int[]> voterList= new ArrayList<>();
        //投票数量
        Map<Integer,Integer> shopVoteMap = new HashMap<>();
        shopVoteMap.put(1,0);

        for (int i = 0; i < n; i++) {
            int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            if(nums[0] != 1){
                voterList.add(new int[]{nums[0],nums[1]});
            }
            shopVoteMap.put(nums[0],shopVoteMap.getOrDefault(nums[0],0)+1);
        }
        findSubsidy(voterList,0,0,shopVoteMap);
        System.out.println(min);

    }

    /**
     *  * 5,5
     *  * 2,10
     *  * 3,20
     *  * 4,30
     *  * 5,40
     *  * 5,90
     * @param voterList
     * @param index
     * @param currentSubsidy
     * @param shopVoteMap
     */
    public static void findSubsidy(List<int[]> voterList,int index,int currentSubsidy,Map<Integer,Integer> shopVoteMap){
        if(index == voterList.size()){
            if(isTop(shopVoteMap) && min > currentSubsidy){
                min = currentSubsidy;
            }
            return;
        }

        int[] currentVoter = voterList.get(index);
        int shopId = currentVoter[0];
        int price = currentVoter[1];

        //尝试不发放补贴
        findSubsidy(voterList,index+1,currentSubsidy,shopVoteMap);

        shopVoteMap.put(shopId,shopVoteMap.get(shopId) -1);
        shopVoteMap.put(1,shopVoteMap.get(1) +1);
        //尝试发放补贴
        findSubsidy(voterList,index + 1,currentSubsidy + price,shopVoteMap);

        //恢复发放补贴
        shopVoteMap.put(shopId,shopVoteMap.get(shopId) + 1);
        shopVoteMap.put(1,shopVoteMap.get(1) - 1);
    }

    public static boolean isTop(Map<Integer,Integer> shopVoteMap){
        int maxVote = 0;
        for(Map.Entry<Integer,Integer> entry : shopVoteMap.entrySet()){
            if(entry.getKey() != 1 && entry.getValue() >= maxVote){
                maxVote = entry.getValue();
            }
        }
        return shopVoteMap.get(1) > maxVote;
    }

}
