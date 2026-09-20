class Solution {

    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {0, -1}, {1, -1}, {1, 1}, {1, 0}, {0, 1}, {-1, 1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];
        int n = board.length, m = board[0].length;

        if(board[row][col] == 'M'){
            board[row][col] = 'X';
            return board;
        }

        int mines = 0;
        for(int[] dir : directions){
            int newR = dir[0] + row, newC = dir[1] + col;
            if(newR < n && newR >= 0 && newC < m && newC >= 0 && board[newR][newC] == 'M'){
                mines++;
            }
        }
        if(mines > 0){
            board[row][col] = (char)(mines + '0');
            return board;
        }
        
        board[row][col] = 'B';
        for(int[] dir : directions){
            int newR = dir[0] + row, newC = dir[1] + col;
            if(newR < n && newR >= 0 && newC < m && newC >= 0 && board[newR][newC] == 'E'){
                updateBoard(board, new int[]{newR, newC});
            }
        }
        return board;
    }
}