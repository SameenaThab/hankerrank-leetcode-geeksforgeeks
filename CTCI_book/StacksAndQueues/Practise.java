import java.util.*;

public class Practise {

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        int[] arr= {1,10,7,11,2,8};
        for (int i : arr) {
            st.push(i);
        }
        sortStackSmallestFirst(st);
        System.out.println(st.toString());
        assert st.peek()==1:"top should be 1";
        assert st.pop()==1:"top should be 1";
    }

    /*
     * In order sort a stack such that smallest is on top
     * add elements in the buffer stack with largest on top (reverse)
     * then pop all the elements in buffer into main stack to get desired sort(smallest is on top)
     * Time - O(n^2)
    */
    public static void sortStackSmallestFirst(Stack<Integer> st) {
        Stack<Integer> buffer = new Stack<>(); 
        while(!st.isEmpty()) {
            int top = st.pop();
            while(!buffer.isEmpty()&&buffer.peek()>top) {
                st.push(buffer.pop()); // pop all larger number to st;
            }
            buffer.push(top); // push smaller number
        }
        while(!buffer.isEmpty()) {
            st.push(buffer.pop());
        }
    }
}
