import java.util.*;

/* 
Solution should not have multiplication
https://leetcode.com/problems/product-of-array-except-self/solution/
*/

class ProductExceptSelf {
    public static void main(String[] args) {
        ProductExceptSelf sol = new ProductExceptSelf();
        int[] result1 = sol.productExceptSelf(new int[]{1,2,3,4});
        int[] result2 = sol.productExceptSelf(new int[]{24,12,8,6});
        System.out.println("solution: "+Arrays.toString(result1));
        System.out.println("solution: "+Arrays.toString(result2));
        int[] result3 = sol.productExceptSelfSpaceOptimal(new int[]{1,2,3,4});
        int[] result4 = sol.productExceptSelfSpaceOptimal(new int[]{24,12,8,6});
        System.out.println("solution: "+Arrays.toString(result3));
        System.out.println("solution: "+Arrays.toString(result4));
    }

    /* 
    For every given index, i,
    we will make use of the product of all the numbers to the left of it
    And multiply it by the product of all the numbers to the right. 
    This will give us the product of all the numbers except the one at the given index i. 

    Initialize two empty arrays, L and R where for a given index i, 
    L[i]=L[i-1]*nums[i-1] L[0] = 1
    R[i]=R[i+1]*nums[i+1] R[n+1] = 1

    time = O(n), space=O(n)
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0]=1;
        right[n-1]=1;
        for(int i=1;i<nums.length;i++) {
            left[i]=left[i-1]*nums[i-1];
        }
        for(int i=n-2;i>=0;i--){
            right[i]=right[i+1]*nums[i+1];
        }

        int[] result = new int[n];
        for(int i=0;i<n;i++) {
            result[i] = left[i]*right[i];
        }
        return result;
    }

    /* 
    To save space we will use the result array as left array and calculate R onn the fly
    Intialize and fill result array like we filled left array in previous approach
    Then traverse from right and do
    answer[i] = answer[i]*R, where R = R * nums[i]
   */
    public int[] productExceptSelfSpaceOptimal(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0]=1;
        for(int i=1;i<nums.length;i++) {
            result[i]=result[i-1]*nums[i-1];
        }
        int R = 1;
        for(int i=n-1;i>=0;i--){
            result[i] = result[i]*R;
            R*=nums[i];
        }
        return result;
    }


}