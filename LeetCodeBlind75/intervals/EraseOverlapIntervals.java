import java.util.*;

/* 
https://leetcode.com/problems/non-overlapping-intervals/solution/
Sort the intervals basing on starttime
we have pointer prev to keep track of preceeding interval
3 Cases
    case 1: when end of prev is less than start of curr -> no overlapp , no increment
    case 2: when end of prev is greater than end of curr -> remove prev element, because best to remove interval with more gap , 
                                                            so that other intervals may not overlap
            eg: [1,5],[2,3],[3,4] 
                removing [1,5] will make both [2,3] and [3,4] overlap free
    case 3: when end pf prev is less than end of curr -> remove the curr element, because of it best in every case of future interval
            case 3a: [1,5],[2,9],[3,10] 
                    removing [1,5] or [2,9] will have sam effect , i.e [3,10] is still overlapping
            case 3b: [1,5],[2,9],[6,8]
                    removing [2,9] will benefit [6,8] , as if will overlap free
            case 3c: [1,5],[2,9],[3,6]
                    removing [1,5]/[2,9] will have same effect on [3,6] remains overlapped with on or other
            So best way is to remove [2,9] that is curr interval 
*/

class EraseOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[0]-b[0];
            }
        });
        int prev=0;
        int count=0;
        int n = intervals.length;
        for(int i=1;i<n;i++) {
            //condition for overlap
            if(intervals[prev][1] > intervals[i][0]) { 
                // case 2 where prev is larger interval
               if(intervals[prev][1] > intervals[i][1])  {
                    prev=i; // increment prev to curr , but we also increment count in next step, that means we remove prev here
               }
               count++; // we do not increment prev to curr if above condt not met, that means, we are removing curr here
            } else {
                prev = i; //no pverlap just increment prev to curr
            }
        }
        return count;
    }
}