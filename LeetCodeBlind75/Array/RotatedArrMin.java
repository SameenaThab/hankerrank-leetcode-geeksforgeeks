//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
class RotatedArrMin {
    public int findMin(int[] nums) {
        int n = nums.length;
        if(n == 1)
            return nums[0];
        if(nums[0]<nums[n-1]) // no rotation
            return nums[0];  
        int left = 0;
        int right = n-1;
        while(right >= left) {
            int mid = (left+right)/2;
              // if the mid element is greater than its next element then mid+1 element is the smallest
              // This point would be the point of change. From higher to lower value.
              if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
              }

              // if the mid element is lesser than its previous element then mid element is the smallest
              if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
              }
            if(nums[mid]<nums[right]) // inflection at left, right is normal
                right = mid-1;
            else  // inflection at right, left is normal
                left = mid+1;                        
        } 
        return -1;       
    }
}