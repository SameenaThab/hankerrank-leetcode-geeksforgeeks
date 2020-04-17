class MeetingRoomsMinHeap {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length<=0)
            return 0;
       Arrays.sort(intervals,new Comparator<int[]>(){
           @Override
           public int compare(int[] a1,int[] a2) {
               return a1[0]-a2[0];
           }
       });
    
        //min heap to track min end time
        PriorityQueue<Integer> minHeap= new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a,Integer b) {
                return a-b;
            }
        });
        
        minHeap.add(intervals[0][1]);
        for(int i=1;i<intervals.length;i++) {
            if(intervals[i][0]>=minHeap.peek()) {
                //remove the over meeting
                minHeap.poll();
            }              
            // add a new running meeting in queue
            minHeap.add(intervals[i][1]);
        }
    return minHeap.size();
    }
}