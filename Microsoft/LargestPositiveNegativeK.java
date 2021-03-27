import java.util.*;

/* 
https://algo.monster/problems/largest_k_positive_and_negative

Given an array A of N integers, returns the largest integer K > 0 such that both values K and -K exist in array A. If there is no such integer, the function should return 0.

Example 1:
Input:[3, 2, -2, 5, -3]
Output: 3
Example 2:
Input:[1, 2, 3, -4]
Output: 0

*/
class LargestPositiveNegativeK {

    int largestPositiveNegativeK(int[] arr) {
        Set<Integer> set = new HashSet<Integer>();
        int maxK = 0;
        for(int num:arr) {
            if(num>0)
                set.add(num);
            else {
                int abs = -1*num;
                if(set.contains(abs)) {
                    maxK = Math.max(maxK,abs);
                }
            }
        }
        return maxK;
    }

    public int internetSolution(int nums[]) {
        // WRITE YOUR BRILLIANT CODE HERE
        HashSet<Integer> set = new HashSet<>();
        int curMax = 0;
        for (int a: nums) {
            if (set.contains(a * -1))
                curMax = Math.max(curMax, Math.abs(a));
            else
                set.add(a);
        }
        return curMax;
    }

    public static void main(String[] args) {
        LargestPositiveNegativeK sol = new LargestPositiveNegativeK();
        System.out.println("should be 3: "+sol.largestPositiveNegativeK(new int[]{3,-3,2,-2,5}));
        System.out.println("should be 0: "+sol.largestPositiveNegativeK(new int[]{1,2,-3,-4}));
    }
}