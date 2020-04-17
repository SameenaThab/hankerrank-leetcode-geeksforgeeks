//https://leetcode.com/explore/interview/card/amazon/84/recursion/2989/
class WordSearch {
    public boolean exist(char[][] board, String word) {
        char ch = word.charAt(0);
        int m = board.length;
        int n = board[0].length;
        //System.out.println(m+"-"+n);
        Set<String> visited = new HashSet<String>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                //System.out.println("back to method");
                if(board[i][j] == ch) {
                    if(helper(board,i,j,1,word,new HashSet<String>()))
                        return true;
                }
            }
        }
        return false;
    }
    
    public boolean helper(char[][] board,int row,int col,int index,String word,Set<String> visited) {
        // System.out.println(row+"-"+col);
        // System.out.println("index: "+index);
        if(index >= word.length())
            return true;
        visited.add(row+" "+col);
        int m = board.length;
        int n = board[0].length;
        int[] adjR = new int[]{0,0,1,-1};
        int[] adjC = new int[]{1,-1,0,0};
        for(int i=0;i<adjR.length;i++){
            int left = row+adjR[i];
            int right = col+adjC[i];
          if(isSafe(left,right,visited,board) && board[left][right] == word.charAt(index)){
            boolean ret = helper(board,left,right,index+1,word,visited);
            if(ret)
                return true;
              else
              {
                  visited.remove(left+" "+right);
              }
          }
        }
        return false;
    }
    
    public boolean isSafe(int i,int j ,Set<String> visited,char[][] board) {
        int m = board.length;
        int n=board[0].length;
        return i>=0 && i<m && j>=0 && j<n && !visited.contains(i+" "+j);
    }
}