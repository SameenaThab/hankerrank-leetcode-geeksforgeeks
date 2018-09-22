/*
Given a number of dollars, n, and a list of dollar values for m distinct coins, C={c0,c1,c2,.....cm-1}, find and print the number of different ways you can make change for  dollars if each coin is available in an infinite quantity.
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Coin_Change {
    /*
    public static long makeChange(int[] coins, int money) {
       return makeChangeHelper(coins,money,0);
    }
    
        public static long makeChangeHelper(int[] coins,int money,int index)
    {
        int rem=0;
        long count=0;
        if(money==0)
            return 1;
        if(index>=coins.length)
            return 0;
        while(rem<=money)
        {
            int temp=money-rem;
           count+=makeChangeHelper(coins,temp,index+1);
               rem+=coins[index];
        }
        
        return count;
    }
    */
    
    public static long makeChange(int[] coins, int money) {
        int m=money+1;
        int n=coins.length+1;
        long[][] dp=new long[m][n];
        for(int i=0;i<n;i++)
            dp[0][i]=0;
        for(int i=0;i<m;i++)
            dp[i][0]=0;
        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
            {
                if(i==coins[j-1])
                    dp[i][j]=dp[i][j-1]+1;
                else if(i<coins[j-1])
                    dp[i][j]=dp[i][j-1];
                else
                {
                    dp[i][j]=dp[i][j-1]+dp[i-coins[j-1]][j];
                }
            }
        return dp[m-1][n-1];
    }

    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int coins[] = new int[m];
        for(int coins_i=0; coins_i < m; coins_i++){
            coins[coins_i] = in.nextInt();
        }
        System.out.println(makeChange(coins, n));
    }
}
