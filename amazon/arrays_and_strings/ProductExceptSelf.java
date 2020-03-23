class ProductExceptSelf {
    //https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/499/
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0]=1;
        for(int i=1;i<n;i++) {
            result[i]=nums[i-1]*result[i-1];
        }
        int right = 1;
        for(int i=n-1;i>=0;i--) {
            result[i]=result[i]*right;
            right = right*nums[i];
        }
        return result;
    }
}