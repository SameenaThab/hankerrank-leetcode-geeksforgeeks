//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2966/
class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0;i<nums.length;i++) {
            if(i != 0 && nums[i] == nums[i-1]) continue;
            int j=i+1;
            int k=nums.length-1;
            while(j<k) {
                //System.out.println(nums[i]+" "+nums[j]+" "+nums[k]);
                if(nums[i]+nums[j]+nums[k] == 0) {
                    result.add(Arrays.asList(new Integer[]{nums[i],nums[j],-nums[i]-nums[j]}));
                    while(j+1 < nums.length && nums[j+1] == nums[j]) j++;
                    j++;
                    k--;
                }
                else if(nums[i]+nums[j]+nums[k]<0) {
                    j++;
                }
                else {
                    k--;
                }
            }
            
        }
        return result;
    }
    
    /*
    Not working
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0;i<nums.length;i++) {
            if(i != 0 && nums[i] == nums[i - 1]) continue;
            int target = 0-nums[i];
            System.out.println("target: "+target);
            for(int j=i+1;j<nums.length;j++) {
                System.out.println("j: "+nums[j]);
                Set<Integer> set = new HashSet<Integer>();
                //List<Integer> list = new ArrayList<Integer>();
                if(set.contains(-nums[i]-nums[j])) {
                    System.out.println("Here return");
                    //list.add(nums[i]);
                    //list.add(nums[j]);
                    //list.add(-nums[i]-nums[j]);
                    result.add(Arrays.asList(new Integer[]{nums[i],nums[j],-nums[i]-nums[j]}));
                    while(j+1<nums.length && nums[j+1] == nums[j]) j++;
                }
                set.add(nums[j]);
            }
        }
        return result;
    }
    */
}