class FreqStack {
    //https://leetcode.com/explore/interview/card/amazon/81/design/3001/
    Map<Integer,Integer> freqMap;
    Map<Integer,Stack<Integer>> stackMap;
    int maxFreq;

    public FreqStack() {
        freqMap = new HashMap<Integer,Integer>();
        stackMap = new HashMap<Integer,Stack<Integer>>();
        maxFreq = 0;
    }
    
    public void push(int x) {
        int freq = freqMap.getOrDefault(x,0)+1;
        freqMap.put(x,freq);
        if(freq>maxFreq){
            maxFreq = freq;
        }
        Stack<Integer> st = stackMap.computeIfAbsent(freq,z -> new Stack<Integer>());
        st.push(x);
    }
    
    public int pop() {
        Stack<Integer> st = stackMap.get(maxFreq);
        int result = st.pop();
        freqMap.put(result, freqMap.get(result) - 1);
        if(st.isEmpty()) {
            maxFreq--;
        }
        return result;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */