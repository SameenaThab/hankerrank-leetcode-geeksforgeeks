import java.util.*;

class CombinationSum4 {


    public static void main(String[] args) {
        CombinationSum4 sol = new CombinationSum4();

        System.out.println("Solution: "+sol.combinationSum4(new int[]{1, 2, 3},4));
        // System.out.println("Solution: "+sol.combinationSum4(new int[]{2, 1, 3},35)); // exceeds time

        System.out.println("Solution: "+sol.combinationSum4Mem(new int[]{1, 2, 3},4));
        System.out.println("Solution: "+sol.combinationSum4Mem(new int[]{2, 1, 3},35));

        System.out.println("Solution: "+sol.combinationSum4BottomUp(new int[]{1, 2, 3},4));
        System.out.println("Solution: "+sol.combinationSum4BottomUp(new int[]{2, 1, 3},35));
    }

/* 
[1,2] target = 3
helper(3-1) combination = {1}, helper(3-2) combination = {2}
    helper(3-1) => helper(2) => helper(2-1) {1,1}, helper(2-2) {1,2}
        helper(2-1) => helper(1) => helper(1-1) => helper(0) => 1 {1,1,1}
        helper(2-2) => helper(0) => 1 {1,2}
    helper(3-2) => helper(1) => helper(1-1) = > helper(0) = 1  {2,1}
    
    Time O(n^n) , Space = O(T); (T = target)
*/
    public int combinationSum4(int[] nums, int target) {
        if(target == 0)
            return 1;
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            int taken = 0;
            if(target>=nums[i]) {
                count+=combinationSum4(nums, target-nums[i]);
            }
        }
        return count;
    }

    // Time O(T.N) , Space = O(T); TopDown Approach
    public int combinationSum4Mem(int[] nums, int target) {
        int[] mem = new int[target+1];
        Arrays.fill(mem,-1);
        return combinationSum4Mem(nums,target,mem);
    }

    public int combinationSum4Mem(int[] nums, int target,int[] mem) {
        if(target == 0)
            return 1;
        if(mem[target] != -1)
            return mem[target];
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            int taken = 0;
            if(target>=nums[i]) {
                count+=combinationSum4Mem(nums, target-nums[i],mem);
            }
        }
        mem[target] = count;
        return count;
    }

    // LeetCOde BottomUp

    public int combinationSum4BottomUp(int[] nums, int target) {
        int[] dp = new int[target+1];
        Arrays.fill(dp,-1);
        dp[0] = 1;
        for(int i=1;i<=target;i++) {
            int count = 0;
            for(int num:nums) {
                if(i>=num) {
                    count+=dp[i-num];
                }            
            }
            dp[i]=count;
        }
        return dp[target];
    }

}