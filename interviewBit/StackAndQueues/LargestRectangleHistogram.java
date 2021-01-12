import java.util.*;

public class LargestRectangleHistogram {

    /* 
    https://www.interviewbit.com/problems/largest-rectangle-in-histogram/
    A = [1, 4, 3, 4]
    A = [10, 7, 100]
    Iterate i through all histograms
    We maintain a stack with decreasing heights of histogram (highest histogram on top)
    1   Once we encounter a histogram with height < stack top.
    2   We keep popping until stack is empty or lesser height histogram is on the top.
        for every popped histogram we calculate area and update maxArea.
        we calculate area such that (height of popped histogram)*(width)
        width = R-L-1 = current i value - stack.peek()-1 - this gives maximum width between the popped histogram and stack.peek
        if stack is empty width = i (which means the popped histogram lasted for i width)

    after exitng loop i value is n
    repeat the same process#2 until stack is empty 
     */
    public static void main(String[] args) {
        LargestRectangleHistogram sol = new LargestRectangleHistogram();
        // ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(2, 1, 5, 6, 2, 3));
        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(1));
        System.out.println("LargestRectangleArea: "+sol.largestRectangleArea(A));
    }

    public int largestRectangleArea(ArrayList<Integer> A) {
        Stack<Integer> stack = new Stack<Integer>();
        int n = A.size();
        if(n == 1)
            return A.get(0);
        if(n == 0)
            return 0;
        int maxArea = 0;
        int i=0;
        for(i=0;i<n;i++) {
            if(stack.isEmpty() || A.get(i) >= A.get(stack.peek()) ) {
                stack.push(i);  // pushing everthing in decreasing order (Large values on top)
            }
            else { // when ith element is smaller than top
                while(!stack.isEmpty() && A.get(stack.peek()) > A.get(i)) {
                    int h = A.get(stack.pop());
                    int r = i;
                    int width = stack.isEmpty()? i : (r-stack.peek()-1);  // width between left and right 
                    int area = h*width;
                    if(area>maxArea)
                        maxArea = area;
                }
                stack.push(i);
            }
        }
        // at this point i is n and here we calculate areas for all remaining histograms in stack
        while(!stack.isEmpty()) {
            int h = A.get(stack.pop());
            int r = i;
            int width = stack.isEmpty()? i : (r-stack.peek()-1);  // width between left and right 
            int area = h*width;
            if(area>maxArea)
                maxArea = area;
        }  
        
        return maxArea;
    }

}
