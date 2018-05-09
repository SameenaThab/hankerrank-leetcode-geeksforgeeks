import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
https://www.hackerrank.com/challenges/coin-change/problem
*/

public class CoinChange {

        /*
    static long getWays(long n, long[] c){
        long count=0;
        for(int i=0;i<c.length;i++)
            count+=getways(n,c,i);
        return count;
    }
   

    static long getways(long n,long[] c,int index)
    {
        if(n-c[index]==0)
            return 1;
        else if(n<c[index])
            return 0;
        else
        {
            long count=0;
            for(int i=index;i<c.length;i++)
                count+=getways(n-c[index],c,i);
            return count;
        }
    }
    */
    
        static long getWays(long n,long[] c)
    {
        int x=(int)n+1;
        int y=c.length+1;
        long[][] dp=new long[x][y];
        dp[0][0]=0;
        for(int i=0;i<y;i++)
            dp[0][i]=0;
        for(int i=0;i<x;i++)
            dp[i][0]=0;
        for(int i=1;i<x;i++)
            for(int j=1;j<y;j++)
            {
                if(i==(int)c[j-1])
                    dp[i][j]=dp[i][j-1]+1;
                else if(i<c[j-1])
                    dp[i][j]=dp[i][j-1];
                else
                {
                    int rem=i-(int)c[j-1];
                     dp[i][j]=dp[i][j-1]+dp[rem][j];                   
                }


            }
        return dp[x-1][y-1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] c = new long[m];
        for(int c_i=0; c_i < m; c_i++){
            c[c_i] = in.nextLong();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        long ways = getWays(n, c);
        System.out.println(ways);
    }

}
