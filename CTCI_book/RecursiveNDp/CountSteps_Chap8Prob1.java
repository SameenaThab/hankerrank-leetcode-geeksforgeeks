import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class  CountSteps_Chap8Prob1 {
    /*
          4
      2       6
    1   3   5     7
                      9      
    */
        
    public static void main(String[] args) {
        CountSteps_Chap8Prob1 sol = new CountSteps_Chap8Prob1();
        System.out.println("Count steps: "+sol.countSteps(8));
        System.out.println("Count steps: "+sol.countStepsMem(8));
    }

    public int countSteps(int n) {
        if(n == 0)
            return 1;
        else if(n < 0)
            return 0;
        else {
            return countSteps(n-1)+countSteps(n-2)+countSteps(n-3);
        }
    }

    public int countStepsMem(int n) {
        int[] cache = new int[n+1];
        cache[0]=1;
        cache[1]=1;
        cache[2]=2;
        for(int i=3;i<=n;i++) {
            cache[i]=cache[i-1]+cache[i-2]+cache[i-3];
        }
        return cache[n];
    }
}
