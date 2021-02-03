import java.util.*;

/* https://leetcode.com/problems/merge-intervals/

Sort the intervals according to their start time
Intialize a result list
add  intervals one by one, merge if necessary
time O(nlogn) ,Space = O(logn){mergeble}/O(n)
*/
class MergeIntervals {

    public static void main(String[] args) {
        // int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = new int[][]{{1,4},{0,4}};
        System.out.print("intervals before: ");
        for(int[] interval:intervals){
            System.out.print(Arrays.toString(interval)+" ");
        }
        System.out.println();
        MergeIntervals sol = new MergeIntervals();
        int[][] result = sol.merge(intervals);
        System.out.print("intervals after: ");
        for(int[] interval:result){
            System.out.print(Arrays.toString(interval)+" ");
        }
        System.out.println();
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1)
            return intervals;
        Arrays.sort(intervals,new Comparator<int[]>() {
            @Override
            public int compare(int[] a,int[] b) {
                return a[0]-b[0];
            }
        });
        LinkedList<int[]> list = new LinkedList<int[]>();
        int i = 0;
        int n = intervals.length;
        int[] interval = new int[2]; 
        while(i<n) {
            if(list.isEmpty() || list.getLast()[1]<intervals[i][0])
                list.add(intervals[i]);
            else {
                interval = list.removeLast();
                interval[1]=Math.max(interval[1],intervals[i][1]);
                list.add(interval);                
            }
            i++;
        }
        return list.toArray(new int[list.size()][2]);
    }
}