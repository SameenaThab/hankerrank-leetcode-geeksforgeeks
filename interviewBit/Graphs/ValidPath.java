import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
https://www.interviewbit.com/problems/valid-path/
 */

public class ValidPath {
    int[][] grid;
    int r;
    int n;
    List<Integer> centerX;
    List<Integer> centerY;

    public static void main(String[] args) {

        /* 
        A : 41
        B : 67
        C : 5
        D : 0
        E : [ 17, 16, 12, 0, 40 ]
        F : [ 52, 61, 61, 25, 31 ]
        */
        ValidPath sol = new ValidPath();
        List<Integer> centerX = new ArrayList<Integer>(Arrays.asList(3, 3, 0, 11, 8, 11, 14, 8, 4 ));
        // centerX.add(2);
        List<Integer> centerY = new ArrayList<Integer>(Arrays.asList(1, 0, 2, 2, 0, 2, 3, 2, 1 ));
        // centerY.add(3);

        System.out.println("Solution: "+sol.solve(14,3,9,0,centerX,centerY));
    }

    public String solve(int x, int y, int n, int r, List<Integer> centerX, List<Integer> centerY) {
        grid = new int[x+1][y+1];
        this.r=r;
        this.n=n;
        this.centerX=centerX;
        this.centerY=centerY;
        // for(int i=0;i<n;i++) {
        //     int xi= centerX.get(i);
        //     int yi = centerY.get(i);
            // fillCellsWithinCircle(xi,yi,xi,yi,r);
            // if(valid(xi+r,yi))
            //     grid[xi+r][yi]=1;
            //  if(valid(xi-r,yi))
            //     grid[xi-r][yi]=1;  

            //  if(valid(xi,yi+r))
            //     grid[xi][yi+r]=1;   
            //  if(valid(xi,yi-r))
            //     grid[xi][yi-r]=1; 

            //  if(valid(xi+r,yi+r))
            //     grid[xi+r][yi+r]=1;    
            //  if(valid(xi-r,yi-r))
            //     grid[xi-r][yi-r]=1; 

            //  if(valid(xi+r,yi-r))
            //     grid[xi+r][yi-r]=1;  
            //  if(valid(xi-r,yi+r))
            //     grid[xi-r][yi+r]=1;  

        // }

        // for(int[] row : grid) {
        //     System.out.println(Arrays.toString(row));
        // }
        return solve(x,y)?"YES":"NO";
    }

    // void fillCellsWithinCircle(int i,int j,int cx,int cy,int r) {
    //     if(valid(i,j) && insideCircle(i, j, cx, cy, r))   {
    //         grid[i][j] = 1;
    //         fillCellsWithinCircle(i+1,j,cx,cy,r);
    //         fillCellsWithinCircle(i-1,j,cx,cy,r);
    //         fillCellsWithinCircle(i,j+1,cx,cy,r);
    //         fillCellsWithinCircle(i,j-1,cx,cy,r);

    //         fillCellsWithinCircle(i+1,j+1,cx,cy,r);
    //         fillCellsWithinCircle(i-1,j-1,cx,cy,r);
    //         fillCellsWithinCircle(i-1,j+1,cx,cy,r);
    //         fillCellsWithinCircle(i+1,j-1,cx,cy,r);
    //     }          
    // }

    boolean insideCircle(int i,int j,int cx,int cy) {
        // System.out.println("radius: "+Math.abs(cx-i)*Math.abs(cx-i)+Math.abs(cy-j)*Math.abs(cx-j));
        return Math.abs(cx-i)*Math.abs(cx-i)+Math.abs(cy-j)*Math.abs(cy-j) <= r*r;
    }
    
    boolean inAnyCircle(int x,int y) {
        for(int i=0;i<n;i++) {
            int cx= centerX.get(i);
            int cy = centerY.get(i);
            // System.out.println("CEnter:"+cx+","+cy+" point: "+x+","+y);
            if(insideCircle(x, y, cx, cy)) {
                // System.out.println(x+","+y+" in "+cx+","+cy);
                return true;
            }
        }
        return false;
    }

    boolean solve(int x,int y) {
        if(inAnyCircle(x,y))
            return false;
        Queue<Integer[]> queue = new LinkedList<Integer[]>();
        queue.add(new Integer[]{0,0});
        while(!queue.isEmpty()) {
            Integer[] cell = queue.poll();
            int i = cell[0];
            int j = cell[1];
            if(x == i && y == j)
                return true;
            if(valid(i,j) && grid[i][j]==0 && !inAnyCircle(i,j)) {
                grid[i][j] = 1;
                queue.add(new Integer[]{i+1,j});
                queue.add(new Integer[]{i-1,j});
                queue.add(new Integer[]{i,j+1});
                queue.add(new Integer[]{i,j-1});
                queue.add(new Integer[]{i+1,j+1});
                queue.add(new Integer[]{i-1,j-1});
                queue.add(new Integer[]{i+1,j-1});
                queue.add(new Integer[]{i-1,j+1});
            }
        }
        return false;
    }

    public boolean valid(int x,int y) {
        return x>=0 && x<grid.length && y>=0 && y<grid[0].length;
    }

}
