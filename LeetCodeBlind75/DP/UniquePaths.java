import java.util.Arrays;

class UniquePaths {

    //https://leetcode.com/problems/unique-paths/
    public int uniquePaths(int m, int n) {
        int[][] mem = new int[m][n];
        for(int[] rw : mem) {
            Arrays.fill(rw,-1);
        }
        return uniquePaths(0, 0, m, n, mem);
    }

    private int uniquePaths(int i, int j, int m, int n, int[][] mem) {
        if(i == m-1 && j == n-1)
            return 1;
        if(i<0 || i>=m || j<0 || j>=n)
            return 0;
        if(mem[i][j] != -1)
            return mem[i][j];
        mem[i][j] = uniquePaths(i+1,j,m,n,mem)+uniquePaths(i, j+1, m, n, mem);
        return mem[i][j];
    }

    /* 
    intialize dp = in[m+1][n+1]
    every row and column index represent m and n values, value represent number paths for that ma dn n value
    dp[0][0] = 1;
    dp[row=0][j] = 1; for all 0<j<n+1
    dp[i][col=0] = 1 for all 0<i<m+1
    Algorithm
    Initiate 2D array d[m][n] = number of paths. To start, put number of paths equal to 1 for the first row and the first column. For the simplicity, one could initiate the whole 2D array by ones.
    Iterate over all "inner" cells: d[col][row] = d[col - 1][row] + d[col][row - 1].
    Return d[m - 1][n - 1].
    */
    public int uniquePathsIterative(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<n;i++)
            dp[0][i]=1;
        for(int j=0;j<m;j++)
            dp[j][0]=1;
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];        
    }   


    public static void main(String[] args) {
        UniquePaths sol = new UniquePaths();

        System.out.println("Solution: "+sol.uniquePaths(3,2));
        System.out.println("Solution: "+sol.uniquePaths(7,3));


        System.out.println("Solution: "+sol.uniquePathsIterative(3,2));
        System.out.println("Solution: "+sol.uniquePathsIterative(7,3));
    }
}