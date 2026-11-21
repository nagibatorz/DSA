class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        int maxArea = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] != 0){
                    int area = dfs(i, j, n, m, grid);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int i, int j, int n, int m, int[][] grid){
        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        return 1 + 
            dfs(i + 1, j, n, m, grid) +
            dfs(i, j + 1, n, m, grid) +
            dfs(i - 1, j, n, m, grid) +
            dfs(i, j - 1, n, m, grid);
    }
}