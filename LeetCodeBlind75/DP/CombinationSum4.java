class CombinationSum4 {


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
    
    public int combinationSum4(int[] nums, int target) {
        int count = 0;
        count+=combinationSum4Helper(nums,target);
    }

/* 
[1,2] target = 3
helper(3-1) {1},helper(3-2) {2}
    helper(3-1) => helper(2) => helper(2-1) {1,1}, helper(2-2) {1,2}
        helper(2-1) => helper(1) => helper(1-1) => helper(0) => 1 {1,1,1}
        helper(2-2) => helper(0) => 1 {1,2}
    helper(3-2) => helper(1) => helper(1-1) = > helper(0) = 1  {2,1}



*/
    private int combinationSum4Helper(int[] nums, int target) {
        if(target == 0)
            return 1;
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            int taken = 0;
            if(target>=nums[i]) {
                int sub = combinationSum4Helper(nums, target-nums[i]);
                if(sub >= 0)
                    count+=sub;
            }
        }
        return count;
    }

}