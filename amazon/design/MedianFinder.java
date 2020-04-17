class MedianFinder {

    //https://leetcode.com/explore/interview/card/amazon/81/design/495/

    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
       @Override
        public int compare(Integer a,Integer b) {
            return b-a;
        }
    });
    
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    
    /** initialize your data structure here. */
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        maxHeap.add(num); 
        if(maxHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
        if(minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
    
    public double findMedian() {
        int n = maxHeap.size()+minHeap.size();
        if(n%2 == 0)
            return (maxHeap.peek()+minHeap.peek())*0.5;
        else
            return maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */