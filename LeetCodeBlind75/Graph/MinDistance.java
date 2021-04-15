class MinDistance {
    //https://leetcode.com/problems/edit-distance/
    /* 
    for every mismatched character in word1 and word2
        we have 3 options 
            eg: horse,ros i=0,j=0
            insert rhorse,ros i=0,j=1
            remove orse,ros i=1,j=0
            replace rorse,ros i=1,j=1
        if i >word1.length -> all inserts
        if j<word2.length -> all deletes
    for all matched chars we move forward
        eg: role,roll (0,0) ->role,roll(1,1)->role,roll(2,2)->role,roll(3,3) 
    Base case : when i=word1.length && word=word2.length return 0.

    */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // if atleast one word is empty, then all insertions
        if(m*n == 0)
            return m+n;
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        // all word1 empty scenarios
        for(int i=0;i<=n;i++){
            dp[0][i] = i;
        }
        // all word2 empty scenarios
        for(int i=0;i<=m;i++){
            dp[i][0] = i;
        }
        
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    //insert
                    int ins = 1+dp[i][j-1];
                    //remove
                    int remv = 1+dp[i-1][j];
                    // replace 
                    int replc = 1+dp[i-1][j-1];
                    dp[i][j] = Math.min(ins,Math.min(remv,replc));
                }           
            }
        }
        
            return dp[m][n];
    }
    
    
    public int minDistanceRecursive(String word1, String word2) {
        int[][] mem = new int[word1.length()+1][word2.length()+1];
        for(int[] row:mem){
            Arrays.fill(row,-1);
        }
        return editDistance(word1,word2,0,0,mem);
    }
    
    public int editDistance(String word1,String word2,int i,int j,int[][] mem) {
        if(mem[i][j] != -1)
            return mem[i][j];
        if(i==word1.length() && j == word2.length()) {
            mem[i][j] = 0;
            return mem[i][j];
        }
        if(i==word1.length() || j == word2.length()) {
            if(i==word1.length()) {
                //word1 is shorter, only insertions
                mem[i][j] = 1+editDistance(word1,word2,i,j+1,mem);
                return mem[i][j];
            } else {
                //word2 is shorter, only removals
                mem[i][j] = 1+editDistance(word1,word2,i+1,j,mem);
                return mem[i][j];
            }
        }
        if(word1.charAt(i) == word2.charAt(j))
            mem[i][j] = editDistance(word1,word2,i+1,j+1,mem);
        else {
            //remove
            int x = 1+editDistance(word1,word2,i+1,j,mem);
            //insert
            int y = 1+editDistance(word1,word2,i,j+1,mem);
            //replace
            int z = 1+editDistance(word1,word2,i+1,j+1,mem);   
            mem[i][j] = Math.min(x,Math.min(y,z));
        }
        return mem[i][j];
    }
    
    
}