import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;



public class MinCostPath {

    ArrayList<String> matrix;
    int m;
    int n;

    /* https://www.interviewbit.com/problems/min-cost-path/
     */

    public static void main(String[] args) {
        MinCostPath sol = new MinCostPath();
        /* 
            A = 3
            B = 3
            C = ["RRR","DDD","UUU"]
            A = 1
            B = 4
            C = ["LLLL"]
            A : 5
            B : 5
            C : [ "RRRRD", "DLLLL", "RRRRD", "DLLLL", "RRRRR" ]
         */
        // ArrayList<String> matrix = new ArrayList<String>(Arrays.asList("RRR","DDD","UUU"));
        // System.out.println(sol.solve(3,3,matrix));
        // ArrayList<String> matrix = new ArrayList<String>(Arrays.asList("LLLL"));
        // System.out.println(sol.solve(1,4,matrix));
        // ArrayList<String> matrix = new ArrayList<String>(Arrays.asList("R"));
        // System.out.println(sol.solve(1,1,matrix));
        ArrayList<String> matrix = new ArrayList<String>(Arrays.asList("RRRRD", "DLLLL", "RRRRD", "DLLLL", "RRRRR"));
        System.out.println(sol.solve(5,5,matrix));
    }

    class Cell {
        int row;
        int col;
        Cell(int row,int col) {
            this.row=row;
            this.col=col;
        }

        boolean equals(int row,int col) {
            return this.row == row && this.col == col;
        }
    }

    public int solve(int m, int n, ArrayList<String> matrix) {
        this.m=m;
        this.n=n;
        this.matrix=matrix;
        Queue<Cell> queue = new LinkedList<Cell>();
        int[][] dist = new int[m][n];
        for(int i=0;i<m;i++) {
            Arrays.fill(dist[i],-1);
        }
        queue.add(new Cell(0,0));
        dist[0][0] = 0;
        for(int i=0;i<m;i++) {
            System.out.println(Arrays.toString(dist[i]));
        }       
        int minPath = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Cell cell = queue.poll();
            int r = cell.row;
            int c = cell.col;
            int currDist = dist[r][c];
            System.out.println("r: "+r+" c: "+c+" dist: "+currDist);
            if(r==m-1 && c==n-1) {
                minPath = Math.min(minPath, currDist);
            }
            Cell dir = getDirection(cell);
            if(valid(r,c+1)) {
                // System.out.println("here");
                int path=dir.equals(r,c+1)?currDist:currDist+1;
                if(dist[r][c+1] == -1 || dist[r][c+1] > path) {
                    dist[r][c+1] = path;
                    queue.add(new Cell(r,c+1));
                }

            }
            if(valid(r,c-1)) {
                int path=dir.equals(r,c-1)?currDist:currDist+1;
                if(dist[r][c-1] == -1 || dist[r][c-1] > path){
                    dist[r][c-1] = path;
                    queue.add(new Cell(r,c-1));
                }
            }
            if(valid(r+1,c)) {
                int path=dir.equals(r+1,c)?currDist:currDist+1;
                if(dist[r+1][c] == -1 || dist[r+1][c] > path) {
                    dist[r+1][c] = path;
                    queue.add(new Cell(r+1,c));
                }
            }
            if(valid(r-1,c)) {
                int path=dir.equals(r-1,c)?currDist:currDist+1;
                if(dist[r-1][c] == -1 || dist[r-1][c] > path) {
                    dist[r-1][c] = path;
                    queue.add(new Cell(r-1,c));
                }
            }
        }
        return minPath;
    }

    Cell getDirection(Cell cell) {
        int r = cell.row;
        int c = cell.col;
        char direction = matrix.get(r).charAt(c);  
        // System.out.println("r: "+r+" c: "+c+" direction: "+direction);
        if(direction == 'R') {
            return new Cell(r,c+1);
        } else if(direction == 'L') {
            return new Cell(r,c-1);
        } else if(direction == 'U') {
            return new Cell(r-1,c);
        } else {
            return new Cell(r+1,c);
        }
      
    }

    boolean valid(int r,int c) {
        // System.out.println("valid: "+(r>=0 && r<m && c>=0 && c<n));
        return (r>=0 && r<m && c>=0 && c<n);
    }
}