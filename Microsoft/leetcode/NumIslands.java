import java.util.*;

class NumIslands {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]=='1') {
                    count++;
                    bfs(i,j,grid);
                }
            }
        }
        return count;
    }
    
    void bfs(int x,int y,char[][] grid) {
        grid[x][y]='0';
        int[] rows = new int[]{-1,1,0,0};
        int[] cols = new int[]{0,0,1,-1};
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{x,y});
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            for(int i=0;i<4;i++) {
                int newX = currX+rows[i];
                int newY = currY+cols[i];
                if(newX>=0 && newX<grid.length && newY>=0 && newY<grid[0].length && grid[newX][newY]=='1'){
                    grid[newX][newY]='0';
                    queue.add(new int[]{newX,newY});
                }
            }            
        }
    }
}