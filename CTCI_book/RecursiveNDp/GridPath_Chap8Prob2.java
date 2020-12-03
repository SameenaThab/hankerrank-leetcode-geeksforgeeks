import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class  GridPath_Chap8Prob2 {
    /*
    1 1 1 1 1 1
    1 0 0 1 0 1
    1 1 1 1 0 0
    1 0 0 1 1 1    
    */
    int[][] matrix;
    int m,n;
        
    public static void main(String[] args) {
        GridPath_Chap8Prob2 sol = new GridPath_Chap8Prob2();
        int[][] matrix = {
            {1,1,1,1,1,1},
            {1,0,0,1,0,1},
            {1,1,1,1,0,0},
            {1,0,0,1,1,1}
        };
        sol.matrix = matrix;
        sol.m=4;
        sol.n=6;
        LinkedList<Integer[]> path = new LinkedList<Integer[]>();
        System.out.println("backTracking");
        if(sol.findPathBackTrack(0,0,path)){
            for(Integer[] pair:path) {
                System.out.println(pair[0]+" "+pair[1]);
            }
        } else {
            System.out.println("No Path possible"); 
        }

        List<Integer[]> path2 = new ArrayList<Integer[]>();
        Set<String> failedPoints = new HashSet<String>();
        System.out.println("Memorization");
        // calling from end
        if(sol.findPathUsingMem(3,5,path2,failedPoints)) {
            for(Integer[] pair:path2) {
                System.out.println(pair[0]+" "+pair[1]);
            }
        } else {
            System.out.println("No Path possible");
        }
    }

    public boolean findPathUsingMem(int i,int j,List<Integer[]> path,Set<String> failedPoints) {
        if(isInvalid(i,j) || failedPoints.contains(i+"-"+j))
            return false;
    
        if((i == 0 && j== 0) || findPathUsingMem(i,j-1,path,failedPoints) || findPathUsingMem(i-1,j,path,failedPoints)) {
            Integer[] pair = {i,j};
            path.add(pair);
            return true;
        }

        failedPoints.add(i+"-"+j);
        return false;        
    }

    public boolean findPathBackTrack(int i,int j,LinkedList<Integer[]> path) {
        if(isInvalid(i,j)){
            return false;
        }
        Integer[] pair = {i,j};

        //add first
        path.add(pair);
        matrix[i][j]=0;

        if((i == m-1 && j== n-1) || findPathBackTrack(i+1,j,path) || findPathBackTrack(i,j+1,path)) {
            matrix[i][j]=1;
            return true;
        }

        //backTrack when Not worked
        path.removeLast();
        matrix[i][j]=1;

        return false;
    }

    public boolean isInvalid(int i,int j) {
        return i<0||i>=m||j<0||j>=n||matrix[i][j]==0;
    }
}
