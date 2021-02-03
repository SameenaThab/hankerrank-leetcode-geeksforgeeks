import java.util.*;

class ContainerWithMostWater {
    /* 
    https://leetcode.com/problems/container-with-most-water/
     */

    public static void main(String[] args) {
        ContainerWithMostWater sol = new ContainerWithMostWater();
        System.out.println("Solution: "+sol.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println("Solution: "+sol.maxArea(new int[]{1,1}));
        System.out.println("Solution: "+sol.maxArea(new int[]{4,3,2,1,4}));
        System.out.println("Solution: "+sol.maxArea(new int[]{2,3,4,5,18,17,6}));
        System.out.println("Solution: "+sol.maxArea(new int[]{1,2,1}));
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length-1;
        while(l<r) {
            maxArea = Math.max(maxArea,Math.min(height[l],height[r])*(r-l));
            if(height[l]<height[r]) {
                l++; // bcoz we already considered area with height[l], Math.min(height[l],height[r])*(r-l)
            }
            else {
                r--;
            }
        }
        return maxArea;
    }
}