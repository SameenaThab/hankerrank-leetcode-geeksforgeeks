
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/knight-on-chess-board/


*/

public class KnightChessBoard {

    int n,m;

    public static void main(String[] args) {
        KnightChessBoard sol = new KnightChessBoard();    
        System.out.println("Solution: "+sol.knight(8, 8, 1, 1, 8, 8));   
    }

    public int knight(int n, int m, int srcX, int srcY, int desX, int desY) {

        this.n=n;
        this.m=m;
        return bfs(srcX-1,srcY-1,desX-1,desY-1);
    }

    int  bfs(int srcX, int srcY, int desX, int desY) {
        boolean[][] visited = new boolean[n][m];
        if(!valid(srcX,srcY))
            return 0;
        int count = 0;
        int[] rows = new int[]{1,-1,1,-1,2,-2,2,-2};
        int[] cols = new int[]{2,2,-2,-2,1,1,-1,-1};
        Queue<Integer[]> queue = new LinkedList<Integer[]>();
        queue.add(new Integer[]{srcX,srcY});
        visited[srcX][srcY] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            // this way I will distinguish each level 
            for(int i=0;i<size;i++) {
                Integer[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];
                
                if(x == desX && y == desY)
                    return count;
                for(int u=0;u<8;u++) {
                    int newX = x+rows[u];
                    int newY = y+cols[u];
                    if(valid(newX,newY) && !visited[newX][newY]) {
                        queue.add(new Integer[]{newX,newY});
                        // we mark the visited before itself so that next neighbors do not add it again
                        visited[newX][newY]=true;
                    }
                }
            }
            count++;
        }
        return -1;
    }

    boolean valid(int i,int j) {
        return i>=0 && i<n && j>=0 && j<m;
    }
}
