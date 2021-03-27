import java.util.*;

public class Interview2 {
    /*Given an array of integers “nums” and an integer target, return indices of the two numbers such that they add up to target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
    You can return the answer in any order.
    Example: Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Output: Because nums[0] + nums[1] == 9, we return [0, 1].
    map <2,0>
    target-ele in map
    9-7 = 2 return [0,1]
    <11,2>
    */
    public static void main(String... args) {
        int[] input = {4, 8, 22, 7, 9, -64, -9, 23, 68, 0, 32};
        int target = -64;
        int[] result = getSumIndices(input,target);
        if(result == null)
            System.out.println("null");
        else
            System.out.println(result[0]+","+result[1]);
    }

    private static int[] getSumIndices(int[] input,int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<input.length;i++) {
            int rem = target-input[i];
            if(map.containsKey(rem)) {
                return new int[]{map.get(rem),i};
            }
            map.put(input[i],i);
        }
        return null;
    }
}