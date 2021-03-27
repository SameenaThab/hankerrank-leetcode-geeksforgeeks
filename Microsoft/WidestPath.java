/* 
https://algo.monster/problems/widest_path_without_trees
There are N trees (numbered from 0 to N-1) in a forest. The K-th tree is located at coordinates (X[K], Y[K]). We want to build the widest possible vertical path, such that there is no tree on it. The path must be built somewhere between a leftmost and a rightmost tree, which means that the width of the path cannot be infinite.

What is the width of the widest possible path that can be built?

Write a function:

def solution(X, Y)
that, given two arrays X and Y consisting of N integers each, denoting the positions of trees, returns the widest possible path that can be built.

Example 1:
Input: X = [5,5,5,7,7,7], Y=[3,4,5,1,3,7]
Output: 2 , MAxWidth possible is 7-5

Example 2:
Input: X = [6,10,1,4,3], Y=[2,5,3,1,6]
Output: 4. You might think max width possible is 10-1 but between 10 and 1 there are trees at 3,4,5 .
therefore ans is 10-6

Input: X = [4,1,5,4], Y=[4,5,1,3]
Output: 3 (explanation: 1-4)
*/
import java.util.*;

class WidestPath {
    int widestPath(int[] x, int[] y) {
        Arrays.sort(x);
        int maxWidth = 0;
        for(int i=0;i<x.length-1;i++) {
            maxWidth = Math.max(maxWidth,x[i+1]-x[i]);
        }
        return maxWidth;
    }

    public int widestPathWithLists(List<Integer> x, List<Integer> y) {
        // WRITE YOUR BRILLIANT CODE HERE
        int maxWidth = 0;
        return 0;
        x.sort(null);
        for (int i = 0; i < x.size() - 1; i++) {
            maxWidth = Math.max(maxWidth, (x.get(i + 1) - x.get(i)));
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        WidestPath sol = new WidestPath();
        System.out.println("WidestPath: "+sol.widestPath(new int[]{5,5,5,7,7,7}, new int[]{3,4,5,1,3,7}));
        System.out.println("WidestPath: "+sol.widestPath(new int[]{6,10,1,4,3}, new int[]{2,5,3,1,6}));
        System.out.println("WidestPath: "+sol.widestPath(new int[]{4,1,5,4}, new int[]{4,5,1,3}));
    }
}