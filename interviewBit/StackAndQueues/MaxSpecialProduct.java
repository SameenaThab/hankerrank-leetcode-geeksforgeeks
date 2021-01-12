import java.util.*;

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
