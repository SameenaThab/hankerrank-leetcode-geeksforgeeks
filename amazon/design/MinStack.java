class MinStack {

    //https://leetcode.com/explore/interview/card/amazon/81/design/503/
    
    Stack<Integer> s1;
    Stack<Integer> s2;

    /** initialize your data structure here. */
    public MinStack() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }
    
    public void push(int x) {
        s1.push(x);
        if(s2.isEmpty())
            s2.push(x);
        else if(s2.peek()>=x) {
            s2.push(x);
        }
    }
    
    public void pop() {
        int s2Top = s2.peek();
        int s1Top = s1.peek();
        if(s2Top == s1Top) {
            //System.out.println("here 2");
            s2.pop();
        }
        s1.pop();
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        //System.out.println("here");
        return s2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */