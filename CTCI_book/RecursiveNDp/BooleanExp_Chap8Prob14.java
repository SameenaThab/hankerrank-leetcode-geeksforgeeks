import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;
import java.lang.reflect.Array;

public class  BooleanExp_Chap8Prob14 {

    public static void main(String[] args) {
        BooleanExp_Chap8Prob14 sol = new BooleanExp_Chap8Prob14();
        Map<String,Integer> map = new HashMap<String,Integer>();
        System.out.println("Solution: "+sol.countWays("0&0&0&1^1|0",true,map));
    }

    int countWays(String exp,boolean result,Map<String,Integer> map) {
        int res = 0;
        if(exp.length() == 0) {
            return 0;
        }
        //base case
        if(exp.length() == 1) {
            boolean val = exp.equals("1")?true:false;
            res = result == val? 1:0;
        }

        if(map.containsKey(exp+result))
            return map.get(exp+result);
        for(int i=1;i<exp.length();i+=2) {
            String left = exp.substring(0,i);
            String right = exp.substring(i+1);
            char operator = exp.charAt(i);
            int leftTruths = countWays(left, true, map);
            int leftFalse = countWays(left,false, map);
            int rightTruths = countWays(right, true, map);
            int rightFalse = countWays(right, false, map);
            int totalWays = (leftFalse+leftTruths)*(rightFalse+rightTruths);
            int totalTruths;
            if(operator == '|') {
                totalTruths = totalWays-(leftFalse*rightFalse);
            } else if(operator == '&') {
                totalTruths = (leftTruths*rightTruths);                  
            } else {
                totalTruths = (leftTruths*rightFalse)+(leftFalse*rightTruths);                            
            }
            int subWays = result? totalTruths:totalWays-totalTruths;
            res+=subWays;
        }
        map.put(exp+result,res);
        return res;
    }

}
