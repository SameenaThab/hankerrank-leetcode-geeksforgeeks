class MeetingRoomsTwoSortArrays {
    public int minMeetingRooms(int[][] intervals) {
        int n=intervals.length;
        if(n<=0)
            return 0;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for(int i=0;i<n;i++){
            starts[i]=intervals[i][0];
            ends[i]=intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int count=0;
        int sp=0,ep=0;
        for(int i=0;i<n;i++){
            //something ended
            if(starts[sp] >= ends[ep]) {
                ep++;
            }
            else {
                //nothing ended , need new meeting room
                count++;
            }
            sp++;                
        }
        return count;
    }
}