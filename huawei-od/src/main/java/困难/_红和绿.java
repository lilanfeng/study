package 困难;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _红和绿 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            solution(sc.nextLine());
        }
    }
    public static void solution(String source){

        List<Integer> redList = new ArrayList<>();
        List<Integer> greenList = new ArrayList<>();
        char[] sourceArr = source.toCharArray();
        for (int i = 0; i < sourceArr.length; i++) {
            if(sourceArr[i] == 'R'){
                redList.add(i);
            }else if(sourceArr[i] == 'G'){
                greenList.add(i);
            }
        }
        List<Integer> tempList = new ArrayList<>();
        int redCount = 0;
        for (int i = redList.size() -1; i >= 0 ; i--) {
            if(greenList.size() > 0 && redList.get(i) > greenList.get(0)){
                tempList.add(redList.get(i));
                redCount++;
            }
            if(tempList.size() > 0 && redList.get(i) > tempList.get(tempList.size() -1)){
                tempList.add(redList.get(i));
                redCount++;
            }
        }

        List<Integer> tempGreenList = new ArrayList<>();
        int greenCount = 0;
        for (int i = 0; i < greenList.size() ; i++) {
            if(redList.size() > 0 && greenList.get(i) < redList.get(redList.size() -1)){
                tempGreenList.add(greenList.get(i));
                greenCount++;
            }
            if(tempGreenList.size() > 0 && greenList.get(i) < tempGreenList.get(0)){
                tempGreenList.add(greenList.get(i));
                greenCount++;
            }
        }
        int min = Math.min(redCount,greenCount);
        System.out.println(min);
    }
}
