/*
https://leetcode.com/problems/first-missing-positive/submissions/
We can disegard negative number, 0's and numbers greater than n becoz smallest postive missing number will be less than n
There is a case where smallest positive number is n+1, but we can deal with it at end
IDea is to Keep track of all number 1 to n present in a hashMap (KEY = index Range[1 to n],VALUE = present or not)
Then traverse thru keys 1 to n  and find the number present or not  based on key. 
Instead of using a hashMap we can use the array indexes itself to keep track using sign

If nums[2] is positive - number 2 is missing. 
Take care of duplicates by not changing sign twice and making it positive
Now everything is ready to write down the algorithm.

Check if 1 is present in the array. If not, you're done and 1 is the answer.
If nums = [1], the answer is 2.
Replace negative numbers, zeros, and numbers larger than n by 1s.
Walk along the array. Change the sign of a-th element if you meet number a. Be careful with duplicates : do sign change only once. Use index 0 to save an information about presence of number n since index n is not available.
Walk again along the array. Return the index of the first positive element.
If nums[0] > 0 return n.
If on the previous step you didn't find the positive element in nums, that means that the answer is n + 1.
TIme: O(n)
Space:O(1)
*/
class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        boolean onePresent = false;
        int n = nums.length;
        for(int i=0;i<n;i++) {
            if(nums[i] == 1) {
                onePresent = true;
                break;
            }
        }
        if(!onePresent)
            return 1;
        if(nums.length == 1) // one is present but length is 1
            return 2;
        // remove negatives , 0's and numbers greater than n
        for(int i=0;i<nums.length;i++) {
            if(nums[i]<=0 || nums[i]>n) {
                nums[i]=1;
            }
        }
        
        for(int i=0;i<n;i++) {
            int num = Math.abs(nums[i]);
            //when value is n then make index 0 negative
            // We do negative(Math.abs) bcoz we dont want to change sign twice and make it positive
            if(num == n)
                nums[0] = -Math.abs(nums[0]);
            else
                nums[num] = -Math.abs(nums[num]);
        }
        
        for(int i=1;i<n;i++) {
            if(nums[i]>0)
                return i;
        }
        
        if(nums[0]>0)
            return n;
        return n+1;
    }
}