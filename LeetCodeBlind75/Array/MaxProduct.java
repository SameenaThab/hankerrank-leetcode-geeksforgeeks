class MaxProduct {

    public static void main(String[] args) {
        MaxProduct sol = new MaxProduct();
        System.out.println("solution: "+sol.maxProduct(new int[]{2,3,-2,4}));
        System.out.println("solution: "+sol.maxProduct(new int[]{-2,3,-4}));
        System.out.println("solution: "+sol.maxProduct(new int[]{0,2}));
    }

    /*
    https://leetcode.com/problems/maximum-product-subarray/submissions/
    if all numbers encountered are positive and greater than 0, max prod will be increasing with elements traversed
    but if we encounter 0, the maxProd stop there and restarts 
    if we encounter minimum, we still have hope of getting max prod, if we encounter negative number in future
    eg: arr={-2,3,-4}, Products_till = {-2,-6,24}
    So for accomodating negative numbers, we use min_so_far 
    max_so_far = Math.max(curr,Math.max(max_so_far*curr,min_so_far*curr))
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int min_so_far = nums[0];
        int max_so_far = nums[0];
        int result = max_so_far;
        for(int i=1;i<n;i++){
            int temp = Math.max(nums[i],Math.max(max_so_far*nums[i],min_so_far*nums[i]));
            // min_so_far can be curr element or its multiple with min_so_Far or its multiple with max_so_Far
            // any of the case, we wont lose sequence
            min_so_far = Math.min(nums[i],Math.min(max_so_far*nums[i],min_so_far*nums[i]));
            max_so_far = temp; // we need prior max_so_far to update min_so_far
            result = Math.max(max_so_far, result);
        }
        return result;
    }
}