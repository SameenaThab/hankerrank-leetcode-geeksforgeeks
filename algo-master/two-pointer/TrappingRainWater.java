import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/* 
https://algo.monster/problems/trapping_rain_water
Given a list of non-negative integers representing elevations of columns, and assuming each column is of equal width of 1, find how much water is trapped in the columns after a rain. Left and right boundaries outside of the columns have 0 elevations.
Input: [3, 2, 1, 2, 2, 3, 2] Output: 5

First, let's understand the problem. The total water the entire structure can hold is the sum of the amount of water each position can hold.
Let's draw a figure to visualize. The water a position can hold is bound by the lower of the two walls.

Since we already know the elevation of each position we just have to find the left and right walls for each position.
Next, we should also realize the height of the wall of a particular direction is decided by the highest elevation to the right of the current position.

Therefore, the problem reduces to finding the wall for each cell from each direction. We can easily do this by going through the elevations array and keep track of max elevation.

*/
class TrappingRainWater {
    public static int trappingRainWater(List<Integer> elevations) {
        int[] leftMax = new int[elevations.size()];
        int[] rightMax = new int[elevations.size()];
        int maxAtLeft = 0;
        int maxAtRight = 0;
        for(int i=0;i<elevations.size();i++) {
            leftMax[i]=maxAtLeft;
            // we update maxAtLeft for the next elevation's leftWall
            maxAtLeft = Math.max(maxAtLeft,elevations.get(i));
        }
        for(int i=elevations.size()-1;i>=0;i--) {
            rightMax[i]=maxAtRight;
            // we update maxAtLeft for the next elevation's leftWall
            maxAtRight = Math.max(maxAtRight,elevations.get(i));
        }

        int sum = 0;

        for(int i=0;i<elevations.size();i++) {
            int minWall = Math.min(leftMax[i], rightMax[i]);
            if(elevations.get(i)<minWall){
                sum+=minWall-elevations.get(i);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        List<Integer> elevations = new ArrayList<Integer>(Arrays.asList(3, 2, 1, 2, 2, 3, 2));
        int res = trappingRainWater(elevations);
        System.out.println(res);
    }
}