package 简单;

import java.util.Scanner;

/**
 * 描述
 * 考试题目和要点：
 * 1、中文大写金额数字前应标明“人民币”字样。中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整等字样填写。
 * 2、中文大写金额数字到“元”为止的，在“元”之后，应写“整字，如532.00应写成“人民币伍佰叁拾贰元整”。在”角“和”分“后面不写”整字。
 * 3、阿拉伯数字中间有“0”时，中文大写要写“零”字，阿拉伯数字中间连续有几个“0”时，中文大写金额中间只写一个“零”字，如6007.14，应写成“人民币陆仟零柒元壹角肆分“。
 * 4、10应写作“拾”，100应写作“壹佰”。例如，1010.00应写作“人民币壹仟零拾元整”，110.00应写作“人民币壹佰拾元整”
 * 5、十万以上的数字接千不用加“零”，例如，30105000.00应写作“人民币叁仟零拾万伍仟元整”
 *
 * 输入描述：
 * 输入一个double数
 *
 * 输出描述：
 * 输出人民币格式
 *
 * 示例1
 * 输入：
 * 151121.15
 * 复制
 * 输出：
 * 人民币拾伍万壹仟壹佰贰拾壹元壹角伍分
 * 复制
 * 示例2
 * 输入：
 * 1010.00
 * 复制
 * 输出：
 * 人民币壹仟零拾元整
 */
public class _HJ095_人民币转换 {
    public static  char[] bigNum = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
    public static char[] unit = {' ','拾','佰','仟'};
    public static char[] unit2 = {'元','万','亿'};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            solution(sc.nextLine());
        }
    }

    public static void solution(String money){
        StringBuilder result = new StringBuilder();
        result.append("人民币");
        String[] moneyArr = money.split("\\.");
        result.append(convertBig(moneyArr[0]));
        result.append(covertSmall(moneyArr[1]));
        System.out.println(result.toString());

    }

    public static String convertBig(String big){
        if("0".equals(big)){
            return "";
        }
        String res = "";
        int index = 0;
        while (big.length() > 0){
            String current = big.length() > 4 ? big.substring(big.length() -4) : big;
            res = readNum(current) + unit2[index++] + res;
            big = big.length() > 4 ? big.substring(0,big.length() - 4) :"";
        }
        return res;
    }

    public static String covertSmall(String small){
        if("0".equals(small)){
            return "整";
        }
        int jNum = Character.getNumericValue(small.charAt(0));
        StringBuffer result = new StringBuffer();
        if(small.length() == 2){
            int fNum = Character.getNumericValue(small.charAt(1));
            if(jNum >  0){
                if(fNum > 0){
                    result.append(bigNum[jNum]+"角").append(bigNum[fNum] + "分");
                }else {
                    result.append(bigNum[jNum]+"角");
                }
            } else {
                result.append(bigNum[fNum] + "分");
            }
        }else {
            if(jNum > 0){
                result.append(bigNum[jNum] + "角");
            }
        }
        return result.toString();

    }

    /**
     * 数字转换
     * @param s
     * @return
     */
    public static String readNum(String s){
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            int u = s.length() - 1 -i;
            int b = Character.getNumericValue(s.charAt(i));
            if( b == 0 &&  i + 1 < s.length() && s.charAt(i+1) == '0'){
                continue;
            }

            if (!(b == 1 && i +1 < s.length() && u == 1)) {
                res.append(bigNum[b]);
            }
            if(b != 0){
                res.append(unit[u]);
            }
        }

        String resNum = res.toString().trim();
        if(resNum.charAt(resNum.length() -1 ) == '零'){
            resNum = resNum.substring(0,resNum.length() -1);
        }
        return resNum;
    }


}
