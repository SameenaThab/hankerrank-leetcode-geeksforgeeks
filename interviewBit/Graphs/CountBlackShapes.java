import java.util.*;
import java.text.*;
import java.math.*;
import java.security.KeyStore.Entry;
import java.util.regex.*;
import java.io.*;

public class CountBlackShapes {

    int m,n;
    ArrayList<String> matrix;

    public static void main(String[] args) {
        CountBlackShapes sol = new CountBlackShapes();
        /* 
        A = [
            OOOXOOO
            OOXXOXO
            OXOOOXO  ]
        */
        
        ArrayList<String> matrix = new ArrayList<String>(Arrays.asList("OOOXOOO","OOXXOXO","OXOOOXO"));
        System.out.println("Solution: "+sol.black(matrix));
    }

    public int black(ArrayList<String> matrix) {
        if(matrix.size() == 0)
            return 0;
        this.m=matrix.size();
        this.n = matrix.get(0).length();
        this.matrix = matrix;
        int count = 0;
        int[][] visited = new int[m][n];
        for(int i=0;i<matrix.size();i++) {
            for(int j=0;j<matrix.get(0).length();j++) {
                String st = matrix.get(i);
                if(st.charAt(j) == 'X') {
                    if(visited[i][j] == 0) {
                        // System.out.println("i: "+i+"j: "+j);

                        dfs(visited,i,j);
                        // System.out.println("visited after dfs");
                        // for(int[] row:visited) {
                        //     System.out.println(Arrays.toString(row));
                        // }
                        count++;
                    }
                }
            }
        }
        return count;
    }

    void dfs(int[][] visited,int i,int j) {
        visited[i][j]=1;
        // System.out.println("i: "+i+"j: "+j);
        int[] rowInc = new int[]{-1,0,1,0};
        int[] colInc = new int[]{0,-1,0,1};
        for(int k=0;k<4;k++) {
            int x = i+rowInc[k];
            int y = j+colInc[k];
            // System.out.println("x: "+x+"y: "+y);
            // System.out.println(valid(x,y) && matrix.get(x).charAt(y) == 'X');
            if(valid(x,y) && matrix.get(x).charAt(y) == 'X' && visited[x][y] == 0) {
                dfs(visited,x,y);
            }
        }
    }

    boolean valid(int x,int y){
        return x>=0 && x<m && y>=0 && y<n;
    }
      
}