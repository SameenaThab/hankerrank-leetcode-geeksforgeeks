import java.util.LinkedList;
import java.util.Queue;

// package com.nike.validatorsvc;

public class Interview4 {
/*Given an m x n 2d matrix of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 
Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
* */
    public static void main(String... args){
        String[][] grid1 = {{"1","1","1","1","0"},{"1","1","0","1","0"},{"1","1","0","0","0"},{"0","0","0","0","0"}};
        String[][] grid2 = {{"1","1","0","0","0"},{"1","1","0","0","0"},{"0","0","1","0","0"},{"0","0","0","1","1"}};
        int output = getNumberOfIslands(grid1);
        System.out.println("Result: "+output);
        output = getNumberOfIslands(grid2);
        System.out.println("Result: "+output);
    }

    private static int getNumberOfIslands(String[][] grid1) {
        int count = 0;
        int m = grid1.length;
        int n = grid1[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if("1".equals(grid1[i][j])) {
                    count++;
                    bfs(i,j,grid1);
                }
            }
        }
        return count;
    }

    private static void bfs(int currRow,int currCol, String[][] grid1) {
        int[] rows = new int[]{-1,1,0,0};
        int[] cols = new int[]{0,0,1,-1};
        int m = grid1.length;
        int n = grid1[0].length;
        grid1[currRow][currCol] = "0";
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{currRow,currCol});
        while(!queue.isEmpty()) {
            int[] curr = queue.remove();
            int currR = curr[0];
            int currC = curr[1];
            for(int i=0;i<4;i++) {
                int newR = currR+rows[i];
                int newC = currC+cols[i];
                if((newR>=0 && newR<m && newC>=0 && newC<n) && "1".equals(grid1[newR][newC])) {
                    grid1[newR][newC] = "0";
                    queue.add(new int[]{newR,newC});
                }
            }
        }
    }
}