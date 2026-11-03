// DFS Solution
class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // found one island
                if(grid[i][j] == '1'){
                    count++;
                    dfs(i, j, n, m, grid);
                }
            }
        }
        return count;
    }

    // "sink" the island so that future calls do not treat a cell as a new island
    private void dfs(int i, int j, int n, int m, char[][] grid){
        // boundary check
        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == '0'){
            return;
        }
        // "sink" this cell
        grid[i][j] = '0';
        // keep "sinking" this island
        dfs(i - 1, j, n, m, grid);
        dfs(i + 1, j, n, m, grid);
        dfs(i, j + 1, n, m, grid);
        dfs(i, j - 1, n, m, grid);
    }
}
