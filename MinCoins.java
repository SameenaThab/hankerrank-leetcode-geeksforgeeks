import java.util.*;
class MinCoins {

    public static void main(String[] args) {
        MinCoins sol = new MinCoins();
        int amount = 0;
        int[] coins = new int[]{1};
        System.out.println(sol.coinChange(coins,amount));
        // int[][] dp = new int[amount+1][4];
        // System.out.println(sol.makeChangeMem(amount,0,dp));
    }

    public int coinChange(int[] coins, int amount) {

        Integer[] sortedcoins = new Integer[coins.length];
        int i = 0;
        for (int value : coins) {
            sortedcoins[i++] = Integer.valueOf(value);
        }
        Arrays.sort(sortedcoins, Collections.reverseOrder());
        int count = Integer.MAX_VALUE;
        for(int k=0;k<sortedcoins.length;k++) {
            count = Math.max(count,makeChange(sortedcoins,amount,k,0));
        }
        if( count == Integer.MAX_VALUE) {
            return -1;
        } else
            return count;

    }

    int makeChange(Integer[] coins, int amount, int index, int count) {
        if (amount == 0)
            return count;
        else if (amount < 0)
            return Integer.MAX_VALUE;
        else if(index >= coins.length)
            return Integer.MAX_VALUE;
        int coinValue = coins[index];
        int min = Integer.MAX_VALUE;
        int i = 0;
        while(i*coinValue<=amount) {
            i++;
        }
        i = i*coinValue == amount? i: i-1;

        min = Math.min(min,makeChange(coins,amount-i*coinValue,index+1,count+i)); 
        return min;
    }

    class Solution {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount+1];
            Arrays.fill(dp,amount+1);
            dp[0]=0;
            for(int i=1;i<=amount;i++) {
                for(int coin:coins) {
                    if(i>=coin) {
                        dp[i]=Math.min(dp[i],1+dp[i-coin]);
                    }
                }
            }
            return dp[amount]>amount?-1:dp[amount];
        }
    }
}