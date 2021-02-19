import java.util.LinkedList;
import java.util.Queue;

class NumIslands {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0)
            return 0;
        int n = grid[0].length;
        int count = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == '1') {
                    count++;
                    bfs(i,j,grid);
                }
            }
        }
        return count;
    }

    private void bfs(int i,int j,char[][] grid) {
        Queue<int[]> queue = new LinkedList<int[]>();
        int m = grid.length;
        int n = grid[0].length;
        grid[i][j] = '0';
        queue.add(new int[]{i,j});
        int[] rows = new int[]{1,-1,0,0};
        int[] cols = new int[]{0,0,1,-1};
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currI = curr[0];
            int currJ = curr[1];
            for(int k=0;k<4;k++) {
                int newI = currI+rows[k];
                int newJ = currJ+cols[k];
                if(newI>=0 && newI<m && newJ>=0 && newJ<n && grid[newI][newJ] == '1') {
                    grid[newI][newJ] = '0';
                    queue.add(new int[]{newI,newJ});
                }
            }
        }
    }

    public static void main(String[] args) {
        NumIslands sol = new NumIslands();
        char[][] grid = new char[][] {
                                    {'1','1','1','1','0'},
                                    {'1','1','0','1','0'},
                                    {'1','1','0','0','0'},
                                    {'0','0','0','0','0'}
                                };
        System.out.println("solution: "+sol.numIslands(grid));
    }
}