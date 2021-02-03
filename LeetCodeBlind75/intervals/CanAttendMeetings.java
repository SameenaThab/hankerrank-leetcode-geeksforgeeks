import java.util.*;

class CanAttendMeetings {

    public static void main(String[] args) {
        int[][] intervals1 = new int[][]{{0,30},{5,10},{15,20}};
        int[][] intervals2 = new int[][]{{7,10},{2,4}};
        CanAttendMeetings sol = new CanAttendMeetings();
        System.out.println("result1: "+sol.canAttendMeetings(intervals1));
        System.out.println("result1: "+sol.canAttendMeetings(intervals2));
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length == 0)
            return true;
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b) {
                return a[0]-b[0];
            }
        });
        int n = intervals.length;
        for(int i=1;i<n;i++){
            if(intervals[i-1][1]>intervals[i][0])
                return false;
        }
        return true;
    }
}