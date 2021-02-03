import java.util.*;

/* https://leetcode.com/problems/insert-interval/ 

[1,2],[5,6] insert [3,4] -> [1,2],[3,4],[5,6]
[1,3],[4,6] insert [2,4] -> [1,6]
[1,3],[6,7] insert [2,4] -> [1,4],[6,7]
[4,5],[6,7] insert [2,4] -> [2,5],[6,7]

Intialize a result list
add all intervals smaller than new interval to the list
add new interval, merge if necessary
add all remaining intervals and merge if necessary to the list
*/
class InsertInterval {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = new int[]{4,8};
        System.out.print("intervals: ");
        for(int[] interval:intervals){
            System.out.print(Arrays.toString(interval)+" ");
        }
        System.out.println();
        System.out.print("newInterval: ");
        System.out.println(Arrays.toString(newInterval));
        InsertInterval sol = new InsertInterval();
        int[][] result = sol.insert(intervals,newInterval);
        System.out.print("result: ");
        for(int[] interval:result){
            System.out.print(Arrays.toString(interval)+" ");
        }
        System.out.println();
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> list = new LinkedList<int[]>();
        int start = newInterval[0];
        int end = newInterval[1];
        // add all intervals smaller than new interval
        int i = 0;
        int n = intervals.length;
        while(i<n && intervals[i][0]<start) {
            list.add(intervals[i]);
            i++;
        }
        int[] interval = new int[2]; 
        // add new interval merge if necessary
        if(list.isEmpty() || list.getLast()[1]<start) {  // not mergeble condition
            list.add(newInterval);
        } else { // mergeble
            interval = list.removeLast();
            interval[1]=Math.max(interval[1],end);
            list.add(interval);
        }
        // now add all remaining intervals and merge if necessary
        while(i<n) {
            if(list.getLast()[1]<intervals[i][0]) {  // not mergeble condition
                list.add(intervals[i]);
            } else { // mergeble
                interval = list.removeLast();
                interval[1]=Math.max(interval[1],intervals[i][1]);
                list.add(interval);
            }      
            i++;      
        }
        return list.toArray(new int[list.size()][2]);
    }
}