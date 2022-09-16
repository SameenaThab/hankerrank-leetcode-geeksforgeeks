import java.util.Arrays;

/* 
 * [-1,2,1,-4] target = 1
 * 0  1  2 3
 * -4 -1 1 2
 *  i = -4 , 
 *  j = -1,comp = 1-(-4)-(-1) = 6 , -
 *  
*/ 

class ThreeSumClosest {

    public static void main(String[] args) {
        // System.out.println(threeSumClosestBS(new int[]{-1,2,1,-4},1));
        System.out.println(threeSumClosestBS(new int[]{0,0,0},1));
    }

    //https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2967/
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        
        Arrays.sort(nums);
        
        int closetSum = nums[0] + nums[1] + nums[2];
        
        for (int i = 0; i < nums.length; i++) {            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closetSum - target)) {
                    closetSum = sum;
                }
                
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }                        
        }
        
        return closetSum;
    }
    
    public int diff(int a,int b) {
        if(a<b) {
            return b-a;
        } else {
            return a-b;
        }
    }

    public static int threeSumClosestBS(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int n = nums.length;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < n && diff != 0; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int complement = target - nums[i] - nums[j];
                // binarySearch returns index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1). 
                // The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element greater than the key,
                //  or a.length if all elements in the array are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
                var idx = Arrays.binarySearch(nums, j + 1, n - 1, complement); 
                System.out.println("comp: "+complement+" idx: "+idx);
                int x = ~idx;
                System.out.println("x: "+x);
                int hi = idx >= 0 ? idx : ~idx, lo = hi - 1;  //~idx = -(idx+1) -> insertion point,(index of first number just greater than comp) , lo = index of greatest number among numbers greater than comp
                System.out.println("hi: "+hi+" lo: "+lo);  
                if (hi < n && Math.abs(complement - nums[hi]) < Math.abs(diff)) // hi< n then the insertion point is in the middle of the array, that numbers just greater than comp is at hi
                    diff = complement - nums[hi];
                if (lo > j && Math.abs(complement - nums[lo]) < Math.abs(diff))
                    diff = complement - nums[lo];
            }
        }
        return target - diff;
    }
}