class RotatedArrSearch {

    /* https://leetcode.com/problems/search-in-rotated-sorted-array/submissions/
    Same as CTCI RotatedArrSearch_Chap10Prob3 problem
    */
    public static void main(String[] args) {
        RotatedArrSearch sol = new RotatedArrSearch();
        System.out.println("solution: "+sol.search(new int[]{4,5,6,7,0,1,2},0));
        System.out.println("solution: "+sol.search(new int[]{4,5,6,7,0,1,2},3));
        System.out.println("solution: "+sol.search(new int[]{3,5,1},3));
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n-1;
        while(right >= left) {
            int mid = (left+right)/2;
            if(nums[mid] == target)
                return mid;

            if(nums[mid]<nums[right]) { 
                // right is normal
                if(target>nums[mid] && target<=nums[right])
                    left = mid+1;
                else
                    right = mid-1;
            } 
            else  {
                // inflection at right, left is normal
                if(target>=nums[left] && target<nums[mid])
                    right = mid-1;
                else
                    left = mid+1;  
            }                     
        } 
        return -1;       
    }
}