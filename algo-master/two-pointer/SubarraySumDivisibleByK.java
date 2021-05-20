import java.util.*;

/* 
Given an array of integers and an integer K, find the number of subarrays which are divisible by K.

Input: [3,1,2,5,1], 3
Output: 6
Explanation: the six subarrays are[3], [3,1,2], [1,2],[5,1], [3,1,2,5,1,], [1,2,5,1]

Approach: 
This problem is very similar to the previous problem subarray sum's follow-up problem.
Instead of saving the prefix_sum, we save the remainder prefix_sum % k.

When we get a non zero reminder for currSum , reminder = currsum%k
(1) -> we can make the currsum divisible by k, by substracting reminder to currsum , or any number whose remainder is the same remainder
num = 2,divisor=3, remainder = 2, => 2-2 is divisible by 3, 7(remainder = 2) = 7-1 = 6, divisible by 3  
        or 
(2) ->we can make the currsum with complement of remainder c, (k-remainder) divisible by k,  by substracting complement c or some number whose complement remainder is same as c
eg: num = 5,divisor=3, rem=2,comp=1,=> 5-2(rem=2,comp=1) = 3 divisible by 3
    num = 7,divisor=3, rem=1,comp=2,=> 7-4(rem=1,comp=2) = 3 divisible by 3
We have to follow option (2) Substraction as we need to substract past prefixsums to make k divisible,
bcoz for finding subarrays bcoz sum of a subarray [i, j] is equal to the sum of [0, j] minus the sum of [0, i - 1].
That way saving complements of currsum in prefixSum instead of sum will help get subarrays
*/
class SubarraySumDivisibleByK {


    public static int subarraySumDivisible(int[] nums, int k) {
        Map<Integer,Integer> remainders = new HashMap<Integer,Integer>();
        // including count of a subarray btw [0 to some index] whose sums is divisible by k
        remainders.put(0,1);
        int currTotal = 0;
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            // System.out.println("num: "+nums[i]);
            currTotal+=nums[i];
            int remainder = currTotal%k;
            // System.out.println("currTotal: "+currTotal);
            // System.out.println("remainder: "+remainder);
            int complement = (k-remainder)%k;
            // System.out.println("complement: "+complement);
            if(remainders.containsKey(complement)) {
                count+=remainders.get(complement);
                // System.out.println("count: "+count);
            }
            remainders.put(complement,remainders.getOrDefault(complement,0)+1);
            // System.out.println(remainders);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,1,2,3,5,1};
        // [2,1] ,[1,2] [3],[1,2,3] [5,1], [1,2,3],[3,5,1]
        System.out.println(subarraySumDivisible(arr, 3));
    }

}
