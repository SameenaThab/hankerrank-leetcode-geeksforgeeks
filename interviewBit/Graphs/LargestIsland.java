import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* 
https://www.interviewbit.com/problems/region-in-binarymatrix/
 */

public class LargestIsland {
    
    int[][] grid;
    int n;
    int m;
    ArrayList<ArrayList<Integer>> A;

    public static void main(String[] args) {

        LargestIsland sol = new LargestIsland();
        ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
        A.add(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 0 )));
        A.add(new ArrayList<Integer>(Arrays.asList(1, 0, 1, 1, 0 )));
        A.add(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 0, 0 )));
        A.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1 )));

        System.out.println("Solution: "+sol.solve(A));
    }

    public int solve(ArrayList<ArrayList<Integer>> A) {
        int max =0;
        n = A.size();
        m = A.get(0).size();
        this.A=A;
        
        Set<String> visited = new HashSet<String>();
        // grid = new int[n][m];
        //  for(int i=0;i<n;i++) {
        //     for(int j=0;j<m;j++) {
        //         grid[i][j] = A.get(i).get(j);
        //     }
        // }
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(A.get(i).get(j) == 1 && !visited.contains(i+"-"+j)) {
                    max=Math.max(max,findLengthBFS(i,j,visited));
                }
            }
        }
        return max;
    }
    
    public int findLengthBFS(int x,int y,Set<String> visited ){
        int length = 0;
        Queue<Integer[]> queue = new LinkedList<Integer[]>();
        queue.add(new Integer[] {x,y});
        while(!queue.isEmpty()) {
            Integer[] cell = queue.poll();
            int i = cell[0];
            int j = cell[1];
            if(valid(i,j) && !visited.contains(i+"-"+j)) {
                length++;
                visited.add(i+"-"+j);
                queue.add(new Integer[] {i+1,j});
                queue.add(new Integer[] {i-1,j});
                queue.add(new Integer[] {i,j+1});
                queue.add(new Integer[] {i,j-1});

                queue.add(new Integer[] {i+1,j+1});
                queue.add(new Integer[] {i-1,j-1});
                queue.add(new Integer[] {i+1,j-1});
                queue.add(new Integer[] {i-1,j+1});
            }
        }
        return length;
    }

    public boolean valid(int x,int y) {
        return x>=0 && x<n&& y>=0 && y<m && A.get(x).get(y)==1;
    }

}
