import java.util.*;

public class NearestSmallerElement {

    /* 
    A = [1, 4, 3, 4]
    A = [10, 7, 100]
    https://www.interviewbit.com/problems/nearest-smaller-element/
     */
    
    public static void main(String[] args) {
        NearestSmallerElement sol = new NearestSmallerElement();
        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(34, 35, 27, 42, 5, 28, 39, 20, 28));
        System.out.println("MaxSpecialProduct: "+sol.prevSmaller(A));
    }

    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        Stack<Integer> stack = new Stack<Integer>();
        int n = A.size();
        ArrayList<Integer> nearestSmallerElements = new ArrayList<Integer>(n);
        for(int i=0;i<n;i++) {
            while(!stack.isEmpty() && stack.peek()>=A.get(i)) {
                stack.pop();
            }
            nearestSmallerElements.add(stack.isEmpty()? -1 : stack.peek());
            stack.push(A.get(i));
        }
        return nearestSmallerElements;
    }

}
