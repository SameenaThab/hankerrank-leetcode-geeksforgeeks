import java.util.*;
/* 
check approach 1,2 and 4 
https://leetcode.com/problems/trapping-rain-water/solution/
for any curr histogram find left_max within range[0(included) to currentIndex(included)] and right_max within range[currentIndex(included) to n-1(included)]
Min(left_max,right_max)-height[curr] gives water stored over the histogram
 */
public class RainWaterTrapped {

    public static void main(String[] args) {
        RainWaterTrapped sol = new RainWaterTrapped();
        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1));
        System.out.println("Solution: "+sol.trap(A));
    }

    public int trap(final List<Integer> A) {
        int left = 0;
        int right = A.size()-1;
        int left_max = 0;
        int right_max = 0;
        int ans = 0;
        while(left < right) {
            if(A.get(left) < A.get(right)) {
                if(A.get(left) >= left_max)
                    left_max=A.get(left);
                else
                    ans+=(left_max-A.get(left));
                left++;
            } else {
                if(A.get(right) >= right_max)
                    right_max=A.get(right);
                else
                    ans+=(right_max-A.get(right)); 
                right--;               
            }
        }
        return ans;
    }
}