class MinCost {
    //https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/
    public int minCost(String s, int[] cost) {
        int minCost = 0;
        for(int i=1;i<s.length();i++) {
            if(s.charAt(i-1) == s.charAt(i)) {
                minCost+=Math.min(cost[i],cost[i-1]);
                cost[i]=Math.max(cost[i],cost[i-1]);
            }
        }
        return minCost;
    }
}