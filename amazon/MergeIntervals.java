//https://leetcode.com/explore/interview/card/amazon/79/sorting-and-searching/2993/
class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,new Comparator<int[]>() {
            @Override
            public int compare(int[] a,int[] b) {
                return a[0]-b[0];
            } 
        });
        LinkedList<int[]> result = new LinkedList<int[]>();
        for(int[] interval:intervals) {
            if(result.isEmpty() || result.getLast()[1] < interval[0])
            {
                result.add(interval);
            } else {
                result.getLast()[1] = Math.max(interval[1],result.getLast()[1]);
            }
        }
        
        return result.toArray(new int[result.size()][]);
    }
}