import java.util.*;
import java.util.stream.Collectors;

/* 
https://algo.monster/problems/two_sum_sorted
Given an array of integers sorted in ascending order, find two numbers that add up to a given target. Return the indices of the two numbers in ascending order. You can assume elements in the array are unique and there is only one solution. Do this in O(n) time and with constant auxiliary space.
Input: [2 3 5 8 11 15], 5
Output: 0 1

Approach:
Since the array is sorted, we dont need a extra space like hashset
Maintain two pointers largest and smaller on each ends of list smaller at 0 and larger at n-1
if(list(smaller)+list(larger) < target)
    Increment smaller, bcoz to match target we need a slightly larger value. Best to increase a smaller value
if(list(smaller)+list(larger) > target)
    decrement larger, bcoz to match target we need a slightly smaller value. Best to decrease the larger value

Walkthru: [2 3 5 8 11 15], 5
We noticed that the array is sorted. This means the smallest two sum we can get is the sum of the first two numbers 2 + 3, and the largest two sum we can get is the sum of the last two numbers 11 + 18. If we sort all two sum pairs by their sum value, the middle point is the smallest number + largest number 2 + 18. From this point, we can compare with our target 8.

If our sum equals target, then we're done.
If our sum is less than the target, we need to exchange one of the numbers for a bigger number. Since 18 is already the bigger number available, we have to exchange 2 for a bigger number. We go to 3.
If our sum is greater than the target, we need to exchange one of the numbers for a smaller number. Since 2 is the smallest number available, we have to exchange 18 for a smaller number. We go to 11.
repeat the process until two sum is equal to our target.
    */
class TwoSumSorted {
    public static List<Integer> twoSumSorted(List<Integer> arr, int target) {
        int smaller = 0;
        int larger = arr.size()-1;
        List<Integer> result = new ArrayList<Integer>();
        while(smaller < larger) {
            int sum = arr.get(smaller)+arr.get(larger);
            if( sum == target) {
                result.add(smaller);
                result.add(larger);
                break;
            }
            else if(sum < target) {
                smaller++;
            }
            else {
                larger--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>(Arrays.asList(2,3,5,8,11,15));
        List<Integer> res = twoSumSorted(arr, 5);
        System.out.println(twoSumSorted(arr, 5));
        System.out.println(twoSumSorted(arr, 20));
        System.out.println(twoSumSorted(arr, 11));
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}