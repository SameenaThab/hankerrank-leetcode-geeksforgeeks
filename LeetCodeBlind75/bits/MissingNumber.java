class MissingNumber {

/* 
https://leetcode.com/problems/missing-number/

We can harness the fact that XOR is its own inverse to find the missing element in linear time.

Algorithm

Because we know that nums contains n numbers 
And that it is missing exactly one number on the range [0..n−1], 
we know that n definitely replaces the missing number in nums. 
Therefore, if we initialize an integer to n and XOR it with every index and value, we will be left with the missing number.
Consider the following example (the values have been sorted for intuitive convenience, but need not be):

Index	0	1	2	3
Value	0	1	3	4

missing = 4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
        = (4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
        = 0∧0∧0∧0∧2
        = 2
*/

    public static void main(String[] args) {
        MissingNumber sol = new MissingNumber();
        System.out.println("Solution: "+sol.missingNumber(new int[]{3,0,1}));
        System.out.println("Solution: "+sol.missingNumber(new int[]{0,1}));
        System.out.println("Solution: "+sol.missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
        System.out.println("Solution: "+sol.missingNumber2(new int[]{3,0,1}));
        System.out.println("Solution: "+sol.missingNumber2(new int[]{0,1}));
        System.out.println("Solution: "+sol.missingNumber2(new int[]{9,6,4,2,3,5,7,0,1}));
    }

    public int missingNumber(int[] nums) {
        int result=nums.length;

        for(int i=0;i<nums.length;i++) {
            result^= i^nums[i];
        }

        return result;
    }

    /* 
    Sum of n = n*(n-1)/2
    */
    public int missingNumber2(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }
}