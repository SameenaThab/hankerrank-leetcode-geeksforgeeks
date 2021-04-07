/* 
As in Approach1, instead of computing the left and right parts seperately, we may think of some way to do it in one iteration.
Notice that as long as right_max[i]>left_max[i] (from element 0 to 6), the water trapped depends upon the left_max, and similar is the case when left_max[i]>right_max[i] (from element 8 to 11).
So, we can say that if there is a larger bar at one end (say right), we are assured that the water trapped would be dependant on height of bar in current direction (from left to right). 
As soon as we find the bar at other end (right) is smaller, we start iterating in opposite direction (from right to left). 
We must maintain right_max during the iteration, but now we can do it in one iteration using 2 pointers, switching between the two.

Algorithm:
Initialize left pointer to 0 and right pointer to size-1
While left<right, do:
    If height[left] is smaller than height[right]
        If height[left]≥left_max, update left_max
        Else add left_max−height[left] to ans
        Add 1 to left.
    Else
        If height[right]≥right_max, update right_max
        Else add right_max−height[right] to ans
        Subtract 1 from right.
*/
// time : O(n), Space O(1)
class TrapRainWater {
    public int trap(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length-1;
        int sum = 0;
        while(left<right) {
            if(height[left]<height[right]) {
                if(height[left]>=leftMax) {
                    leftMax = height[left];
                } else {
                    sum+=leftMax-height[left];
                }
                left++;
            } else {
                if(height[right]>=rightMax) {
                    rightMax = height[right];
                } else {
                    sum+=rightMax-height[right];
                }
                right--;
            }
        }
        return sum;
    }

    //SPace: O(n),Time O(n)
    public int trapApp1(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        
        int maxLeft = 0;
        int maxRight = 0;
        for(int i=0;i<height.length;i++) {
            leftMax[i] = maxLeft;
            maxLeft = Math.max(height[i],maxLeft);
        }
        
        for(int i=height.length-1;i>=0;i--) {
            rightMax[i]=maxRight;
            maxRight = Math.max(height[i],maxRight);
        }
        int sum = 0;
        
        for(int i=0;i<height.length;i++) {
            int minWall = Math.min(leftMax[i],rightMax[i]);
            if(minWall > height[i]) {
                sum+=minWall-height[i];
            }
        }
        
        return sum;
    }
}