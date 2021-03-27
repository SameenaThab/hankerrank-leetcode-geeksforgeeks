/* 
https://algo.monster/problems/unique_integers_that_sum_up_to_0
Given an integer n, return any array containing n unique integers such that they add up to 0.

Example 1:
Input:5
Output: [-4,-2,0,2,4]
Example 2:
Input:3
Output: [-2, 0, 2]
Example 1:
Input:1
Output: [0]
*/
class ArrSumsToZero {
    int[] arrSumsToZero(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; ++i) {
            v[i] = i * 2 - n + 1;
        }
        return v;
    }
}