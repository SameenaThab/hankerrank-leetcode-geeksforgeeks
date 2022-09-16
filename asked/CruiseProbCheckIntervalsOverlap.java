import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class CruiseProbCheckIntervalsOverlap {

    static class Interval{
        int start;
        int end;
        Interval(int start,int end) {
            this.start= start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(1, 3), new Interval(7, 10), new Interval(2, 4)};
        Interval[] intervals2 = new Interval[]{new Interval(7, 10), new Interval(1, 3), new Interval(12, 13)};
        System.out.println(doesOverlap(intervals));
        System.out.println(doesOverlap(intervals2));
        int x = -1;
        int y = -2;
        System.out.println(~x);
        System.out.println(~x);
    }

    public static boolean doesOverlap(Interval[] intervals) {
        Arrays.sort(intervals,new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start-b.start;
            }
        });
        Interval prev = intervals[0];
        for(int i=1;i<intervals.length;i++) {
            Interval curr = intervals[i];
            if(prev.end >= curr.start) {
                return true;
            }
            prev = curr;
        }
        return false;
    }
}

/*
 *  [[1,3],[2,6],[8,10],[15,18]]
 * [1,2,8,15]
 * [3,6,10,18]
 * 
*/