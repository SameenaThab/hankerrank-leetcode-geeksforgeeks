import java.util.*;

class CoinChange {

    public static void main(String[] args) {
        CoinChange sol = new CoinChange();

        System.out.println("Solution: "+sol.coinChangeRecursive(new int[]{1,2,5},11));
        // System.out.println("Solution: "+sol.coinChange(new int[]{2},3));
        // System.out.println("Solution: "+sol.coinChange(new int[]{1},0));
        //takes forever
        // System.out.println("Solution: "+sol.coinChange(new int[]{186,419,83,408},6249));

        System.out.println("Solution: "+sol.coinChangeMem(new int[]{1,2,5},11));
        // System.out.println("Solution: "+sol.coinChangeMem(new int[]{2},3));
        // System.out.println("Solution: "+sol.coinChangeMem(new int[]{1},0));
        System.out.println("Solution: "+sol.coinChangeMem(new int[]{186,419,83,408},6249));
    }

    // takes forever not optimal
    public int coinChangeRecursive(int[] coins, int amount) {

        // System.out.println("here amount: "+amount);
        if(amount<0)
            return -1;
        else if(amount == 0)
            return 0;
        else {
            int min = Integer.MAX_VALUE;
            for(int coin:coins) {
                int result = coinChangeRecursive(coins, amount-coin);
                if(result >= 0 && result<min) {
                    min = 1+result;
                }
            }
            return min==Integer.MAX_VALUE? -1:min;
        }
    }

    /* 
    Time complexity : O(S*n). where S is the amount, n is denomination count.
    In the worst case the recursive tree of the algorithm has height of S and the algorithm solves only S subproblems because it caches precalculated solutions in a table.
    Each subproblem is computed with nn iterations, one by coin denomination. Therefore there is O(S*n) time complexity.
    Space complexity : O(S), where S is the amount to change We use extra space for the memoization table. 
    */
    public int coinChangeMem(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }
    
    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
          int res = coinChange(coins, rem - coin, count);
          if (res >= 0 && res < min)
            min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
          for (int j = 0; j < coins.length; j++) {
            if (coins[j] <= i) {
              dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
          }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}