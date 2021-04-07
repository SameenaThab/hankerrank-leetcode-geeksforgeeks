import java.util.*;
class SpiralOrder {
    /*
    We define the k-th outer layer of a matrix as all elements that have minimum distance to some border equal to k.
    For example, the following matrix has all elements in the first-outer layer equal to 1, all elements in the second-outer layer equal to 2, and all elements in the third-outer layer equal to 3.
    eg:[[1, 1, 1, 1, 1, 1, 1],
        [1, 2, 2, 2, 2, 2, 1],
        [1, 2, 3, 3, 3, 2, 1],
        [1, 2, 2, 2, 2, 2, 1],
        [1, 1, 1, 1, 1, 1, 1]]
    For each outer layer, we want to iterate through its elements in clockwise order starting from the top left corner.
    Suppose the current outer layer has top-left coordinates (r1, c1) and bottom-right coordinates (r2, c2).
    Then, the top row is the set of elements (r1, c) for c = c1,...,c2, in that order. The rest of the right side is the set of elements (r, c2) for r = r1+1,...,r2, in that order. Then, if there are four sides to this layer (ie., r1 < r2 and c1 < c2), we iterate through the bottom side and left side as shown in the solutions below.
    time : O(n), n = no of elements in matrix
    Space: O(1)
    */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if(matrix.length == 0)
            return result;
        int rowStart = 0;
        int rowEnd = matrix.length-1;
        int colStart = 0;
        int colEnd = matrix[0].length-1;
        while(rowStart <= rowEnd && colStart <= colEnd) {
            for(int i=colStart;i<=colEnd;i++) {
                result.add(matrix[rowStart][i]);
            }
            for(int i=rowStart+1;i<=rowEnd;i++) {
                result.add(matrix[i][colEnd]);
            }
            // bcoz if equal dont have to do it twice
            if(rowStart < rowEnd && colStart < colEnd) {
                // reverse directions
                // i>colStart and i>rowStart bcoz corners will be added twice
                for(int i=colEnd-1;i>colStart;i--) {
                    result.add(matrix[rowEnd][i]);
                }
                for(int i=rowEnd;i>rowStart;i--) {
                    result.add(matrix[i][colStart]);
                }                
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }
        return result;
    }

    /*
    Let the array have R rows and C columns. seen[r][c] denotes that the cell on the r-th row and c-th column was previously visited.
    Our current position is (r, c), facing direction di, and we want to visit R x C total cells.
    As we move through the matrix, our candidate next position is (cr, cc).
    If the candidate is in the bounds of the matrix and unseen, then it becomes our next position; otherwise, our next position is the one after performing a clockwise turn.
    Time: O(n), n = no of elements in matrix
    SPace:O(n)
    */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }
}