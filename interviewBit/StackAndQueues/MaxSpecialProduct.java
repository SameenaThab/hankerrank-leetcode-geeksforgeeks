import java.util.*;
/* 
https://www.interviewbit.com/problems/maxspprod/
Ok, lets try to calculate LeftSpecialValue.

Lets make a stack A.
We will keep elements in strictly decreasing order in stack A.

When processing the ith element remove all the elements from the stack which have value less than or equal to ith element.
* You might think what if are removing leftSpecial value of future element?
ans: if the futture element is smallest value, then the nearest large left value will be this curr element.
    therefore it is safe to remove
    eg: 10 5 8 1
        for 8 , LSV = 10
        though 5 can be LSV for 1, but it is not the closest 8 is closest, thrfr, it is safe to remove 5
Now whatever element will remain at the top of index will give the index corresponding to LeftSpecialValue. If no element is at top then take it as 0.

Push ith element in stack A now.

You can see each element is pushed and popped for atmax one time. Hence the solution runs in O(n) time.

Do the similar thing for RightSpecialValue.
*/
public class MaxSpecialProduct {

    /* 
    A = [1, 4, 3, 4]
    A = [10, 7, 100]
     */
    public static void main(String[] args) {
        MaxSpecialProduct sol = new MaxSpecialProduct();
        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(6, 7, 9, 5, 5, 8));
        System.out.println("MaxSpecialProduct: "+sol.maxSpecialProduct(A));
        A = new ArrayList<Integer>(Arrays.asList(1, 4, 3, 4));
        System.out.println("MaxSpecialProduct: "+sol.maxSpecialProduct(A));
    }

    int MOD = 1000000007;
    public int maxSpecialProduct(ArrayList<Integer> A) {
        Stack<Integer> stack = new Stack<Integer>();
        int n = A.size();
        int[] leftSpecialValues = new int[n];
        int[] rightSpecialValues = new int[n];
        for(int i=0;i<n;i++) {
            while(!stack.isEmpty() && A.get(stack.peek())<=A.get(i)) {
                stack.pop();
            }
            leftSpecialValues[i] = stack.isEmpty()? -1 : stack.peek();
            stack.push(i);
        }

        stack = new Stack<Integer>();

        for(int i=n-1;i>=0;i--) {
            while(!stack.isEmpty() && A.get(stack.peek())<=A.get(i)) {
                stack.pop();
            }
            rightSpecialValues[i] = stack.isEmpty()? -1 : stack.peek();
            stack.push(i);
        }
        long maxSpecialProduct = Integer.MIN_VALUE;
        // System.out.println("leftSpecialValues: "+Arrays.toString(leftSpecialValues));
        // System.out.println("rightSpecialValues: "+Arrays.toString(rightSpecialValues));
        for(int i=0;i<n;i++) {
            if(leftSpecialValues[i] < 0 || rightSpecialValues[i] < 0)
                continue;
            long possible = (long)leftSpecialValues[i]*(long)rightSpecialValues[i];
            if(possible > maxSpecialProduct)
                maxSpecialProduct = possible;
        }
        if(maxSpecialProduct == Integer.MIN_VALUE)
            maxSpecialProduct = 0;
        return (int)(maxSpecialProduct%1000000007);
    }
}
