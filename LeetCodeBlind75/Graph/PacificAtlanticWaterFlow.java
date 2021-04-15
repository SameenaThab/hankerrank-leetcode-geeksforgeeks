
import java.util.*;

class PacificAtlanticWaterFlow {
    
/* 
Since all the elements are postive numbers.
For Every cell reachable to both sides, we mark them with 2
else we mark them with 1
We check a cell reaches to both sides if DFS reached to (row0 or col0) && (row n-1 or col n-1)
At the end we count number of cells with value 2
*/
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int m = matrix.length;
        if(m == 0)
            return result;
        int n = matrix[0].length;
        if(n == 0)
            return result;
        int[][] visited1 = new int[m][n];
        int[][] visited2 = new int[m][n];
        for(int i=0;i<n;i++) {
            bfs(matrix,0,i,visited1);
        }
        for(int i=0;i<m;i++) {
            bfs(matrix,i,0,visited1);
        }
        for(int i=0;i<n;i++) {
            bfs(matrix,m-1,i,visited2);
        }
        for(int i=0;i<m;i++) {
            bfs(matrix,i,n-1,visited2);
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(visited1[i][j]+visited2[i][j] == 2) {
                    result.add(new ArrayList<Integer>(Arrays.asList(i,j)));
                }
            }
        }
        return result;
    }

 
    private void bfs(int[][] matrix, int row, int col,int[][] visited) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{row,col});
        int[] rows = new int[] {1,-1,0,0};
        int[] cols = new int[] {0,0,1,-1};
        int m = matrix.length;
        int n = matrix[0].length;
        visited[row][col]=1;
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currRow = curr[0];
            int currCol = curr[1];
            for(int i=0;i<4;i++) {
                int newRow = currRow+rows[i];
                int newCol = currCol+cols[i];
                if(valid(newRow,newCol,m,n) && visited[newRow][newCol] != 1 && matrix[newRow][newCol] >= matrix[currRow][currCol]) {
                    visited[newRow][newCol]=1;
                    queue.add(new int[]{newRow,newCol});
                }
            }
        }
    }

    private boolean valid(int row, int col, int m,int n) {
        return row>=0&&row<m&&col>=0&&col<n;
    }

    public static void main(String[] args) {
        PacificAtlanticWaterFlow sol = new PacificAtlanticWaterFlow();
        // int[][] matrix = new int[][]{
        //     {1,2,2,3,5},
        //     {3,2,3,4,4},
        //     {2,4,5,3,1},
        //     {6,7,1,4,5},
        //     {5,1,1,2,4}
        // };
        int[][] matrix = new int[][]{
            {1,2,3},
            {8,9,4},
            {7,6,5}
        };
        List<List<Integer>> result = sol.pacificAtlantic(matrix);
        System.out.println("solution");
        for(List<Integer> cell : result) {
            System.out.println(cell);
        }
    }

}