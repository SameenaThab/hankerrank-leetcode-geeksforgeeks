class OrangesRotting {
    //https://leetcode.com/problems/rotting-oranges/submissions/
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int freshOranges=0;
        Queue<int[]> queue = new LinkedList<int[]>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == 2)
                    queue.add(new int[]{i,j});
                else if(grid[i][j] == 1)
                    freshOranges++;
            }
        }
        int minutes = 0;
        int[] rows = new int[]{0,0,1,-1};
        int[] cols = new int[]{1,-1,0,0};
        
        while(!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] curr = queue.poll();
                int currR = curr[0];
                int currC = curr[1];
                for(int j=0;j<4;j++) {
                    int newR = currR+rows[j];
                    int newC = currC+cols[j];
                    if(valid(newR,newC,m,n,grid)) {
                        grid[newR][newC]=2;
                        queue.add(new int[]{newR,newC});
                        freshOranges--;
                    }
                }
            }
            minutes++;
        }
        
        return freshOranges == 0? minutes:-1;
    }
    
    boolean valid(int i,int j,int m,int n,int[][] grid) {
        return i>=0 && i<m && j>=0 &&j<n && grid[i][j] == 1;
    }
}