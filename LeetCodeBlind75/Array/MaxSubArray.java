class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray sol = new MaxSubArray();
        System.out.println("solution: "+sol.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("solution: "+sol.maxSubArray(new int[]{1}));
        System.out.println("solution: "+sol.maxSubArraySpaceOptmize(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("solution: "+sol.maxSubArraySpaceOptmize(new int[]{1}));
        System.out.println("solution: "+sol.maxSubArrayDP(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("solution: "+sol.maxSubArrayDP(new int[]{1}));
    }

    /* 
    https://leetcode.com/problems/maximum-subarray/
    time =O(n), space = O(n)
    use mem array to store maxsum for the index
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] mem = new int[n];
        mem[0]=nums[0];
        int max = mem[0];
        for(int i=1;i<n;i++){
            mem[i] = Math.max(mem[i-1]+nums[i],nums[i]);
            max = Math.max(max,mem[i]);
        }
        return max;
    }

    /* 
    time =O(n), space = O(1)
     */
    public int maxSubArraySpaceOptmize(int[] nums) {
        int n = nums.length;
        int currSum = nums[0];
        int max = nums[0];
        for(int i=1;i<n;i++){
            currSum = Math.max(currSum+nums[i],nums[i]);
            max = Math.max(max,currSum);
        }
        return max;
    }

    /* 
    no need of currSum, use nums[i] to store it
    */
    /* 
    time =O(n), space = O(1)
     */
    public int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        for(int i=1;i<n;i++){
            if(nums[i-1]>0)
                nums[i] = nums[i]+nums[i-1];
            max = Math.max(max,nums[i]);
        }
        return max;
    }

}