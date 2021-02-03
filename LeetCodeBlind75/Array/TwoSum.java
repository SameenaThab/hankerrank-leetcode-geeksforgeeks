import java.util.*;

class TwoSum {

    public static void main(String[] args) {
        TwoSum sol = new TwoSum();
        int[] result1 = sol.twoSum(new int[]{2,7,11,15}, 9);
        int[] result2 = sol.twoSum(new int[]{3,2,4}, 6);
        System.out.println("solution: "+Arrays.toString(result1));
        System.out.println("solution: "+Arrays.toString(result2));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++) {
            // System.out.println("i:"+i+" "+nums[i]);
            if(map.containsKey(target-nums[i])) {
                // System.out.println("key present"); 
                return new int[]{map.get(target-nums[i]),i};
            }
            else {
                map.put(nums[i],i);
            }
        }
        return null;
    }
}