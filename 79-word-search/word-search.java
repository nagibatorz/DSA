class Solution {
    int[][] directions = new int[][]{{1, 0},{0, 1},{-1, 0},{0, -1}};
    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length, len = word.length();
        if(n == 0 || m == 0) return false;
        if(word.equals("")) return true;
        
        boolean[][] vis = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == word.charAt(0) && dfs(i, j, n, m, board, vis, word, 0, len)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int n, int m, char[][] board, boolean[][] vis, String word, int idx, int len){
        if(i >= n || i < 0 || j >= m || j < 0 || vis[i][j] || board[i][j] != word.charAt(idx)){
            return false;
        }
        if(idx == len - 1){
            return true;
        }
        vis[i][j] = true;
        char curr = board[i][j];
        for(int[] direction : directions){
            int newR = i + direction[0], newC = j + direction[1];
            if (dfs(newR, newC, n, m, board, vis, word, idx + 1, len)) {
                return true;
            }

        }
        vis[i][j] = false;
        return false;
    }
}