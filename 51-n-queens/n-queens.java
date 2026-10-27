// Backtracking with tracking indexes of columns, positive diagonals and negative diagonals
class Solution {
    // <col index>
    Set<Integer> cols;
    // <negative diagonal index> as (row - col)
    Set<Integer> negDiag;
    // <positive diagonal index> as (row + col)
    Set<Integer> posDiag;
    // track the state of the boatd
    char[][] board;
    public List<List<String>> solveNQueens(int n) {
        cols = new HashSet<>(n);
        negDiag = new HashSet<>(n);
        posDiag = new HashSet<>(n);
        board = new char[n][n];
        // initialize to empty board 
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        bt(res, n, 0);
        return res;
    }

    // iterate by rows using recursion and within each col we check all columns and diagonals.
    // we use column index within the for loop because it is used to compute indexes of diagonals
    private void bt(List<List<String>> res, int n, int row){
        if(row == n){
            // add copy of board to res as a list of strings
            List<String> combination = new ArrayList<>();
            for(int i = 0; i < n; i++){
                combination.add(new String(board[i]));
            }
            res.add(combination);
        }
        for(int col = 0; col < n; col++){
            if(cols.contains(col) || posDiag.contains(row + col) || negDiag.contains(row - col)){
                continue;
            }
            cols.add(col);
            posDiag.add(row + col);
            negDiag.add(row - col);
            board[row][col] = 'Q';
            bt(res, n, row + 1);
            cols.remove(col);
            posDiag.remove(row + col);
            negDiag.remove(row - col);
            board[row][col] = '.';
        }
    }
}