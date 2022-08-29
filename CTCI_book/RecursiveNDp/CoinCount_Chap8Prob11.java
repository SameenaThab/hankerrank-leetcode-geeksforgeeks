import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class  CoinCount_Chap8Prob11 {
    int[] coins;

    CoinCount_Chap8Prob11() {
        coins = new int[]{25,10,5,1};
    }
    public static void main(String[] args) {
        CoinCount_Chap8Prob11 sol = new CoinCount_Chap8Prob11();
        int amount = 100;
        System.out.println(sol.makeChange(amount,0));
        int[][] dp = new int[amount+1][4];
        System.out.println(sol.makeChangeMem(amount,0,dp));
        int[] coins2 = new int[]{1,5,10,25};
        System.out.println(sol.countWaysIterative(amount,coins2));
    }


    /* 
    We first subtract all multiple combination of single coin until amount becomes less than 0
    Amount 50 using quarters (25) -> 25 using 0  quarter 
                                  -> 25 using 1  quarter ->25 
                                  -> 25 using 2  quarter -> 0
    25 using 0 quarter  ->   25 using 0 dimes (10)
                        -> 25 using 2 dimes   -> 5
    25 using 0 dimes  -> 25 using 0 nickles (5)   
                    -> 25 using 2 nickles -> 15
                    -> 25 using 3 nickles -> 0
    25 using 2 dimes -> 5 -> 5 using 0 nickles 
                        -> 5 using 1 nickle -> 0     
    */
    int makeChange(int amount,int index) {
        // this way we do not count until we are exhausted with options
        // if we return 1 when amount is 0 then we are doing the same mistake

        // System.out.println("amount: "+amount+" index: "+index);
        //    if(amount == 0) return 1; // this is also correct but below condition is better,
                                    //  as stops recursion before
       if(index == coins.length-1) return 1; //bcoz we know indx coins-length-1 carries 1 cent
                                            // which is always 1 way
    //    if(index > coins.length-1) return 0;  no need as we stop at index = coins.length-1
       int coinValue = coins[index];
       int count = 0;
       for(int i=0;i*coinValue <= amount;i++) {
           int remaining = amount-i*coinValue;
           count+=makeChange(remaining,index+1); 
       }
       return count;
    }

    int makeChangeMem(int amount,int index,int[][] dp) {
        if(dp[amount][index]>0)
            return dp[amount][index];
        if(index == coins.length-1) return 1; 
        int coinValue = coins[index];
        for(int i=0;i*coinValue <= amount;i++) {
            int remaining = amount-i*coinValue;
            dp[amount][index]+=makeChange(remaining,index+1); 
        }
        return dp[amount][index];
    }

    public int countWaysIterative(int amount, int[] denoms) {
        int[][] mem = new int[amount+1][denoms.length];
        for(int d=0;d<mem[0].length;d++) {
            mem[0][d]=1;
        }
        for(int i=1;i<=amount;i++) {
            for(int j=0;j<mem[0].length;j++) {
                if(denoms[j]>i) {
                    mem[i][j]=j==0?0:mem[i][j-1];;
                } else if(denoms[j]==i) {
                    mem[i][j]=j==0?1:1+mem[i][j-1];
                } else {
                    int ways=0;
                    int denom = denoms[j];
                    for(int k=0;k*denom<=i;k++) {
                        int newRem = i-k*denom;
                        if(j!=0) 
                            ways+=mem[newRem][j-1];
                        if(newRem == 0 && j==0)
                            ways+=1;
                        
                    }
                    mem[i][j]=ways;
                }
            }
        }
        return mem[amount][denoms.length-1];
    }

/* 
 ************COMMON MISTAKE **************
    This is wrong becasue is repeats itself 
    for example amount = 10
    at recursive call countWays(10-5 = 5) -> it gives {2*5},{5+5*1} => 2 ways
    at recursive call countWays(10-1 = 9) = it again give {5+4*1} ,{9*1} => 2 ways
    But it should have been 1 way as we have already counted {5+5*1} combination 
    countWays(10-1=9) = {5+4*1} = is basically countWays(10) -> {5+5*1}
    
    Therfore its dp solution is also wrong . Hence below code is commented*/

    // int countWays(int rem) {
    //     System.out.println("rem: "+rem);
    //     int count = 0;
    //     if(rem < 0)
    //         return count;
    //     if(rem == 0) {
    //         return 1;
    //     }
    //     for(int coin:coins) {
    //         int newRem = rem-coin;
    //         int value = countWays(newRem);
    //         System.out.println("newRem: "+newRem+" value: "+value);
    //         count+=value;
    //     }
    //     return count;
    // }

    // int countWaysDp(int n) {
    //     int[][] dp = new int[n+1][5];
    //     int[] change = new int[]{0,1,5,10,25};
    //     for(int i=0;i<5;i++) {
    //         dp[0][i] = 0;
    //     }
    //     for(int i=0;i<=n;i++) {
    //         dp[i][0]=0;
    //     }
    //     for(int i=1;i<=n;i++) {
    //         for(int j=1;j<5;j++) {
    //             if(i == change[j])
    //                 dp[i][j]=dp[i][j-1]+1;
    //             else if(i < change[j])
    //                 dp[i][j]=dp[i][j-1];
    //             else {
    //                 int rem = i-change[j];
    //                 dp[i][j]=dp[i][j-1]+dp[rem][4];
    //             }
    //         }
    //     }
    //     return dp[n][4];
    // }

}
