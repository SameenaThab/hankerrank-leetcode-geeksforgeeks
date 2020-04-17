//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2987/
class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;
        Set<String> visited = new HashSet<String>();
        visited.add(sr+" "+sc);
        helper(image,sr,sc,newColor,oldColor);
        return image;
    }
    
    public void helper(int[][] image,int sr,int sc,int newColor,int oldColor) {
        int m = image.length;
        int n = image[0].length;
        //System.out.println("sr: "+sr+" sc: "+sc);
        int[] adjr = new int[]{0,0,1,-1};
        int[] adjc = new int[]{1,-1,0,0};
        for(int i=0;i<4;i++) {
            int row = sr+adjr[i];
            int col = sc+adjc[i];
            //System.out.println("row: "+row+" col: "+col);
            if(row>=0 && row<m && col>=0 && col<n && image[row][col] == oldColor && image[row][col] != newColor) {
                //visited.add(row+" "+col);
                image[row][col] = newColor;
                //System.out.println("row: "+row+" col: "+col);
                helper(image,row,col,newColor,oldColor);
            }
        }
    }
}