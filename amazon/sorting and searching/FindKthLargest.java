//https://leetcode.com/explore/interview/card/amazon/79/sorting-and-searching/482/
class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int n  = nums.length;
        // k largest is n-k smallest
        int kSmallest = n-k;
        int left = 0;
        int right = n-1;
        while(left <= right) {
            int index = partition(nums, left,right);
            if(kSmallest == index)
                return nums[index];
            else if(index < kSmallest)
                left = index+1;
            else
                right = index-1;   
        }

        return -1;        
    }
    
    public static int partition(int[] nums, int left,int right) {
        int pivot = nums[right];
        int i = left-1;
        for(int j=left;j<=right;j++) {
            if(nums[j]<pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        i++;
        int temp = nums[i];
        nums[i] = nums[right];
        nums[right] = temp;
        return i;
    }
}