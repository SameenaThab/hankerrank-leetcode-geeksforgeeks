import java.util.*;

class HouseRobberI {

    public static void main(String[] args) {
        HouseRobberI sol = new HouseRobberI();

        System.out.println("Solution: "+sol.rob(new int[]{1,2,3,1}));
        System.out.println("Solution: "+sol.rob(new int[]{2,7,9,3,1}));
        System.out.println("Solution: "+sol.rob(new int[]{2,1,1,2}));
        System.out.println("Solution: "+sol.rob(new int[]{1,2,1,1}));

        //mem efficient
        System.out.println("Solution: "+sol.robMem(new int[]{1,2,3,1}));
        System.out.println("Solution: "+sol.robMem(new int[]{2,7,9,3,1}));
        System.out.println("Solution: "+sol.robMem(new int[]{2,1,1,2}));
        System.out.println("Solution: "+sol.robMem(new int[]{1,2,1,1}));

        //both mem and space efficient
        System.out.println("Solution: "+sol.robDP(new int[]{1,2,3,1}));
        System.out.println("Solution: "+sol.robDP(new int[]{2,7,9,3,1}));
        System.out.println("Solution: "+sol.robDP(new int[]{2,1,1,2}));
        System.out.println("Solution: "+sol.robDP(new int[]{1,2,1,1}));
    }

    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        // two answer possible either start from index = 0 or index = 1
        return Math.max(rob(nums,0),rob(nums,1));
    }

    /*
     * {1, 2, 3 ,1,4,5}
     * Except for adjcent one we recurse through all indexes as start index
     * startIndex=0 -----> Max(startIndex=2, startIndex=3,startIndex=4,startIndex=5)
     *      startIndex=2 -----> Max(startIndex=4,startIndex=5)
     *      startIndex=4 -----> none (bcoz 5 is adjacent to 4)
     * start=1 -----> Max(startIndex=3, startIndex=4,startIndex=5)
     *      startIndex=3 -----> Max(startIndex=5)
     *      startIndex=4 -----> none (bcoz 5 is adjacent to 4)
     *      startIndex=5 -----> none (bcoz 5 last index)
     *      time = O(n*(n-2))= O(n^2) , space = O(1)
     */
    public int rob(int[] nums,int start) {
        int max = 0;
        for(int i=start+2;i<nums.length;i++) {
            max=Math.max(max,rob(nums,i));
        }
        return max+nums[start];
    }

    // time = O(n) [because we dont calculate twice], space = O(n)
    public int robMem(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        int[] mem = new int[nums.length];
        Arrays.fill(mem,-1);
        // two answer possible either start from index = 0 or index = 1
        return Math.max(robMem(nums,0,mem),robMem(nums,1,mem));
    }

    public int robMem(int[] nums,int start,int[] mem) {
        int max = 0;
        if(mem[start] != -1)
            return mem[start];
        for(int i=start+2;i<nums.length;i++) {
            max=Math.max(max,robMem(nums,i,mem));
        }
        mem[start] = max+nums[start];
        return mem[start];
    }

    /* 
    [1,2,1,4]
    prev =0,curr=0
    i=0 => curr=1,prev=0
    i=1 => curr=2,prev=1
    i=2 => curr=2, prev=2
    i=3 => curr=6, prev=2
    */
    // time = O(n) , space = O(1)
    public int robDP(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for(int i=0;i<nums.length;i++) {
            int temp = currMax;
            currMax = Math.max(currMax,prevMax+nums[i]);
            prevMax = temp;
        }
        return currMax;
    }
}