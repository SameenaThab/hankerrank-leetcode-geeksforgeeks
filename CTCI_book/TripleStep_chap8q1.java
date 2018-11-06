+import java.util.*;
import java.lang.*;
import java.io.*;

//Subarray with given sum

class TripleStep_chap8q1
{
	public static void main(String[] args)
	{
		System.out.println(countWays(8));
	}

	public static int countWays(int n)
	{
		int[] dp = new int[n];
		dp[0]=0;
		dp[1]=1;
		or (int i=2; i<n; i++) 
        { 
           dp[i] = 0; 
           for (int j=1; j<=3 && j<=i; j++) 
             dp[i] += dp[i-j]; 
        }

        return dp[n-1]
	}
}
