//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2975/
class WaterTrap {
    public int trap(int[] height) {
        int n = height.length;
        int ans=0;
        int l=0;
        int r=n-1;
        int left_max = 0;
        int right_max = 0;
        while(l<r) {
            if(height[l]<height[r]) {
                if(left_max<=height[l])
                    left_max=height[l];
                else
                    ans+=left_max-height[l];
                l++;
            }
            else {
                if(right_max<=height[r])
                    right_max=height[r];
                else
                    ans+=right_max-height[r];
                r--;
            }
        }
        return ans;
    }
    
    // public int trap(int[] height) {
    //     int n = height.length;
    //     int[] left = new int[n];
    //     int[] right = new int[n];
    //     if(n < 2)
    //         return 0;
    //     left[0]=height[0];
    //     for(int j=1;j<n;j++) {
    //         left[j] = Math.max(height[j],left[j-1]);
    //     }
    //     right[n-1]=height[n-1];
    //     for(int j=n-2;j>0;j--) {
    //         right[j] = Math.max(height[j],right[j+1]);
    //     }
    //     int sum = 0;
    //     for(int i=1;i<=n-2;i++) {
    //         sum+=Math.min(left[i],right[i])-height[i];
    //     }
    //     return sum;
    // }
}