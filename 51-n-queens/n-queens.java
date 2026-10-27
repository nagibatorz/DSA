import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Backtracking with tracking indexes of columns, positive diagonals and negative diagonals
class Solution {
    // <col index>
    boolean[] cols;
    // <negative diagonal index> as (row - col)
    boolean[] negDiag;
    // <positive diagonal index> as (row + col)
    boolean[] posDiag;
    // track the state of the boatd
    char[][] board;

    public List<List<String>> solveNQueens(int n) {
        cols = new boolean[n];
        negDiag = new boolean[2 * n - 1];
        posDiag = new boolean[2 * n - 1];
        board = new char[n][n];
        
        // initialize to empty board 
        for(int i = 0; i < n; i++){
            Arrays.fill(board[i], '.');
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
            List<String> combination = new ArrayList<>(n);
            for(int i = 0; i < n; i++){
                combination.add(new String(board[i]));
            }
            res.add(combination);
            return; 
        }
        for(int col = 0; col < n; col++){
            int pDiag = row + col;
            int nDiag = row - col + n - 1; 
            
            if(cols[col] || posDiag[pDiag] || negDiag[nDiag]){
                continue;
            }
            
            cols[col] = true;
            posDiag[pDiag] = true;
            negDiag[nDiag] = true;
            board[row][col] = 'Q';
            
            bt(res, n, row + 1);
            
            cols[col] = false;
            posDiag[pDiag] = false;
            negDiag[nDiag] = false;
            board[row][col] = '.';
        }
    }
}