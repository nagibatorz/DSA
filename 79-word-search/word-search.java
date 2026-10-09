class Solution {
    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == word.charAt(0)){
                    if(dfs(board, i, j, n, m, new StringBuilder(), word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int n, int m, StringBuilder sb, String word){
        if(sb.length() == word.length()){
            return sb.toString().equals(word);
        }
        if(i < 0 || i >= n || j < 0 || j >= m || board[i][j] == '#'){
            return false;
        }
        sb.append(""+board[i][j]);
        char temp = board[i][j];
        board[i][j] = '#';
        
        boolean exist = dfs(board, i, j + 1, n, m, sb, word) 
        || dfs(board, i + 1, j, n, m, sb, word) 
        || dfs(board, i - 1, j, n, m, sb, word) 
        || dfs(board, i, j - 1, n, m, sb, word);
        sb.deleteCharAt(sb.length() - 1);
        board[i][j] = temp;
        return exist;
    }
}