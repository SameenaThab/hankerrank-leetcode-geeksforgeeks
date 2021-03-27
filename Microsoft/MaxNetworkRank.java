/*
 https://algo.monster/problems/max_network_rank
 An infrastructure consisting of N cities from l to N, and M bidirectional roads between them are given. Roads do not intersect apart from at their start and endpoints (they can pass through underground tunnels to avoid collisions).

For each pair of cities directly connected by a road, let’s define their network rank as the total number of roads that are connected to either of the two cities.

Write a function, given two arrays A, B consisting of M integers each and an integer N, where A[i] and B[i] are cities at the two ends of the i-th road, returns the maximal network rank in the whole infrastructure.
Example:
Input:
A = [1, 2, 3, 3]
B = [2, 3, 1, 4]
N = 4
Output:
4
Explanation:
The chosen cities may be 2 and 3, and the four roads connected to them are: (2,1), (2,3), (3,1), (3,4).
*/
class MaxNetworkRank {

    public int maxNetworkRank(int[] a,int[] b, int n) {
        // stores no of roads for each city
        int[] edgeCount = new int[n];
        int roads = a.length;
        for(int i=0;i<roads;i++) {
            edgeCount[a[i]-1]++; // we do -1 bcoz cities are 1-base index
            edgeCount[b[i]-1]++;
        }
        int maxRank = Integer.MIN_VALUE;
        for(int i=0;i<roads;i++) {
            int rank = edgeCount[a[i]-1]+edgeCount[b[i]-1]-1; // we substract 1 bcoz we dont want to count road between a[i] and b[i] twice
            maxRank = Math.max(rank, maxRank);
        }
        return maxRank;
    }

    public static void main(String[] args) {
        MaxNetworkRank sol = new MaxNetworkRank();
        System.out.println("MAxNetworkRank: "+sol.maxNetworkRank(new int[]{1,2,3,3}, new int[]{2,3,1,4}, 4));
    }
    
}