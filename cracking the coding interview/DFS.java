/*

https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem

Given an nXm matrix, find and print the number of cells in the largest region in the matrix. Note that there may be more than one region in the matrix.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DFS {
    
    public static int getBiggestRegion(int[][] matrix) {
        ArrayList<String> al=new ArrayList<String>();
        int m=matrix.length;
        int n=matrix[0].length;
        int count=0;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                count=Math.max(count,dfs(matrix,i,j,al));
        return count;     
    }
    
    public static int dfs(int[][] matrix,int i,int j,ArrayList<String> al)
    {
        int m=matrix.length;
        int n=matrix[0].length;
        if(i<0 || j<0 ||i>=m ||j>=n)
            return 0;
        int count=0;
        if(!al.contains(Integer.toString(i)+"-"+Integer.toString(j)))
            al.add(Integer.toString(i)+"-"+Integer.toString(j));
        else
            return 0;
        if(matrix[i][j]==0)
            return 0;
        else
           count=1+dfs(matrix,i-1,j-1,al)
            +dfs(matrix,i+1,j+1,al)
            +dfs(matrix,i-1,j+1,al)
            +dfs(matrix,i+1,j-1,al)
            +dfs(matrix,i-1,j,al)
            +dfs(matrix,i+1,j,al)
            +dfs(matrix,i,j+1,al)
            +dfs(matrix,i,j-1,al);
        return count;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        System.out.println(getBiggestRegion(grid));
    }
}
