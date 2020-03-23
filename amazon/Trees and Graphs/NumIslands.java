//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/894/ 
 class NumIslands {
     char[][] grid;
    public int numIslands(char[][] grid) {
        Set<String> visited = new HashSet<String>();
        int result = 0;
        this.grid = grid;
        for(int i =0;i<this.grid.length;i++) {
            for(int j=0;j<this.grid[0].length;j++) {
                if(this.grid[i][j] == '1') {
                    dfs(i,j);
                    result++;
                }
                
            }
        }
        return result;
    }
    
    public void dfs(int row,int col) {
        //System.out.println(row+" "+col);
        grid[row][col] = '0';
        int[] rows = {0,0,-1,1};
        int[] cols = {1,-1,0,0};
        for(int i=0;i<4;i++) {
            int newrow = row+rows[i];
            int newcol = col+cols[i];
            if(newrow>=0 && newrow < grid.length && newcol >= 0 && newcol < grid[0].length && grid[newrow][newcol] == '1') {
                dfs(newrow,newcol);
            }
        }
    }
}