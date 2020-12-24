
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/capture-regions-on-board/ */

public class CaptureRegions {
    int n,m;
    ArrayList<ArrayList<Character>> matrix;

    /*
      A = [  [X, X, X, X],
        [X, X, X, X],
        [X, X, X, X],
        [X, O, X, X]
     ]
     
     A : [ "XOX XXX OOX X", "XOOOOXOOXX", "OXXOOXXXOO", "OXOXOOOXXO", "OXOOXXOOXX", "OXXXOXXOXO", "OOXXXXOXOO" ]
     */

    public static void main(String[] args) {
        CaptureRegions sol = new CaptureRegions();     
        ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();   
        // matrix.add(new ArrayList<Character>(Arrays.asList('X', 'X', 'X', 'X')));
        // matrix.add(new ArrayList<Character>(Arrays.asList('X', 'O', 'O', 'X')));
        // matrix.add(new ArrayList<Character>(Arrays.asList('X', 'O', 'O', 'X')));
        // matrix.add(new ArrayList<Character>(Arrays.asList('X', 'X', 'X', 'X')));
        matrix.add(new ArrayList<Character>(Arrays.asList('X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'X')));
        matrix.add(new ArrayList<Character>(Arrays.asList('X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'X')));
        matrix.add(new ArrayList<Character>(Arrays.asList('O', 'X', 'X', 'O', 'O', 'X', 'X', 'X', 'O', 'O')));
        matrix.add(new ArrayList<Character>(Arrays.asList('O', 'X', 'O', 'X', 'O', 'O', 'O', 'X', 'X', 'O')));
        matrix.add(new ArrayList<Character>(Arrays.asList('O', 'X', 'O', 'O', 'X', 'X', 'O', 'O', 'X', 'X')));
        matrix.add(new ArrayList<Character>(Arrays.asList('O', 'X', 'X', 'X', 'O', 'X', 'X', 'O', 'X', 'O')));
        matrix.add(new ArrayList<Character>(Arrays.asList('O', 'O', 'X', 'X', 'X', 'X', 'O', 'X', 'O', 'O')));
        System.out.println("Before Solution: ");
        for(ArrayList<Character> row: matrix) {
            System.out.println(row);
        }
        sol.solve(matrix);
        System.out.println("Solution: ");
        for(ArrayList<Character> row: matrix) {
            System.out.println(row);
        }
    }

    public void solve(ArrayList<ArrayList<Character>> matrix) {
        this.matrix = matrix;
        this.n=matrix.size();
        this.m=matrix.get(0).size();
        boolean[][] visited = new boolean[n][m];
        //BFS only boundaries
        for(int col=0;col<m;col++) {
            if(matrix.get(0).get(col) == 'O') {
                bfs(0,col);
            }
            if(matrix.get(n-1).get(col) == 'O') {
                bfs(n-1,col);
            }
        }
        for(int row=0;row<n;row++) {
            if(matrix.get(row).get(0) == 'O') {
                bfs(row,0);
            }
            if(matrix.get(row).get(m-1) == 'O') {
                bfs(row,m-1);
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix.get(i).get(j)=='B')
                    matrix.get(i).set(j,'O');
                else if(matrix.get(i).get(j)=='O')
                    matrix.get(i).set(j,'X');
            }
         }

    }

    public void bfs(int i, int j) {
        int[] rows = new int[]{1,-1,0,0};
        int[] cols = new int[]{0,0,1,-1};
        Queue<Integer[]> queue = new LinkedList<Integer[]>();
        queue.add(new Integer[]{i,j});
        while(!queue.isEmpty()) {
            Integer[] cell = queue.poll();
            int currRow = cell[0];
            int currCol = cell[1];
            matrix.get(currRow).set(currCol,'B');
            for(int u=0;u<rows.length;u++) {  
                int newRow = currRow+rows[u];
                int newCol = currCol+cols[u];
                if(!invalid(newRow,newCol) && matrix.get(newRow).get(newCol) == 'O') {
                    queue.add(new Integer[]{newRow,newCol});
                }
            }          
        }
    }

    /* DFS doesnot work because we cannot predict parent before child So i */

    // public boolean isSurroundedDFS(int i,int j,boolean[][] visited,int pi,int pj) {
    //     visited[i][j] = true;
    //     if(matrix.get(i).get(j) == 'X')
    //         return true;
    //     int[] rows = new int[]{1,-1,0,0};
    //     int[] cols = new int[]{0,0,1,-1};
    //     for(int u=0;u<rows.length;u++) {
    //         int newI = i+rows[u];
    //         int newJ = j+cols[u]; 
    //         if(invalid(newI,newJ))
    //             return false;
    //         if(!visited[newI][newJ] && !isSurroundedDFS(newI,newJ,visited,i,j)) {
    //             return false;
    //         }
    //         if(visited[newI][newJ] && pi!=newI && pj!=newJ && matrix.get(newI).get(newJ) == 'O') {
    //             return false;
    //         }
    //     }
    //     matrix.get(i).set(j,'X');
    //     return true;
    // }

    public boolean invalid(int i,int j) {
        return i<0 || i>=n || j<0 || j>=m;
    }

}
