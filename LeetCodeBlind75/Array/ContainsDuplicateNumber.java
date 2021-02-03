import java.util.*;

class ContainsDuplicateNumber {

    public static void main(String[] args) {
        ContainsDuplicateNumber sol = new ContainsDuplicateNumber();
        System.out.println("solution: "+sol.containsDuplicate(new int[]{1,2,3,1}));
        System.out.println("solution: "+sol.containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    }

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++) {
            if(nums[i-1]==nums[i])
                return true;
        }
        return false;
    }

    public boolean containsDuplicateUsingSet(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++) {
            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);
        }
        return false;
    }
}