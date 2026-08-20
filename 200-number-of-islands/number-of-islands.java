class Solution {
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        int n = grid.length, m = grid[0].length;
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '1'){
                    count++;
                    dfs(i, j, n, m, grid);
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, int n, int m, char[][] grid){
        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';
        dfs(i + 1, j, n, m, grid);
        dfs(i, j + 1, n, m, grid);
        dfs(i - 1, j, n, m, grid);
        dfs(i, j - 1, n, m, grid);
    }
}