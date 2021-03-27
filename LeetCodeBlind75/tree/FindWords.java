import java.util.*;

class FindWords {
    /* 
    https://leetcode.com/problems/word-search-ii/
    */
    char[][] board;
    int m,n;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        List<String> result = new ArrayList<String>();
        for(String word : words) {
            if(backTrack(word)) {
                result.add(word);
            }
        }
        return result;
    }

    boolean backTrack(String word) {
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(word.charAt(0) == board[i][j] && backTrackHelper(word,i,j,1,new int[m][n])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backTrackHelper(String word, int row, int col, int index, int[][] visited) {
        visited[row][col] = 1;
        if(index == word.length())
            return true;
        int[] rows = new int[] {1,-1,0,0};
        int[] cols = new int[] {0,0,1,-1};
        for(int i=0;i<4;i++) {
            int newRow = row+rows[i];
            int newCol = col+cols[i];
            if(valid(newRow,newCol,visited) && board[newRow][newCol] == word.charAt(index)) {
                if(backTrackHelper(word, newRow, newCol, index+1,visited)) {
                    return true; 
                }
            }
        }
        visited[row][col] = 0;
        return false;
    }

    private boolean valid(int newRow, int newCol, int[][] visited) {
        return newRow>=0 && newRow<m && newCol>=0 && newCol<n && visited[newRow][newCol]==0;
    }

    public static void main(String[] args) {
        FindWords sol = new FindWords();
        char[][] board = new char[][]{{'a','a'}};
        String[] words = new String[] {"aaa"};
        System.out.println("solution: "+sol.findWords(board,words));

        board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        words = new String[]{"oath","pea","eat","rain"};
        System.out.println("solution: "+sol.findWords(board,words));
    }
}