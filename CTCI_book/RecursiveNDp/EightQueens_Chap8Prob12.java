import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;
import java.lang.reflect.Array;

public class  EightQueens_Chap8Prob12 {
    public static void main(String[] args) {
        EightQueens_Chap8Prob12 sol = new EightQueens_Chap8Prob12();
        // List<Integer> columns = new ArrayList<Integer>();
        // List<List<Integer>> result = new ArrayList<List<Integer>>();
        // sol.fill(0,columns,result);
        // for(List<Integer> cols:result) {
        //     System.out.println("here");
        //     System.out.println(cols);
        //     sol.print(cols);
        // }
        //  Can avoid backtracking if columns is an array.
        Integer[] columnsArray = new Integer[8];
        List<Integer[]> result2 = new ArrayList<Integer[]>();
        sol.fill(0,columnsArray,result2);
        for(Integer[] cols:result2) {
            System.out.println("here");
            System.out.println(cols);
            sol.print(cols);
        }       
    }

    void print(List<Integer> columns) {
        System.out.println("new pattern");
        int row=0;
        for(int col : columns) {
            for(int i=0;i<col;i++)
                System.out.print("0 ");
            System.out.print("1 ");
            for(int i=col+1;i<8;i++)
                System.out.print("0 ");
            System.out.println();
        }
        System.out.println("*********");
    }

    void print(Integer[] columns) {
        System.out.println("new pattern");
        int row=0;
        for(int col : columns) {
            for(int i=0;i<col;i++)
                System.out.print("0 ");
            System.out.print("1 ");
            for(int i=col+1;i<8;i++)
                System.out.print("0 ");
            System.out.println();
        }
        System.out.println("*********");
    }

    void fill(int row,List<Integer> columns,List<List<Integer>> result) {
        System.out.println("row: "+row);
        if(row == 8)
            result.add(new ArrayList<Integer>(columns));
        else {
            for(int col=0;col<8;col++) {
                if(possible(row, col, columns)) {
                    columns.add(col);
                    fill(row+1,columns,result);
                    columns.remove(row); //backtrack
                }
            }
        }
    }

    boolean possible(int row,int col,List<Integer> columns) {
        for(int row2=0;row2<row;row2++) {
            int col2 = columns.get(row2);
            if(col == col2) //same column
                return false;
            int rowDiff = Math.abs(row2-row);
            int colDiff = Math.abs(col2-col);
            if(rowDiff == colDiff) // length == breadth , forms a square, therefore diagonal
                return false;
        }
        return true;
    }

// No backtracking with array
    void fill(int row,Integer[]columns,List<Integer[]> result) {
        System.out.println("row: "+row);
        if(row == 8)
            result.add(columns.clone());
        else {
            for(int col=0;col<8;col++) {
                if(possible(row, col, columns)) {
                    columns[row]=col;
                    fill(row+1,columns,result);
                    // no backtracking as we replace the array element in next iteration
                }
            }
        }
    }

    boolean possible(int row,int col,Integer[] columns) {
        for(int row2=0;row2<row;row2++) {
            int col2 = columns[row2];
            if(col == col2) //same column
                return false;
            int rowDiff = Math.abs(row2-row);
            int colDiff = Math.abs(col2-col);
            if(rowDiff == colDiff) // length == breadth , forms a square, therefore diagonal
                return false;
        }
        return true;
    }
}
