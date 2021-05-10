import java.util.*;

/* 
https://leetcode.com/problems/3sum/solution/

*/
class ThreeSum {

    public static void main(String[] args) {
        ThreeSum sol = new ThreeSum();
        List<List<Integer>>  result1 = sol.threeSum(new int[]{-1,0,1,2,-1,-4});
        List<List<Integer>>  result2 = sol.threeSum(new int[]{0,0,0});
        System.out.println("solution: "+result1.toString());
        System.out.println("solution: "+result2.toString());
        result1 = sol.threeSumApp2(new int[]{-1,0,1,2,-1,-4});
        result2 = sol.threeSumApp2(new int[]{0,0,0});
        System.out.println("solution: "+result1.toString());
        System.out.println("solution: "+result2.toString());
        result1 = sol.threeSumApp3(new int[]{-1,0,1,2,-1,-4});
        result2 = sol.threeSumApp3(new int[]{0,0,0});
        System.out.println("solution: "+result1.toString());
        System.out.println("solution: "+result2.toString());
    }

    /* 
    For the main function:
    Sort the input array nums.
    Iterate through the array:
    If the current value is greater than zero, break from the loop. Remaining values cannot sum to zero.
    If the current value is the same as the one before, skip it.
    Otherwise, call twoSumII for the current position i.
    For twoSumII function:

    Set the low pointer lo to i + 1, and high pointer hi to the last index.
    While low pointer is smaller than high:
    If sum of nums[i] + nums[lo] + nums[hi] is less than zero, increment lo.
    If sum is greater than zero, decrement hi.
    Otherwise, we found a triplet:
    Add it to the result res.
    Decrement hi and increment lo.
    Increment lo while the next value is the same as before to avoid duplicates in the result.
    Return the result res.

    Time = O(n^2)(we call sumII(O(n)) n times), space = O(1)
    */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums.length<=2)
            return result;
        Arrays.sort(nums);
       int n = nums.length;
       for(int i=0;i<n;i++) {
            if(nums[i] > 0) // if element > 0 other nums are much greater, sum cannot be equal to 0
                break; 
            if(i!=0 && nums[i]==nums[i-1])
                continue; // avoid duplicates in triplets
            twoSumsII(nums, i, result);
       }  
       return result; 
    }

    private void twoSumsII(int[] nums,int i,List<List<Integer>> result) {
        int n = nums.length;
        int target = -nums[i];
        int left = i+1;
        int right = n-1;
        while(left<right) {
            if(nums[left]+nums[right] == target) {
                result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                left++;
                right--;
                while(left<right && nums[left] == nums[left-1]) // we dont do left<n bcoz right of right are greater anyways 
                    left++;
            }
            else if(nums[left]+nums[right] < target)
                left++;
            else
                right--;   
        }  
    }

    /* 
    Approach 2 : using hash set but sorted
        For each i , we do twoSum problem using hashMap, with target -nums[i]
        Easy to avoid duplicates since duplicates in sorted are side by side
        Time = O(n^2)(we call sum(O(n)) n times), space = O(n)
    */

    public List<List<Integer>> threeSumApp2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, res);
            }
        return res;
    }
    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        Set<Integer> seen = new HashSet<Integer>();
        int target = -nums[i];
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = target - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j+1 < nums.length && nums[j] == nums[j + 1])
                    j++;
            }
            seen.add(nums[j]);
        }
    }

    /* 
    Approach 3 : using hash set but not sorted
    For each i , we do twoSum problem using hashMap, with target -nums[i]
    Easy to avoid duplicates we use DUps hashSet
    Time = O(n^2), Space = O(n)
    */
    public List<List<Integer>> threeSumApp3(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        Set<Integer> dups = new HashSet<Integer>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (!dups.contains(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    // we do seen.get(complement) == i bcoz we want ot make sure the target we are considering is -nums[i]
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
                dups.add(nums[i]);
            }

        return new ArrayList(res);
    }
}