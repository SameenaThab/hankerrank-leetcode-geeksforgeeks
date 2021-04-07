class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<String>(wordDict);
        int[] mem = new int[s.length()];
        return wordBreakHelper(s,0,set,mem);
    }
    
    boolean wordBreakHelper(String s, int start, Set<String> set,int[] mem) {
        if(start == s.length())
            return true;
        if(mem[start] != 0)
            return mem[start]==1?true:false;
        for(int i=start;i<s.length();i++) {
            if(set.contains(s.substring(start,i+1)) && wordBreakHelper(s,i+1,set,mem)) {
                mem[start]=1;
                return true;
            }
        }
        mem[start]=-1;
        return false;
    }
    
    public boolean wordBreakDP(String s, List<String> wordDict) {
        Set<String> set = new HashSet<String>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}