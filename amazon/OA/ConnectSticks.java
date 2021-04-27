
//https://leetcode.com/problems/minimum-cost-to-connect-sticks/solution/
class ConnectSticks {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int result = 0;
        for(int stick:sticks)
            pq.add(stick);
        while(pq.size()>1) {
            int sum = pq.poll()+pq.poll();
            result+=sum;
            pq.add(sum);
        }
        return result;
    }
}