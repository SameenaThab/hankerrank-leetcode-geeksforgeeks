class MedianFinder {
    
    PriorityQueue<Integer> leftMaxHeap;
    PriorityQueue<Integer> rightMinHeap;
        
    public MedianFinder() {
        this.rightMinHeap = new PriorityQueue<Integer>();
        this.leftMaxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
    }
    
    // 1 1 2 3 2
    public void addNum(int num) {
        leftMaxHeap.add(num);
        rightMinHeap.add(leftMaxHeap.poll());
        if(leftMaxHeap.size()<rightMinHeap.size()) {
            leftMaxHeap.add(rightMinHeap.poll());
        }
    }
    
    public double findMedian() {
        return leftMaxHeap.size()>rightMinHeap.size()?leftMaxHeap.peek():(leftMaxHeap.peek()+rightMinHeap.peek())*0.5;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */