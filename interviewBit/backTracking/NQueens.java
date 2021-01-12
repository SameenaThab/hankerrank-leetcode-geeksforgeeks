import java.util.*;

public class NQueens {

    public static void main(String[] args) {
        NQueens sol = new NQueens();
        System.out.println("Solution: "+sol.solveNQueens(4));
    }

    public ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>> ();
        ArrayList<String> subResult = new ArrayList<String>();
        String string = new String();
        for(int j=0;j<n;j++) {
            string+=".";
        }
        for(int i=0;i<n;i++) {
            subResult.add(new String(string));

        }
        // System.out.println("test valid");
        // ArrayList<String> testList = new ArrayList<String>(Arrays.asList("...Q","...Q", "Q...", "..Q."));
        // System.out.println(valid(testList,0,3,n));
        // for(char[] arr:subResult) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // solveNQueensBT(result,subResult,0,n);
        // array represents which column queens are stores in a row.
        //index is row and value is column;
        int[] queens = new int[n];
        solveNQueensBTOptimal(result,queens,0,n);
        return result;
    }

    private void solveNQueensBTOptimal(ArrayList<ArrayList<String>> result, int[] queens, int row, int n) {
        if(row == n) {
            result.add(arrToList(queens));
            return;
        }
        for(int col=0;col<n;col++) {
            // this step acts as backtracking as every loop new col is assigned to same row
            queens[row] = col; 
            if(valid(queens,row)) {
                solveNQueensBTOptimal(result, queens, row+1, n);
            }
        }
    }

    private boolean valid(int[] queens, int row) {
        for(int i=0;i<row;i++) { // just checking prior rows
            int diagonal1 = Math.abs(queens[i]-queens[row]); 
            int diagonal2 = row-i;
            // checking if same column assigned or same diagonal
            if(queens[row] == queens[i] || diagonal1 == diagonal2) 
                return false;
        }
        return true;
    }

    private ArrayList<String> arrToList(int[] queens) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<queens.length;i++) {
            String st = "";
            int column = queens[i];
            for(int j=0;j<queens.length;j++) {
                if(j == queens[i]){
                    st+="Q";
                } else {
                    st+=".";
                }
            }
            list.add(st);
        }
        return list;
    }

    private void solveNQueensBT(ArrayList<ArrayList<String>> result, ArrayList<String> subResult, int row, int n) {
        if(row == n) {
            result.add(new ArrayList<String>(subResult));
            return;
        }
        for(int col=0;col<n;col++) {
            String curr = subResult.get(row);
            String newString = curr.substring(0,col)+"Q"+curr.substring(col+1);
            subResult.set(row,newString);
            if(valid(subResult,row,col,n)) {
                solveNQueensBT(result, subResult, row+1, n);
            }
            subResult.set(row,curr);
        }
    }

    private boolean valid(ArrayList<String> subResult, int row, int col,int n) {
        for(int i=row-1;i>=0;i--) {
            if(subResult.get(i).charAt(col) == 'Q')
                return false;
        }
        // This might not be necessary as later rows will not be assigned
        // for(int i=row+1;i<n;i++) {
        //     if(subResult.get(i).charAt(col) == 'Q')
        //         return false;
        // }
        for(int i=col-1;i>=0;i--) {
            if(subResult.get(row).charAt(i) == 'Q')
                return false;
        }
        for(int i=col+1;i<n;i++) {
            if(subResult.get(row).charAt(i) == 'Q')
                return false;
        }
        for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--) {
            if(subResult.get(i).charAt(j) == 'Q')
                return false;
        }
        for(int i=row+1,j=col+1;i<n&&j<n;i++,j++) {
            if(subResult.get(i).charAt(j) == 'Q')
                return false;
        }
        for(int i=row-1,j=col+1;i>=0&&j<n;i--,j++) {
            if(subResult.get(i).charAt(j) == 'Q')
                return false;
        }
        for(int i=row+1,j=col-1;i<0&&j>=0;i++,j--) {
            if(subResult.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }
}