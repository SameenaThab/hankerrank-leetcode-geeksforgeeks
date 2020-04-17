class LongestPalnDP {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n==0)
            return s;
        String[][] dp = new String[n+1][n+1];
        for(int i=0;i<n;i++)
            dp[i][i] = s.substring(i,i+1);
        for(int i=0;i<n;i++) {
            int j;
            for(j=i+1;j<n+1;j++){
                if(palin(s.substring(i,j))){
                    //System.out.println(s.substring(i,j));
                    dp[i][j] = max(dp[i][j-1],s.substring(i,j));     
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
                dp[i+1][i+1] = dp[i][j-1];
        }        
        return dp[n][n];
        
    }
    
    boolean palin(String st) {
        if(st.length() <= 1)
            return true;
        else {
            int n = st.length();
            int mid = n%2==0?(n-1)/2:n/2;
            for(int i =0;i<=mid;i++)
            {
                if(st.charAt(i) != st.charAt(n-i-1))
                   return false;
            }
            return true;
        }
    }
    
    String max(String s1,String s2){
        return s1.length()>=s2.length()?s1:s2;
    }
}