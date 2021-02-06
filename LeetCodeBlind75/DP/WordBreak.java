import java.util.* ;

class WordBreak
{

    public static void main(String[] args) {
        WordBreak sol = new WordBreak();

        System.out.println("Solution: "+sol.wordBreak("leetcode",Arrays.asList("leet", "code")));
        System.out.println("Solution: "+sol.wordBreak("applepenapple",Arrays.asList("apple", "pen")));
        System.out.println("Solution: "+sol.wordBreak("catsandog",Arrays.asList("cats", "dog", "sand", "and", "cat")));

        System.out.println("Solution: "+sol.wordBreakMem("leetcode",Arrays.asList("leet", "code")));
        System.out.println("Solution: "+sol.wordBreakMem("applepenapple",Arrays.asList("apple", "pen")));
        System.out.println("Solution: "+sol.wordBreakMem("catsandog",Arrays.asList("cats", "dog", "sand", "and", "cat")));

        //better
        System.out.println("Solution: "+sol.wordBreakRecursionNoRight("leetcode",Arrays.asList("leet", "code")));
        System.out.println("Solution: "+sol.wordBreakRecursionNoRight("applepenapple",Arrays.asList("apple", "pen")));
        System.out.println("Solution: "+sol.wordBreakRecursionNoRight("catsandog",Arrays.asList("cats", "dog", "sand", "and", "cat")));


        //better with mem
        System.out.println("Solution: "+sol.wordBreakRecursionNoRightMem("leetcode",Arrays.asList("leet", "code")));
        System.out.println("Solution: "+sol.wordBreakRecursionNoRightMem("applepenapple",Arrays.asList("apple", "pen")));
        System.out.println("Solution: "+sol.wordBreakRecursionNoRightMem("catsandog",Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }


    // O(n^3) (because of substringmethod is n and size of recursion can go up to n^2)  Space = O(n) for set 
    public boolean wordBreak(String s, List<String> wordDict) {
       System.out.println("string: "+s);
       Set<String> words = new HashSet<String>(wordDict);
       return wordBreak(s,0,s.length(),words); 
    }


    private boolean wordBreak(String s, int left, int right, Set<String> words) {
        // System.out.println("left: "+left+" right: "+right);
        if(left == right)
            return true;
        else if(words.contains(s.substring(left,right))) {
            return true;
        }
        else {
            // i<=right causes infinite recursion
            for(int i=left+1;i<right;i++) {
                if(wordBreak(s,left,i,words) && wordBreak(s,i,right,words)) 
                    return true;
            }
        }
        return false;
    }

  // O(n^3) (because of substringmethod is n and size of recursion can go up to n^2)  Space = O(n^2) for matrix
    public boolean wordBreakMem(String s, List<String> wordDict) {
        System.out.println("string: "+s);
        int[][] mem = new int[s.length()+1][s.length()+1];
        for(int[] row:mem){
            Arrays.fill(row,-1);
        }
        for(int i=0;i<=s.length();i++)
            mem[i][i] = 1;
        Set<String> words = new HashSet<String>(wordDict);
        return wordBreakMem(s,0,s.length(),words,mem); 
     }
 
     private boolean wordBreakMem(String s, int left, int right, Set<String> words,int[][] mem) {
        //  System.out.println("left: "+left+" right: "+right);
        if(mem[left][right] != -1)
            return mem[left][right]==0?false:true;
         else if(words.contains(s.substring(left,right))) {
             mem[left][right]=1;
             return true;
         }
         else {
             for(int i=left+1;i<right;i++) {
                if(wordBreakMem(s,left,i,words,mem) && wordBreakMem(s,i,right,words,mem)) {
                    mem[left][right]=1;
                    return true;
                }
             }
         }
         mem[left][right]=0;
         return false;
    }

    // Can reduce space to n , bcoz no need of right param
    public boolean wordBreakRecursionNoRight(String s, List<String> wordDict) {
        System.out.println("string: "+s);
        Set<String> words = new HashSet<String>(wordDict);
        return wordBreakRecursionNoRight(s,0,words); 
     }
 
 
     private boolean wordBreakRecursionNoRight(String s, int left, Set<String> words) {
        //  System.out.println("left: "+left);
         if(left == s.length())
             return true;
         else {
             for(int right=left+1;right<=s.length();right++) {
                 if(words.contains(s.substring(left,right)) && wordBreakRecursionNoRight(s,right,words)) {
                        return true;
                 }
             }
         }
         return false;
     }

     public boolean wordBreakRecursionNoRightMem(String s, List<String> wordDict) {
        System.out.println("string: "+s);
        int[] mem = new int[s.length()+1];
        Arrays.fill(mem,-1);
        Set<String> words = new HashSet<String>(wordDict);
        return wordBreakRecursionNoRightMem(s,0,words,mem); 
     }
 
 
     private boolean wordBreakRecursionNoRightMem(String s, int left, Set<String> words,int[] mem) {
        //  System.out.println("left: "+left);
         if(left == s.length()) {
            return true;
         }
         if(mem[left] != -1)
            return mem[left]==0?false:true;
         else {
             for(int right=left+1;right<=s.length();right++) {
                 if(words.contains(s.substring(left,right)) && wordBreakRecursionNoRightMem(s,right,words,mem)) {
                        mem[left]=1;
                        return true;
                 }
             }
         }
         mem[left] = 0;
         return false;
     }
    
}