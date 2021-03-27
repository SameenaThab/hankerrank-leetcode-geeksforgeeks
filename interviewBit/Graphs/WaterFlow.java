
import java.util.*;

class WaterFlow {

/* 
Since all the elements are postive numbers.
For Every cell reachable to both sides, we mark them with -2
else we mark them with -1
We check a cell reaches to both sides if DFS reached to (row0 or col0) && (row n-1 or col n-1)
At the end we count number of cells with value -2
*/
public int solve(ArrayList<ArrayList<Integer>> matrix) {
    int m = matrix.size();
    if(m == 0)
        return 0;
    int n = matrix.get(0).size();
    int[][] visited1 = new int[m][n];
    int[][] visited2 = new int[m][n];
    for(int i=0;i<n;i++) {
        if(visited1[0][i] == 0)
            bfs(matrix,0,i,visited1);
    }
    for(int i=0;i<m;i++) {
        if(visited1[i][0] == 0)
            bfs(matrix,i,0,visited1);
    }
    System.out.println("visited1: ");
    for(int[] arr:visited1) {
        System.out.println(Arrays.toString(arr));
    }
    for(int i=0;i<n;i++) {
        if(visited2[m-1][i] == 0)
            bfs(matrix,m-1,i,visited2);
    }
    for(int i=0;i<m;i++) {
        if(visited2[i][n-1] == 0)
            bfs(matrix,i,n-1,visited2);
    }

    System.out.println("visited2: ");
    for(int[] arr:visited2) {
        System.out.println(Arrays.toString(arr));
    }
    int count = 0;
    for(int i=0;i<m;i++) {
        for(int j=0;j<n;j++) {
            if(visited1[i][j]+visited2[i][j] == 2) {
                count++;
            }
        }
    }
    return count;
}


private void bfs(ArrayList<ArrayList<Integer>> matrix, int row, int col,int[][] visited) {
    Queue<int[]> queue = new LinkedList<int[]>();
    queue.add(new int[]{row,col});
    int[] rows = new int[] {1,-1,0,0};
    int[] cols = new int[] {0,0,1,-1};
    int m = matrix.size();
    int n = matrix.get(0).size();
    visited[row][col]=1;
    while(!queue.isEmpty()) {
        int[] curr = queue.poll();
        int currRow = curr[0];
        int currCol = curr[1];
        for(int i=0;i<4;i++) {
            int newRow = currRow+rows[i];
            int newCol = currCol+cols[i];
            if(valid(newRow,newCol,m,n) && visited[newRow][newCol] == 0 && matrix.get(newRow).get(newCol) >= matrix.get(currRow).get(currCol)) {
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
        WaterFlow sol = new WaterFlow();
        // int[][] matrix = new int[][]{
        //     {1,2,2,3,5},
        //     {3,2,3,4,4},
        //     {2,4,5,3,1},
        //     {6,7,1,4,5},
        //     {5,1,1,2,4}
        // };
        ArrayList<ArrayList<Integer>>  matrix = new ArrayList<ArrayList<Integer>>();
        matrix.add(new ArrayList<Integer>(Arrays.asList(2,2)));
        matrix.add(new ArrayList<Integer>(Arrays.asList(2,2)));
        System.out.println("solution: "+sol.solve(matrix));

        ArrayList<ArrayList<Integer>>  matrix2 = new ArrayList<ArrayList<Integer>>();
        matrix2.add(new ArrayList<Integer>(Arrays.asList(1, 2, 2, 3, 5)));
        matrix2.add(new ArrayList<Integer>(Arrays.asList(3, 2, 3, 4, 4)));
        matrix2.add(new ArrayList<Integer>(Arrays.asList(2, 4, 5, 3, 1)));
        matrix2.add(new ArrayList<Integer>(Arrays.asList(6, 7, 1, 4, 5)));
        matrix2.add(new ArrayList<Integer>(Arrays.asList(5, 1, 1, 2, 4)));
        System.out.println("solution: "+sol.solve(matrix2));
    }

}