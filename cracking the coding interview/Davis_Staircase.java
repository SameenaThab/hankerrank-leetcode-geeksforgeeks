import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem

public class Davis_Staircase {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        for(int a0 = 0; a0 < s; a0++){
            int n = in.nextInt();
            System.out.println(findways(n));
        }
        
    }
    
    public static int findways(int n)
    {
        int[][] dp=new int[n+1][4];
        for(int i=0;i<3;i++)
            dp[0][i]=0;
        for(int i=0;i<=n;i++)
            dp[i][0]=0;
        for(int i=1;i<=n;i++)
            for(int j=1;j<=3;j++)
            {
                if(i-(j)<0)
                    dp[i][j]=0+dp[i][j-1];
                else if(i-(j)==0)
                    dp[i][j]=1+dp[i][j-1];
                else
                {
                    int rem=i-(j);
                    dp[i][j]=dp[rem][3]+dp[i][j-1];
                }
            }
        
        return dp[n][3];
            
    }
    /*
    public static int findways(int n)
    {
        return findwayshelper(n,1)+findwayshelper(n,2)+findwayshelper(n,3);
    }
    
    public static int findwayshelper(int n,int i)
    {
        if(n-i<0)
            return 0;
        else if(n-i==0)
            return 1;
        else
        {
            return findwayshelper(n-i,1)+findwayshelper(n-i,2)+findwayshelper(n-i,3);
        }
    }
    */
}
