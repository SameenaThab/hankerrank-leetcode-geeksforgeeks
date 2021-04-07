/* 
https://algo.monster/problems/subarray_sum

Given an array of integers and an integer target, find a subarray that sums to target and return the start and end indices of the subarray. It's guaranteed to have a unique solution.
Input:
1 -20 -3 30 5 4
7
Output: [1 4) right limit is open . i.e., element at index 4 is not included in target sum
Explanation: -20 - 3 + 30 = 7. The indices for subarray [-20,-3,30] is 1 and 4 (right exclusive).

Approach:
The brute force way is to find the sum of each subarray and compare it with the target. Let N be the number of elements in the array.
There are N subarrays with size 1, N-1 subarrays with size 2 .. and 1 subarray with size N.
Time complexity is O(N^2).
A key observation is the the sum of a subarray [i, j] is equal to the sum of [0, j] minus the sum of [0, i - 1].
The sum of elements from index 0 to i is called the prefix sum. If we have the subarray sum for each index, we can find the sum of any subarray in constant time.

With the knowledge of prefix sum under our belt, the problem boils down to Two Sum. We keep a dictionary of prefix_sum: index while going through the array calculating prefix_sum. If at any point, prefix_sum - target is in the dictionary we have found our subarray.
*/
import java.util.*;

class SubarraySum {
    public static List<Integer> subarraySum(List<Integer> arr, int target) {
        Map<Integer,Integer> prefixSum = new HashMap<Integer,Integer>();
        // This will cover the case where subarray[0 to anyIndex] = target
        // that means prefix-target = 0;
        prefixSum.put(0,0);
        int currTotal = 0;
        for(int i=0;i<arr.size();i++) {
            currTotal+=arr.get(i);
            int complement = currTotal-target;
            if(prefixSum.containsKey(complement)) {
                //i+1 bcoz range is right exclusive. i.e., right limit is not included in sum
                return List.of(prefixSum.get(complement),i+1);
            } else {
                prefixSum.put(currTotal,i+1);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList(Arrays.asList(1,-20,-3,30,5,4));
        System.out.println("Input: "+arr);
        System.out.println("For Target: 7 "+subarraySum(arr, 7));
        System.out.println("For Target: 8 "+subarraySum(arr, 8));
        System.out.println("For Target: 8 "+subarraySum(arr, 27));
    }
}