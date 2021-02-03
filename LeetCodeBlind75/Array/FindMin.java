class FindMin {
/* 
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

eg:
    4 5 6 7 2 3
    if arr[left] < arr[right] => array is not rotated
    else => rotated
    we need to find the inflection point to find the minimum number
    for 4 5 6 7 2 3 => between 7 and 2 is inflection point. Inflection point is where we notice the change
    For a rotated array arr[mid]<arr[right] => right side is normal, inflection point in left
    else left side is normal(that is arr[left]<arr[mid]), inflection point on right side
    we will stop binary search when we find inflection point
    Anyone condition should satisfy for inflection point
    arr[mid-1]>arr[mid], then min is arr[mid]
    eg: 4 5 6 7 2 3 => go right as left is sorted normally
        7 2 3 arr[mid-1]>arr[mid] => min is arr[mid]
    arr[mid]>arr[mid+1], then min is arr[mid+1]   
    eg: [4,5,6,7,0,1,2] => arr[mid] = 7 arr[mid+1]<arr[mid] => min = arr[mid+1]

*/
    public static void main(String[] args) {
        FindMin sol = new FindMin();
        System.out.println("solution: "+sol.findMin(new int[]{3,4,5,1,2}));
        System.out.println("solution: "+sol.findMin(new int[]{2,1}));
        System.out.println("solution: "+sol.findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println("solution: "+sol.findMin(new int[]{11,13,15,17}));
    }

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
                // by checking this case first we are avoiding index out of bound exception
                // eg [2,1] mid = 0,mid-1 = -1 (exception)
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