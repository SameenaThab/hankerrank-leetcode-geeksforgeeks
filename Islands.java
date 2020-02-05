//https://leetcode.com/problems/number-of-islands/
//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

class Islands {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0)
            return 0;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                visited[i][j]=false;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    DFS(i,j,grid,visited);
                }  
        return count;
    }
    
    void DFS(int i,int j,char[][] grid,boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;
            visited[i][j] = true;
        int[] rws = {0,0,1,-1};
        int[] cols = {-1,1,0,0};
        
        for(int k=0;k<4;k++)
            if(i+rws[k]>=0 && i+rws[k]<m && j+cols[k]>=0 && j+cols[k]<n && !visited[i+rws[k]][j+cols[k]] && grid[i+rws[k]][j+cols[k]] == '1' )
                DFS(i+rws[k],j+cols[k],grid,visited);
        
    }
}