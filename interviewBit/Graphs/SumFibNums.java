
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/sum-of-fibonacci-numbers/

0 1 1 2 3 5 8 13 21

n = 0 -> 0
n = 1 -> 1
n = 2 -> 1
n = 3 -> 1 
n = 4 -> n(4-3)+1 = 2
n = 5 -> 0
n = 6 -> n(6-5)+1 = 2

*/





public class SumFibNums {

    public static void main(String[] args) {
        SumFibNums sol = new SumFibNums();        
        System.out.println("Solution: "+sol.fibsum(4));
        System.out.println("Solution: "+sol.fibsumMem(4));
    }

    public int fibsum(int n) {
        if(n == 0)
            return 0;
        int a = 0;
        int b = 1;
        while(a+b <= n) {
            int fib = a+b;
            a = b;
            b = fib;
        }
        // b is the fibnocci number <=n
        return fibsum(n-b)+1;
    }

    public int fibsumMem(int n) {
        if(n == 0)
            return 0;
        List<Integer> series = new ArrayList<Integer>();
        series.add(1);
        series.add(1);
        int a = 0;
        int b = 1;
        while(series.get(a)+series.get(b) <= n) {
            series.add(series.get(a)+series.get(b));
            a=b;
            b=b+1;
        }
        int count=0;
        for(int i=series.size()-1;i>=0;i--) {    
            if(n >= series.get(i)) {
                n = n-series.get(i);
                count++;
            }
            if(n == 0)
                break;
        }
        return count;
    }
}
