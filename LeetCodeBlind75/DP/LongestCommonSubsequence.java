import java.util.*;

class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence sol = new LongestCommonSubsequence();

        System.out.println("Solution: "+sol.longestCommonSubsequence("abcde","ace"));
        System.out.println("Solution: "+sol.longestCommonSubsequence("abc","abc"));
        System.out.println("Solution: "+sol.longestCommonSubsequence("abc","def"));

        System.out.println("Solution: "+sol.longestCommonSubsequenceMem("abcde","ace"));
        System.out.println("Solution: "+sol.longestCommonSubsequenceMem("abc","abc"));
        System.out.println("Solution: "+sol.longestCommonSubsequenceMem("abc","def"));

        System.out.println("Solution: "+sol.longestCommonSubsequenceDP("abcde","ace"));
        System.out.println("Solution: "+sol.longestCommonSubsequenceDP("abc","abc"));
        System.out.println("Solution: "+sol.longestCommonSubsequenceDP("abc","def"));

        //optimized
        System.out.println("Solution: "+sol.longestCommonSubsequenceDPSpaceOptimize("abcde","ace"));
        System.out.println("Solution: "+sol.longestCommonSubsequenceDPSpaceOptimize("abc","abc"));
        System.out.println("Solution: "+sol.longestCommonSubsequenceDPSpaceOptimize("abc","def"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(0,0,text1,text2);
    }

    private int longestCommonSubsequence(int i, int j, String text1, String text2) {
        if(i == text1.length() || j == text2.length())
            return 0;
        if(text1.charAt(i) == text2.charAt(j)) {
            return 1+longestCommonSubsequence(i+1,j+1,text1,text2);
        }
        else {
            //not needed as we will eventually reach there
            // int sub1 = longestCommonSubsequence(i+1,j+1,text1,text2);
            int sub2 = longestCommonSubsequence(i,j+1,text1,text2);
            int sub3 = longestCommonSubsequence(i+1,j,text1,text2);
            // return Math.max(sub1,Math.max(sub2,sub3));
            return Math.max(sub2,sub3);
        }
    }

    public int longestCommonSubsequenceMem(String text1, String text2) {
        int[][] mem = new int[text1.length()][text2.length()];
        for(int[] row : mem) {
            Arrays.fill(row,-1);
        }
        return longestCommonSubsequenceMem(0,0,text1,text2,mem);
    }

    private int longestCommonSubsequenceMem(int i, int j, String text1, String text2, int[][] mem) {
        if(i == text1.length() || j == text2.length())
            return 0;
        if(mem[i][j] >= 0)
            return mem[i][j];
        if(text1.charAt(i) == text2.charAt(j)) {
            mem[i][j] = 1+longestCommonSubsequenceMem(i+1,j+1,text1,text2,mem);
        }
        else {
            // int sub1 = longestCommonSubsequenceMem(i+1,j+1,text1,text2,mem);//not needed as we will eventually reach there
            int sub2 = longestCommonSubsequenceMem(i,j+1,text1,text2,mem);
            int sub3 = longestCommonSubsequenceMem(i+1,j,text1,text2,mem);
            // mem[i][j] = Math.max(sub1,Math.max(sub2,sub3));
            mem[i][j] = Math.max(sub2,sub3);
        }
        return mem[i][j];
    }


    // bottom-up approach time = O(n*m) space O(n*m)
    public int longestCommonSubsequenceDP(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for(int i=1;i<dp.length;i++) {
            for(int j=1;j<dp[0].length;j++) {

                // System.out.println("i: "+i+" j: "+j);
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1+dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                    // dp[i][j] = Math.max(dp[i-1][j-1],Math.max(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /* 
    You might have noticed in the dp approach that we only ever looked at the current column/row and the previous column/row.
    After that, previously computed columns/rows are no longer needed
    We can save a lot of space by instead of keeping track of an entire 2D array, only keeping track of the last two columns.
    This reduces the space complexity to be proportional to the length of the word going down. We should make sure this is the shortest of the two words.
    time = O(n*m) space MIN(n,m)
    */
    public int longestCommonSubsequenceDPSpaceOptimize(String text1, String text2) {
        if (text2.length() < text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
          }
        // initialize array with smaller string length
        int[] previousRow = new int[text1.length()+1];
        for(int i=1;i<=text2.length();i++) {
            // initialize array with smaller string length
            int[] currentRow = new int[text1.length()+1];
            for(int j=1;j<=text1.length();j++) {
                if(text2.charAt(i-1) == text1.charAt(j-1)) {
                    currentRow[j] = 1+previousRow[j-1]; //[previousrow,previouscol]
                } else {
                    currentRow[j] = Math.max(previousRow[j],currentRow[j-1]); // max([previousrow,samecol],[samerow,previouscol])
                }                
            }
            previousRow = currentRow;
        }
        return previousRow[text1.length()];
    }
}