import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PacificAtlanticWaterFlow {
    
/* 
Since all the elements are postive numbers.
For Every cell reachable to both sides, we mark them with -2
else we mark them with -1
We check a cell reaches to both sides if DFS reached to (row0 or col0) && (row n-1 or col n-1)
At the end we count number of cells with value -2
*/
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] visited = new int[m][n];
        // for(int[] row:visited) {
        //     Arrays.fill(row,-1);
        // }
        Set<String> reachable = new HashSet<String>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(visited[i][j] == 0)
                    dfsPacificToAtlantic(i,j,m,n,matrix,visited,reachable);
            }
        }
        for(String cell : reachable) {
            System.out.println(cell);
        }
        System.out.println("visited");
        for(int[] row : visited) {
            System.out.println(Arrays.toString(row));
        }
        for(String str : reachable) {
            int i = Integer.parseInt(str.split(",")[0]);
            int j = Integer.parseInt(str.split(",")[1]);
            System.out.println("visited");
            for(int[] row : visited) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("In cell i: "+i+" j: "+j);
            if(visited[i][j] == 1 && dfsAtlanticToPacific(i,j,m,n,matrix,visited,result,reachable)) {
                result.add(new ArrayList<Integer>(Arrays.asList(i,j)));
            }            
        }
        return result;
    }

    private boolean dfsAtlanticToPacific(int i, int j, int m, int n, int[][] matrix, int[][] visited,
            List<List<Integer>> result, Set<String> reachable) {
        System.out.println("i: "+i+" j: "+j);
        if(i == 0 || j == 0) {
            return true;
        }

        visited[i][j]=0;
        int[] rows = new int[]{-1,0};
        int[] cols = new int[]{0,-1};
        for(int k=0;k<2;k++) {
            int newRow = i+rows[k];
            int newCol = j+cols[k];
            if(valid(newRow,newCol,m,n) && visited[newRow][newCol] == 1 && matrix[newRow][newCol] <= matrix[i][j] && dfsAtlanticToPacific(newRow,newCol,m,n,matrix,visited,result,reachable)) {                
                visited[i][j]=1;
                return true;
            } 
        }
        visited[i][j]=1;
        return false;
    }

    private boolean dfsPacificToAtlantic(int i, int j, int m, int n, int[][] matrix, int[][] visited, Set<String> reachable) {
        visited[i][j]=1;
        if(i==m-1 || j == n-1) {
            reachable.add(i+","+j);
            return true;
        }
        int[] rows = new int[]{1,0};
        int[] cols = new int[]{0,1};
        for(int k=0;k<2;k++) {
            int newRow = i+rows[k];
            int newCol = j+cols[k];
            if(valid(newRow,newCol,m,n) && visited[newRow][newCol] == 0 && matrix[newRow][newCol] <= matrix[i][j] && dfsPacificToAtlantic(newRow,newCol,m,n,matrix,visited,reachable)) {
                reachable.add(i+","+j);
                return true;
            } 
        }
        return false;
    }

    private boolean valid(int newRow, int newCol, int m, int n) {
        return newRow>=0 && newRow<m && newCol >= 0 && newCol < n;
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