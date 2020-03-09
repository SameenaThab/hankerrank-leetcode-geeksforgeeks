class WordBreak {
    //https://leetcode.com/explore/interview/card/amazon/80/dynamic-programming/2997/
    Set<String> set;
    int n;
    public boolean wordBreak(String s, List<String> wordDict) {
        this.set = new HashSet<String>(wordDict);
        this.n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        for(int i=0;i<=n;i++){
            System.out.println("i: "+i+" dp: "+dp[i]);
        }
        return dp[s.length()];
        //return wordBreak(s,0,new Boolean[s.length()]);
    }
    
    public boolean wordBreak(String s,int start,Boolean[] mem) {
        if(start == n)
            return true;
        if(mem[start] != null)
            return mem[start];
        for(int j=start+1;j<=n;j++){
            if(set.contains(s.substring(start,j)) && wordBreak(s,j,mem)) {
                return mem[start] = true;
            }
        }
        
        return mem[start] = false;
    }    
}