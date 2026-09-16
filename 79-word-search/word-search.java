// Backtracking DFS Optimal
class Solution {
    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length, len = word.length();
        if(n == 0 || m == 0) return false;
        if(word.equals("")) return true;
        char[] wordC = word.toCharArray();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // start recursing if char matches first char in word
                if(board[i][j] == wordC[0] && dfs(i, j, n, m, board, wordC, 0, len)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int n, int m, char[][] board, char[] wordC, int idx, int len){
        //check bounds and char equality
        if(i >= n || i < 0 || j >= m || j < 0 || board[i][j] != wordC[idx]){
            return false;
        }

        //successfully scanned all chars
        if(idx == len - 1){
            return true;
        }
        
        //mark as visited
        char curr = board[i][j];
        int newIdx = idx + 1;
        board[i][j] = '#';

        // scan neigbors
        if (dfs(i + 1, j, n, m, board, wordC, newIdx, len) || 
            dfs(i, j + 1, n, m, board, wordC, newIdx, len) ||
            dfs(i - 1, j, n, m, board, wordC, newIdx, len) ||
            dfs(i, j - 1, n, m, board, wordC, newIdx, len)) {
            
            return true;
        }

        board[i][j] = curr;
        return false;
    }
}