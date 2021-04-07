import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    /*
    maintain half elements in maxHEap and half in minHeap
    In maxHeap maintain lowest half elements
    In minHeap maintain highest half element (so that top will have median)
    for arr 3,5,2,4,1
    minHeap has 3(top),4,5
    maxHeap has 2(top),1

    Time complexity: O(5⋅logn)+O(1)≈O(logn).
    At worst, there are three heap insertions and two heap deletions from the top. Each of these takes about O(logn) time.
    Finding the median takes constant O(1) time since the tops of heaps are directly accessible.
    
    Space complexity: O(n) linear space to hold input in containers.
    */
    PriorityQueue<Integer> minHeap; // lowest number on top
    PriorityQueue<Integer> maxHeap; // highest number on Top
    /** initialize your data structure here. */
    public MedianFinder() {
         minHeap = new PriorityQueue<Integer>();
         maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        //whatever number push to minHeap, it sorts and puts lowest num on top
        minHeap.add(num); 
        // push the lowest number on maxHeap
        maxHeap.add(minHeap.poll());
        if(minHeap.size() < maxHeap.size()) {
            // push the largest number in minHeap
            minHeap.add(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        return minHeap.size()>maxHeap.size()? minHeap.peek():(maxHeap.peek()+minHeap.peek())*0.5;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */