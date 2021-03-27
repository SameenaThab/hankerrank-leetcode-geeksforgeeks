import java.util.Arrays;

/* 
https://algo.monster/problems/min_steps_to_make_piles_equal_height

Given N piles of equal or unequal heights. In one step, You can remove any number of boxes from the pile which has the maximum height and try to make it equal to the one which is just lower than the maximum height of the stack. Determine the minimum number of steps required to make all of the piles equal in height.

Example 1:
Input: [5, 2, 1]
Output: 3
Explanation:
Step 1: reducing 5 -> 2 = [2, 2, 1] Step 2: reducing 2 -> 1 = [2, 1, 1] Step 3: reducing 2 -> 1 = [1, 1, 1]

*/
class MinStepsPile {
    int minStepsPile(int[] arr) {
        if(arr.length < 2)
            return 0;
        Arrays.sort(arr);
        int count=0;
        int n = arr.length;
        for(int i=1;i<n;i++) {
        // Since the array is in descending order we travers in reverse
            if(arr[n-i] != arr[n-i-1])
            // we add i instead of 1 bcoz even though it one step to make values match,
            // we might come back again to match with even reduced value, see example
                count+=i;  
        }
        return count;
    }

    public static void main(String[] args) {
        MinStepsPile sol = new MinStepsPile();
        System.out.println("Output should be 3: "+sol.minStepsPile(new int[]{5,2,1}));
        System.out.println("Output should be 1: "+sol.minStepsPile(new int[]{1,1,5}));
        System.out.println("Output should be 1: "+sol.minStepsPile(new int[]{1,2,4,5}));
    }
}