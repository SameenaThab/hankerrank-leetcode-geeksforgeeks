
/* 
https://algo.monster/problems/min_adj_swaps_to_group_red_balls
There are N balls positioned in a row. Each of them is either red or white . In one move we can swap two adjacent balls. We want to arrange all the red balls into a consistent segment. What is the minimum number of swaps needed?

Given string S of length N built from characters "R" and "W", representing red and white balls respectively, returns the minimum number of swaps needed to arrange all the red balls into a consistent segment. If the result exceeds 10^9, return -1.

Example 1:
Input:WRRWWR
Output: 2
Explanation:
We can move the last ball two positions to the left:

swap R and W -> WRRWRW
swap R and W -> WRRRWW
Example 2:
Input:WWRWWWRWR
Output: 4
Explanation:
We can move the last ball two positions to the left:

swap R and W -> WWRWWWRRW
swap R and W -> WWWRWWRRW
swap R and W -> WWWWRWRRW
swap R and W -> WWWWWRRRW
Example 3:
Input:WR
Output: -1
Explanation:
The minimum needed number of swaps is greater than 10^9. So return -1.

Example 4:
Input:WWW
Output: 0
Explanation:
There are no red balls to arrange into a segment.
*/
class MinSwapRedBalls {
    int minSwapRedBalls(String str) {
        int lastRed =  0;
        int firstRed = -1;
        int redCount = 0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i) == 'R') {
                redCount++;
                lastRed=i;
                if(firstRed==-1) {
                    firstRed = i;
                }
            }
        }
        if(firstRed == -1)
            return 0;
        return (firstRed == lastRed)? -1 : lastRed-firstRed-redCount+1;
    }

    public static void main(String[] args) {
        MinSwapRedBalls sol = new MinSwapRedBalls();
        System.out.println("Should be 2: "+sol.minSwapRedBalls("WRRWWR"));
        System.out.println("Should be 4: "+sol.minSwapRedBalls("WWRWWWRWR"));
        System.out.println("Should be -1: "+sol.minSwapRedBalls("WR"));
        System.out.println("Should be 0: "+sol.minSwapRedBalls("WWW"));
    }
}