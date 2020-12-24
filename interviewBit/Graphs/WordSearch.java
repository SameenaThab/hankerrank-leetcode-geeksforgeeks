
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/word-search-board/ */

public class WordSearch {
    int n,m;
    ArrayList<String> board;    
    /*
      ["ABCE"],
        ["SFCS"],
        ["ADEE"]
     */

    public static void main(String[] args) {
        WordSearch sol = new WordSearch();     
        ArrayList<String> board = new ArrayList<String>(Arrays.asList("ABCE","SFCS","ADEE"));   
        for(String row: board) {
            System.out.println(row);
        }
        System.out.println("Word: ABCCED Solution : "+sol.exist(board,"ABCCED"));
        System.out.println("Word: SEE Solution : "+sol.exist(board,"SEE"));
        System.out.println("Word: ABCB Solution : "+sol.exist(board,"ABCB"));
        System.out.println("Word: ABFSAB Solution : "+sol.exist(board,"ABFSAB"));
        System.out.println("Word: ABCD Solution : "+sol.exist(board,"ABCD"));
    }

    public int exist(ArrayList<String> board,String word) {
        this.board = board;
        this.n=board.size();
        this.m=board.get(0).length();
        boolean[][] visited = new boolean[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(hasWordDFS(i,j,word,0))
                    return 1;
            }
        }
        return 0;
    }

    public boolean hasWordDFS(int row,int col,String word,int index) {
        // System.out.println("word: "+word+" index: "+index+" row: "+row+" col: "+col);
        if(index == word.length()) {
            // System.out.println("base case true");
            return true;
        }
        if(board.get(row).charAt(col) != word.charAt(index)) {
            // System.out.println("base case unmatch");
            return false;
        }
        int[] rowArr = new int[]{1,-1,0,0};
        int[] colArr = new int[]{0,0,1,-1};
        for(int u=0;u<4;u++) {
            int newRow = row+rowArr[u];
            int newCol = col+colArr[u];
            if(valid(newRow,newCol) && hasWordDFS(newRow, newCol, word, index+1)) {
                // System.out.println("found match");
                return true;
            }
        }
        // System.out.println("Exhausted");
        return false;
    }

    public boolean valid(int i,int j) {
        return i>=0 && i<n && j>=0 && j<m;
    }

}
