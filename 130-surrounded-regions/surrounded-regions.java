class Solution {
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void solve(char[][] board) {
        if(board.length == 1) return;
        int n = board.length, m = board[0].length;
        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O') markSafe(i, 0, n, m, board);
            if(board[i][m-1] == 'O') markSafe(i, m-1, n, m, board);
        }

        for(int j = 0; j < m; j++){
            if(board[0][j] == 'O') markSafe(0, j, n, m, board);
            if(board[n-1][j] == 'O') markSafe(n-1, j, n, m, board);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == 'T') board[i][j] = 'O';

            }
        }
    }

    private void markSafe(int i, int j, int n, int m, char[][] board){
        if(i < 0 || i >= n || j < 0 || j >= m || board[i][j] != 'O'){
            return;
        }
        board[i][j] = 'T';
        for(int[] direction : directions){
            int newR = i + direction[0], newC = j + direction[1];
            markSafe(newR, newC, n, m, board);
        }
    }
}