import java.util.*;
/* 
https://www.interviewbit.com/problems/sliding-window-maximum/
    A = [1, 3, -1, -3, 5, 3, 6, 7]
    B = 3

    Initialize a deque, add first k elements such that
    All smaller element indexes are added to the last, so that the largest element index is pushed to the front
    If current element is greater than queue's last position, 
    then remove last element until there are smaller thant current element.
     We do this since the current element has larger values, 
     we dont need anyother smaller elements as there will not make greatest element for any window
    add the current index to the last of dequeu
    add the front element to result(MAximum for the window)
    Remove all out of the window (index<=i-k) elements from first
    repeat until i<n
    
 */
public class SlidingMaximum {

    public static void main(String[] args) {
        SlidingMaximum sol = new SlidingMaximum();
        // ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7));
        // int k = 3;
        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        int k = 2;
        System.out.println("Solution: "+sol.slidingMaximum(A,k));
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int k) {
        Deque<Integer> dq = new LinkedList<Integer>();
        ArrayList<Integer> windowMax = new ArrayList<Integer>();
        int i=0;
        int n = A.size();
        for(;i<k;i++){
            //making sure front index have large values and smaller elements are pushed from last.
            while(!dq.isEmpty() && A.get(i) >= A.get(dq.peekLast())) {
                dq.removeLast(); // Since new index with larger value is coming in we dont need these
            }
            dq.addLast(i);
        }
        windowMax.add(A.get(dq.peekFirst()));
        for(;i<n;i++) { // bcoz we know larger elements are stored in front
            while(!dq.isEmpty() && dq.peekFirst() <= i-k) {
                dq.removeFirst(); // removing all out of window indexes
            }            
            while(!dq.isEmpty() && A.get(i) >= A.get(dq.peekLast())) {
                dq.removeLast(); // Since new index with larger value is coming in we dont need these
            }
            dq.addLast(i); 
            
            windowMax.add(A.get(dq.peekFirst()));           
        }
        return windowMax;
    }
}
