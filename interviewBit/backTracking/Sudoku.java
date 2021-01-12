import java.util.*;

class Cell {
    int row,col;
    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
public class Sudoku {

    /* [[53..7....], [6..195...], [.98....6.],
     [8...6...3], [4..8.3..1], [7...2...6],
    [.6....28.], [...419..5], [....8..79]] */

    public static void main(String[] args) {
        Sudoku sol = new Sudoku();
        ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();
        board.add(new ArrayList<Character>(Arrays.asList('5','3','.','.','7','.','.','.','.')));
        board.add(new ArrayList<Character>(Arrays.asList('6','.','.','1','9','5','.','.','.')));
        board.add(new ArrayList<Character>(Arrays.asList('.','9','8','.','.','.','.','6','.')));

        board.add(new ArrayList<Character>(Arrays.asList('8','.','.','.','6','.','.','.','3')));
        board.add(new ArrayList<Character>(Arrays.asList('4','.','.','8','.','3','.','.','1')));
        board.add(new ArrayList<Character>(Arrays.asList('7','.','.','.','2','.','.','.','6')));


        board.add(new ArrayList<Character>(Arrays.asList('.','6','.','.','.','.','2','8','.')));
        board.add(new ArrayList<Character>(Arrays.asList('.','.','.','4','1','9','.','.','5')));
        board.add(new ArrayList<Character>(Arrays.asList('.','.','.','.','8','.','.','7','9')));
        
        // System.out.println("test valid: "+sol.valid(board,0,2));

        System.out.println("before");
        for(ArrayList<Character> row: board){
            System.out.println(row);
        }
        sol.solveSudoku(board);
        System.out.println("after");
        for(ArrayList<Character> row: board){
            System.out.println(row);
        }
    }

    public void solveSudoku(ArrayList<ArrayList<Character>> board) {
        ArrayList<Cell> emptyCells = new ArrayList<Cell>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                if(board.get(i).get(j) == '.') {
                    emptyCells.add(new Cell(i,j));
                }
            }
        }
        // for(Cell cell : emptyCells) {
        //     System.out.println("cellRow: "+cell.row+" cellCol: "+cell.col);
        // }
        // System.out.println("size: "+emptyCells.size());
        solveSudoku(board,emptyCells,0);
    }


    private boolean solveSudoku(ArrayList<ArrayList<Character>> board, ArrayList<Cell> emptyCells, int index) {
        if(index == emptyCells.size()) {
            // System.out.println("base case index:"+index);
            return true;
        }
        Cell cell = emptyCells.get(index);
        int currRow = cell.row;
        int currCol = cell.col;
        // System.out.println("currRow: "+currRow+" currCol: "+currCol);
        for(char val='1';val<='9';val++) {
            if(valid(board,currRow,currCol,val)) {
                board.get(currRow).set(currCol,val);
                // System.out.println("value: "+val);
                // boolean sub = solveSudoku(board,emptyCells,index+1);
                // System.out.println("value: "+val+" sub: "+sub);
                if(solveSudoku(board,emptyCells,index+1)) {
                    return true;
                }
                board.get(currRow).set(currCol,'.');
            }
        }
        return false;
    }

    private boolean valid(ArrayList<ArrayList<Character>> board, int row, int col,char val) {
		for(int i=0;i<9;i++) {
            if(board.get(row).get(i) == val)
                return false;
            if(board.get(i).get(col) == val)
                return false;
        }
        // int blockRow1 = row/3;
        // int blockCol1 = col/3;
        int blockRow = row-row%(int)Math.sqrt(9);
        int blockCol = col-col%(int)Math.sqrt(9);
        // System.out.println("row: "+row+" col: "+col);
        // System.out.println("blockRow1: "+blockRow1+" blockCol1: "+blockCol1);
        // System.out.println("blockRow: "+blockRow+" blockCol: "+blockCol);
        int nn = (int)Math.sqrt(9);
        for(int i=0;i<nn;i++) {
            int x = blockRow+i;
            for(int j=0;j<nn;j++){
                int y = blockCol+j;
                if(board.get(x).get(y) == val)
                    return false;
            }
        }
        return true;
	}

}
