import java.util.*;
/* 
https://leetcode.com/problems/meeting-rooms-ii/
 */
class MinMeetingRooms {

    public static void main(String[] args) {
        int[][] intervals1 = new int[][]{{0,30},{5,10},{15,20}};
        int[][] intervals2 = new int[][]{{7,10},{2,4}};
        int[][] intervals3 = new int[][]{{9,10},{4,9},{4,17}};
        MinMeetingRooms sol = new MinMeetingRooms();
        System.out.println("result1: "+sol.minMeetingRooms(intervals1));
        System.out.println("result1: "+sol.minMeetingRooms(intervals2));
        System.out.println("result1: "+sol.minMeetingRooms(intervals3));
        System.out.println("result1: "+sol.minMeetingRoomsUsingPQ(intervals1));
        System.out.println("result1: "+sol.minMeetingRoomsUsingPQ(intervals2));
        System.out.println("result1: "+sol.minMeetingRoomsUsingPQ(intervals3));
    }

    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        int i=0;
        for(int[] interval:intervals) {
            start[i] = interval[0];
            end[i] = interval[1];
            i++;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int sp=0,ep=0;
        // ep maintains the least end meeting time
        int count=0;
        while(sp<n) {
            // some meeting ended
            if(start[sp]>=end[ep]){
                ep++;
            } else{
                count++; // nothing ended add meeting rooms
            }
            sp++;
        }
        return count;
    }

    public int minMeetingRoomsUsingPQ(int[][] intervals) {
        int n = intervals.length;
        int count =0;
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b) {
                return a[0]-b[0];
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n,new Comparator<Integer>(){
            @Override
            public int compare(Integer a,Integer b) {
                return a-b;
            }
        });
        pq.add(intervals[0][1]);
        for(int i=1;i<n;i++){
            if(pq.peek()<=intervals[i][0]) { // meeting in queue ended
                pq.remove();
            } 

            pq.add(intervals[i][1]);
        }
        return pq.size();
    }
}