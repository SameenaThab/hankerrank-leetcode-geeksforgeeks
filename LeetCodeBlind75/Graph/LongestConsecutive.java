import java.util.Arrays;
import java.util.HashSet;
// https://leetcode.com/problems/longest-consecutive-sequence/solution/
import java.util.Set;

/* 
First sort the array.
intialize prev with nums[0]
initialize max and count to 1
traverse thru array
    if nums[i] == prev+1, increment count
    else if nums[i] == prev, continue
    else reset count to 1, new seq started
    update max and prev

    Time complexity : O(nlgn).
end

The main for loop does constant work n times, so the algorithm's time complexity is dominated by the invocation of sort, which will run in O(nlgn) time for any sensible implementation.

Space complexity : O(1) (or O(n)).

For the implementations provided here, the space complexity is constant because we sort the input array in place. If we are not allowed to modify the input array, we must spend linear space to store a sorted copy.
*/
class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int max = 1;
        int prev = nums[0];
        int count = 1;
        for(int i=1;i<nums.length;i++) {
            if(nums[i] == prev+1) {
                count++;
            }
            else if(nums[i] == prev)
                continue;
            else {
                count = 1;
            }

            max = Math.max(count,max);
            prev = nums[i];
            // System.out.println("prev: "+prev+" count: "+count);
        }
        return max;
    }

    /* 
    Using hashSets and no sorting
    we add all numbers to hashset, for each number, num in the set 
        we check if num-1 exists, if exists then we have already count the streak or will count in future, therfore no need to proceed
            prev = num ,streak = 1
            while num+1 exists in hashset, 
                we increment our count/Streak and update prev = num+1;
            end
            update the longestSteak
    end
    eg: 1 2 10 11 3
        we start with 1, longest sTreak is 3 as 1,2,3 exists in set
        then next number 2 , 
        we dont have to do the process for 2 because we have already counted 2 when we started with 1
        we avoid this we check if num-1 exists in set, if so , then we already counted, so no need of counting

        Time complexity : O(n)
        Space complexity : O(n)    
    */
    public int longestConsecutiveApp2(int[] nums) {
        if(nums.length == 0)
            return 0;
        Set<Integer> set = new HashSet<Integer>();
        for(int num:nums) {
            set.add(num);
        }
        int longestStreak = 0;
        for(int num:set) {
            if(!set.contains(num-1)) { // if contains then we have already considered its streak or will count in future
                int streak = 1;
                int prev = num;
                while(set.contains(prev+1)) {
                    prev = prev+1;
                    streak++;
                }
                longestStreak = Math.max(streak,longestStreak);
            }
        }
        return longestStreak;
    }    

    public static void main(String[] args) {
        LongestConsecutive sol = new LongestConsecutive();
        System.out.println("solution: "+sol.longestConsecutive(new int[]{100,4,200,1,3,2}));
        System.out.println("solution: "+sol.longestConsecutive(new int[]{0,2,1,1}));


        System.out.println("solution: "+sol.longestConsecutiveApp2(new int[]{100,4,200,1,3,2}));
        System.out.println("solution: "+sol.longestConsecutiveApp2(new int[]{0,2,1,1}));
    }
}