// https://leetcode.com/discuss/interview-question/891654/Postmates-or-OA-or-Almost-Tetris

import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// 1. *
// 2. * * *
// 3. * *
//    * *
// 4. *
//    * *
//    *
// 5.   *
//    * * *

public class AlmostTetris {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter m");     
        int m = scanner.nextInt();
        System.out.println("Enter n");  
        int n = scanner.nextInt();
        System.out.println("Number of figures");
        int q = scanner.nextInt();
        int[] queries = new int[q]; 
        System.out.println("Enter figures");
        for(int i=0;i<q;i++) {
            queries[i] = scanner.nextInt();
        }
        int[][] matrix = new int[m][n];
        for(int i=0;i<m;i++)
            Arrays.fill(matrix[i],0);
        almostTetris(matrix,queries,m,n);
        for(int i=0;i<m;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void almostTetris(int[][] matrix, int[] queries,int m,int n) {
        for(int k=0;k<queries.length;k++) {
            boolean stop = false;
            for(int i=0;i<m && !stop;i++) {
                for(int j=0;j<n && !stop;j++) {
                    if(isValid(matrix,i,j,queries[k])) {
                        fill(matrix,i,j,queries[k],k+1);
                        stop = true;
                    }                    
                }
            }
        }
    }

    private static void fill(int[][] matrix, int i,int j,int q,int k) {
        if(q == 1) {
            matrix[i][j]=k;
        } else if(q == 2) {
            matrix[i][j]=k; matrix[i][j+1]=k; matrix[i][j+2]=k;
        } else if(q == 3) {
            matrix[i][j]=k; matrix[i][j+1]=k;
            matrix[i+1][j]=k; matrix[i+1][j+1]=k;
        } else if(q == 4) {
            matrix[i][j]=k;
            matrix[i+1][j]=k; matrix[i+1][j+1]=k;
            matrix[i+2][j]=k;         
        } else {
            matrix[i][j]=k;
            matrix[i+1][j-1]=k; matrix[i+1][j]=k; matrix[i+1][j+1]=k;              
        }        
    }

    private static boolean isValid(int[][] matrix, int i,int j,int q) {
        if(q == 1) {
            return isValid(matrix, i, j);
        } else if(q == 2) {
            return isValid(matrix, i, j) && isValid(matrix, i, j+1) && isValid(matrix, i, j+2);
        } else if(q == 3) {
            return isValid(matrix, i, j) && isValid(matrix, i, j+1)
             && isValid(matrix, i+1, j) && isValid(matrix, i+1, j+1);
        } else if(q == 4) {
            return isValid(matrix, i, j)
            && isValid(matrix, i+1, j) && isValid(matrix, i+1, j+1)
            && isValid(matrix, i+2, j);           
        } else {
            return isValid(matrix, i, j)
            && isValid(matrix, i+1, j-1) && isValid(matrix, i+1, j) && isValid(matrix, i+1, j+1);              
        }
    }

    private static boolean isValid(int[][] matrix, int i,int j) {
        int m = matrix.length;
        int n = matrix.length;
        return i>=0 && i<m && j>=0 && j<n && matrix[i][j] == 0;
    }

}