import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/* 
https://algo.monster/problems/move_zeros
Given an array of integers, move all the 0s to the back of the array while maintaining the relative order of the non-zero elements. Do this in-place using constant auxiliary space.
Input:
[1, 0, 2, 0, 0, 7]
Output:
[1, 2, 7, 0, 0, 0]

for all non zero values
    fast and slow are swapped
    slow pointers is incremented 
for all zero values 
    only fast pointer is incremented.
    Therefore when non zerovalue is encountered, slow pointer is next to last non zero value

[1(s,f), 0, 2, 0, 0, 7] -> [1, 0(s), 2(f), 0, 0, 7]
[1, 2, 0(s), 0, 0, 7(f)] -> [1,2,7,0,0,0]
*/
class MoveZeros {
    public static void moveZeros(List<Integer> nums) {
        int fast = 0;
        int slow = 0;
        for(fast=0;fast<nums.size();fast++) {
            if(nums.get(fast) != 0) {
                int temp = nums.get(slow);
                nums.set(slow,nums.get(fast));
                nums.set(fast,temp);
                slow++;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>(Arrays.asList(1, 0, 2, 0, 0, 7));
        moveZeros(nums);
        System.out.println(nums);
    }
}