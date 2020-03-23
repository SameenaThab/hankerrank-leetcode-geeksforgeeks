class MostWaterContainer {
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length-1;
        int maxArea = 0;
        while(l<r) {
            int h = Math.min(height[l],height[r]);
            maxArea = Math.max(maxArea,h*(r-l));
            if(height[l]<height[r])
                l++;
            else
                r--;
        }
        return maxArea;
    }

}