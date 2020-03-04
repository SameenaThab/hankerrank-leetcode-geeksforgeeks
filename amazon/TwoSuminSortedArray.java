class TwoSuminSortedArray {
    //https://leetcode.com/explore/interview/card/amazon/79/sorting-and-searching/2994/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(target-nums[i]))
                return new int[]{map.get(target-nums[i])+1,i+1};
            else
                map.put(nums[i],i);
        }
        
        return null;
    }
}