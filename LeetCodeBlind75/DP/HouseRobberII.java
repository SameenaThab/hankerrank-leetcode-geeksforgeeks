import java.util.*;

class HouseRobberII {

    // https://leetcode.com/problems/house-robber-ii/solution/

    /* 
    Since its houses are in cricle 0th house and (n-1)th both are adjacent
    In that case we divide the array into two parts [0 to n-2],[1 to n-1] making them linear houses and apply HouseRobberI solution
    [7,4,1,9,3,8,6,5] => Max(houseRobberI([7,4,1,9,3,8,6]), houseRobberI([4,1,9,3,8,6,5])
    [2,1,4,5,8] => Max(houseRobberI([2,1,4,5]), houseRobberI([1,4,5,8])
    */

    public static void main(String[] args) {
        HouseRobberII sol = new HouseRobberII();

        System.out.println("Solution: "+sol.rob(new int[]{1,2,3,1}));
        System.out.println("Solution: "+sol.rob(new int[]{2,3,2}));
        System.out.println("Solution: "+sol.rob(new int[]{6,6,4,8,4,3,3,10}));

    }

    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        return Math.max(robDP(nums,0,nums.length-2),robDP(nums,1,nums.length-1));
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
    public int robDP(int[] nums, int start, int end) {
        int prevMax = 0;
        int currMax = 0;
        for(int i=start;i<=end;i++) {
            int temp = currMax;
            currMax = Math.max(currMax,prevMax+nums[i]);
            prevMax = temp;
        }
        return currMax;
    }
}