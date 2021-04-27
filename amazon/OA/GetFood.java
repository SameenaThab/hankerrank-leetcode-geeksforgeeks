class GetFood {
    //https://leetcode.com/problems/shortest-path-to-get-food/submissions/
    public int getFood(char[][] grid) {
        // int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == '*') {
                    return bfs(grid,i,j);
                }
            }
        }
        return -1;
    }
    
    int bfs(char[][] grid,int x,int y) {
        // System.out.println(x+" "+y);
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{x,y});
        grid[x][y] = 'X';
        int count = 0;
        int[] rows = new int[] {1,-1,0,0};
        int[] cols = new int[] {0,0,1,-1};
        while(!queue.isEmpty()) {
            int size = queue.size();
            // System.out.println("size: "+size);
            for(int i=0;i<size;i++) {                
                int[] curr = queue.poll();
                int currRow = curr[0];
                int currCol = curr[1];
                // System.out.println("curr: "+currRow+" "+currCol);
                for(int k=0;k<4;k++) {
                    int newRow = currRow+rows[k];
                    int newCol = currCol+cols[k];
                    if(valid(newRow,newCol,grid)){
                        if(grid[newRow][newCol] == '#')
                            return count+1;
                        grid[newRow][newCol] = 'X';
                        queue.add(new int[]{newRow,newCol});
                    }
                }
            }
            count++; 
        }
        
        return -1;
    }
    
    boolean valid(int i,int j,char[][] grid) {
        return i>=0 && i<grid.length && j>=0 && j<grid[0].length && grid[i][j] != 'X';
    }
}