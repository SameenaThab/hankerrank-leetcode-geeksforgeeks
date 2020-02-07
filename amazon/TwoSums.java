//https://leetcode.com/problems/two-sum/submissions/
class TwoSums {
    public int[] twoSum(int[] nums, int target) {
       Map<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++) {
            int comp = target-nums[i];
            if(hash.containsKey(comp) && hash.get(comp) != i)
                return new int[]{hash.get(comp),i};
            hash.put(nums[i],i);
        }
        return null;
    }
}