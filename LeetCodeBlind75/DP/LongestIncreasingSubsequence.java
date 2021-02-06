import java.util.*;

class LongestIncreasingSubsequence {


    public static void main(String[] args) {
        LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();

        System.out.println("Solution: "+sol.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("Solution: "+sol.lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println("Solution: "+sol.lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
        
        System.out.println("Solution: "+sol.lengthOfLISRecursionWithMem(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("Solution: "+sol.lengthOfLISRecursionWithMem(new int[]{0,1,0,3,2,3}));
        System.out.println("Solution: "+sol.lengthOfLISRecursionWithMem(new int[]{7,7,7,7,7,7,7}));

        System.out.println("Solution: "+sol.lengthOfLISDP(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("Solution: "+sol.lengthOfLISDP(new int[]{0,1,0,3,2,3}));
        System.out.println("Solution: "+sol.lengthOfLISDP(new int[]{7,7,7,7,7,7,7}));


        //optmized
        System.out.println("Solution: "+sol.lengthOfLISbinary(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("Solution: "+sol.lengthOfLISbinary(new int[]{0,1,0,3,2,3}));
        System.out.println("Solution: "+sol.lengthOfLISbinary(new int[]{7,7,7,7,7,7,7}));
    }

    /* 
    For every element , we make a choice whether to take it or not as previous element
    if taken and adjacent number is larger if update previous as adjacent element and 1 to the count
    or just keep the previous element as it is but proceed to next index
    return max of both cases;
    Time = O(2^n), Space complexity : O(n^2) memory array of size n * nn∗n is used.
    */
    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(Integer.MIN_VALUE,nums,0);
    }

    private int lengthOfLIS(int prev, int[] nums, int index) {
        if(index == nums.length)
            return 0;
        int taken = 0;
        if(nums[index] > prev) {
            taken = 1+lengthOfLIS(nums[index], nums, index+1);
        } 
        int notTaken = lengthOfLIS(prev,nums,index+1);
        return Math.max(taken,notTaken);
    }

    // Time = O(n^2), space O(n^2)
    public int lengthOfLISRecursionWithMem(int[] nums) {
        int n = nums.length;
         // row represent prev elements, since start from int.MIN, rowLength = n+1,
        //  col length = n , represents index
        int[][] mem = new int[n+1][n];
        for(int[] row:mem) {
            Arrays.fill(row,-1);
        }
        return lengthOfLISRecursionWithMem(-1,nums,0,mem);
    }

    private int lengthOfLISRecursionWithMem(int prevIndex, int[] nums, int index, int[][] mem) {
        if(index == nums.length)
            return 0;
        if(mem[prevIndex+1][index] >= 0)
            return mem[prevIndex+1][index];
        int taken = 0;
        if(prevIndex<0 || nums[index] > nums[prevIndex]) {
            taken = 1+lengthOfLISRecursionWithMem(index, nums, index+1,mem);
        } 
        int notTaken = lengthOfLISRecursionWithMem(prevIndex,nums,index+1,mem);
        mem[prevIndex+1][index] = Math.max(taken,notTaken); 
        return mem[prevIndex+1][index];
    }

    /* 
    This method relies on the fact that the longest increasing subsequence possible upto the ith index in a given array is independent of the elements coming later on in the array. 
    Thus, if we know the length of the LIS upto i th index, 
    we can figure out the length of the LIS possible by including the (i+1)th element based on the elements with indices j such that 0≤j≤(i+1).
    We make use of a dpdp array to store the required data. 
    dp[i] represents the length of the longest increasing subsequence possible considering the array elements up to the ithindex only ,by necessarily including the ith element.
    In order to find out dp[i], we need to try to append the current element(nums[i]) in every possible increasing subsequences upto the (i−1)th index(including the (i−1)th index), 
    such that the new sequence formed by adding the current element is also an increasing subsequence.
    Thus, we can easily determine dp[i] using:
    dp[i]=max(dp[j])+1,∀ 0≤j<i and num[i] > num[j] 
    At the end, the maximum out of all the dp[i]'s to determine the final result.
    length = max(dp[i]),∀0≤i<n
  */
        // Time = O(n^2), space O(n)
        // eg: 10,9,2,5,3,7,101,18
    public int lengthOfLISDP(int[] nums) {
        int n = nums.length;
        if(n==0)
            return 0;
        int[] dp = new int[n];
        int max = 1;
        dp[0] = 1; // max subseq length for first element is 1
        for(int i=1;i<n;i++) {
            int sub = 0;
            for(int j=0;j<i;j++) {
                if(nums[j]<nums[i]) {
                    sub = Math.max(sub,dp[j]);
                }
                dp[i] = sub+1;
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }


/* 
In this approach, we scan the array from left to right.
We also make use of a dp array initialized with all 0's.
This dp array is meant to store the increasing subsequence formed by including the currently encountered element.
While traversing the numsnums array, we keep on filling the dp array with the elements encountered so far.
For the element corresponding to the jth index (nums[j]),
we determine its correct position in the dp array(say ith index) by 
making use of Binary Search(which can be used since the dp array is storing increasing subsequence)
and also insert it at the correct position.
An important point to be noted is that for Binary Search,
we consider only that portion of the dp array in which we have made the updates by inserting some elements at their correct positions(which remains always sorted).
Thus, only the elements upto the ith index in the dp array can determine the position of the current element in it.
Since, the element enters its correct position(i) in an ascending order in the dp array, the subsequence formed so far in it is surely an increasing subsequence.
Whenever this position index i becomes equal to the length of the LIS formed so far(len), it means, we need to update the lenlen as len = len + 1.

Note: dp array does not result in longest increasing subsequence, but length of dp array will give you length of LIS.

Consider the example:
input: [0, 8, 4, 12, 2]
dp: [0]
dp: [0, 8]
dp: [0, 4]
dp: [0, 4, 12]
dp: [0 , 2, 12]
which is not the longest increasing subsequence, but length of dp array results in length of Longest Increasing Subsequence.
   
Time complexity : O(nlogn). Binary search takes \log nlogn time and it is called nn times.

Space complexity : O(n). dpdp array of size nn is used.
*/
    public int lengthOfLISbinary(int[] nums) {
        System.out.println(Arrays.toString(nums));
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            //index of the search key, if it is contained in the array within the specified range; 
            // otherwise, (-(insertion point) - 1).
            int i = Arrays.binarySearch(dp, 0, len, num);
            // System.out.println("len: "+len+" i: "+i+" num: "+num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

}