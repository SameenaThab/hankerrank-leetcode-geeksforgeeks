import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* 
matrix is sorted rowwise and columnwise
top of column - holds smallest value of column
bottom of column - holds greatest value of column
left of row - holds smallest value of row
right of row - holds greatest value of row
top of the column is greater than k,then move to the left (vice verse not true eg: 100 and top of column 3 with vlaue 40)
bottom of the column is less than k, then move to the right (vice verse not true)
left of the row is greater than k, then move top (vice verse not true)
right of the row is less the k, then move bottom (vice verse not true)
every step we keep shrinking our matrix 
for k = 35 , we eliminate last two rows
*/

public class  MatrixSearch_Chap10Prob9 {
      
    public static void main(String[] args) {
        int[][] matrix = new int[][] { {5,10,30,40,55},
                                       {35,55,85,95,100},
                                       {45,65,95,100,120},
                                       {80,75,100,120,130}};
        MatrixSearch_Chap10Prob9 sol = new MatrixSearch_Chap10Prob9();        
        System.out.println("Solution array : "+Arrays.toString(sol.search(matrix,35)));
    }

    private int[] search(int[][] matrix, int k) {
        int col = matrix[0].length;
        return binarySearch(matrix,0,col-1,k);
    }

    private int[] binarySearch(int[][] matrix, int lowestRow, int highestCol, int k) {
        int colLength = matrix[0].length;
        int rowLength = matrix.length;
        while(lowestRow<rowLength && colLength>=0) {
            if(matrix[lowestRow][highestCol] == k) {
                return new int[]{lowestRow,highestCol};
            } else if(matrix[lowestRow][highestCol] > k) {
                highestCol--;
            } else{ // here we compare the case, rightest of row is less thank, go bottom
                lowestRow++;
            }
        }
        return null;
    }
  
}
