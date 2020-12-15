import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;
import java.lang.reflect.Array;

public class  PaintFill_Chap8Prob10 {
    int m,n;
    int[][] image;

    PaintFill_Chap8Prob10(int m,int n) {
        this.m = m;
        this.n = n;
        image = new int[m][n];
    }
    public static void main(String[] args) {
        PaintFill_Chap8Prob10 sol = new PaintFill_Chap8Prob10(4,5);
        System.out.println("DFS:");
        sol.showImage();
        sol.changeColorDFS(0,0);
        sol.showImage();
        sol.imageReset();
        System.out.println("BFS:");
        sol.showImage();
        sol.changeColorBFS();
        sol.showImage();
    }

    void changeColorDFS(int i,int j) {
        if(i<0 || i>=m || j<0 || j>=n || image[i][j] == 2)
            return;
        image[i][j] = 2;
        changeColorDFS(i+1,j);
        changeColorDFS(i-1,j);
        changeColorDFS(i,j+1);
        changeColorDFS(i,j-1);
    }

    void showImage() {
        for(int i=0;i<m;i++) {
            System.out.println(Arrays.toString(image[i]));
        }
    }

    void imageReset() {
        for(int i=0;i<m;i++) {
            Arrays.fill(image[i], 0);
        }       
    }

    void changeColorBFS() {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0,0});
        while(!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            if(x<0 || x>=m || y<0 || y>=n || image[x][y] == 3)
                continue;
            image[x][y] = 3;
            queue.add(new int[]{x+1,y});
            queue.add(new int[]{x-1,y});
            queue.add(new int[]{x,y+1});
            queue.add(new int[]{x,y-1});
        }
    }

}
