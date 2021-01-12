import java.util.*;

class MinStack {

    public static void main(String[] args) {
        MinStack sol = new MinStack();
        sol.push(2);
        sol.push(1);
        System.out.println(sol.getMin());
        sol.pop();
        System.out.println(sol.getMin());
        sol.pop();
        System.out.println(sol.getMin());
    }

    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x<=minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if(stack.isEmpty())
            return;
        // when top is the minimum element
        if(stack.peek() == minStack.peek()) {
            stack.pop();
            minStack.pop();
        }
        else {
            stack.pop();
        }
    }

    public void popAcceptedOnInterviewBit() {        
        if(!stack.isEmpty()){
            int num = stack.pop();
            if(num == minStack.peek())
                minStack.pop();
        }
    }

    public int top() {
        return stack.isEmpty()? -1: stack.peek();
    }

    public int getMin() {
        return minStack.isEmpty()? -1: minStack.peek();
    }
}