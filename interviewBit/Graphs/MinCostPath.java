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
        // System.out.println("dfs solution: "+sol.solveDFS(5,5,matrix));
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
                    queue.add(new Cell(r,c+1));  // updating only when new distance is smaller than recorded before
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

// DFS work in progress
    // int solveDFS(int m, int n, ArrayList<String> matrix) {
    //     this.m=m;
    //     this.n=n;
    //     this.matrix=matrix;
    //     int[][] dist = new int[m][n];
    //     for(int i=0;i<m;i++) {
    //         Arrays.fill(dist[i],-1);
    //     }
    //     dfs(0,0,matrix,dist,0);
    //     return dist[m-1][n-1];
    // }

    // boolean dfs(int i,int j, ArrayList<String> matrix,int[][] dist,int count) {
    //     if(i==m-1 && j==n-1) {
    //         dist[i][j] = count;
    //         return true;
    //     }
    //     int currDist = dist[i][j];
    //     char direction = matrix.get(i).charAt(j);
    //     int nexti;
    //     int nextj;
    //     if(direction == 'R') {
    //         nexti=i;
    //         nextj=j+1;
    //     } else if(direction == 'L') {
    //         nexti=i;
    //         nextj=j-1;
    //     } else if(direction == 'U') {
    //         nexti=i-1;
    //         nextj=j;
    //     } else {
    //         nexti=i+1;
    //         nextj=j;            
    //     }
    //     if(valid(i,j+1)) {
    //         // System.out.println("here");
    //         int path=(nexti==i && nextj==j+1)?count:count+1;
    //         if(dist[i][j+1] == -1 || dist[i][j+1] > path) {
    //             dist[i][j+1] = path;
    //             dfs(i,j+1,matrix,dist,path); // updating only when new distance is smaller than recorded before
    //         }

    //     }
    //     if(valid(i,j-1)) {
    //         int path=(nexti==i && nextj==j-1)?count:count+1;
    //         if(dist[i][j-1] == -1 || dist[i][j-1] > path) {
    //             dist[i][j-1] = path;
    //             dfs(i,j-1,matrix,dist,path); // updating only when new distance is smaller than recorded before
    //         }
    //     }
    //     if(valid(i+1,j)) {
    //         int path=(nexti==i+1 && nextj==j)?currDist:currDist+1;
    //         if(dist[i+1][j] == -1 || dist[i+1][j] > path) {
    //             dist[i+1][j] = path;
    //             dfs(i+1,j,matrix,dist,path); // updating only when new distance is smaller than recorded before
    //         }
    //     }
    //     if(valid(i-1,j)) {
    //         int path=(nexti==i-1 && nextj==j)?currDist:currDist+1;
    //         if(dist[i-1][j] == -1 || dist[i-1][j] > path) {
    //             dist[i-1][j] = path;
    //             dfs(i-1,j,matrix,dist,path); // updating only when new distance is smaller than recorded before
    //         }
    //     }
    }
}