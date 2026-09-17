// Bactracking DFS
class Solution {
    public int getMaximumGold(int[][] grid) {
        if(grid.length == 0) return 0;
        int n = grid.length, m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                //start exploring from a cell that has gold
                if(grid[i][j] > 0){
                    max = Math.max(max, dfs(i, j, n, m, grid, vis));
                }
            }
        }
        return max;
    }


    private int dfs(int i, int j, int n, int m, int[][] grid, boolean[][] vis){
        if(i >= n || i < 0 || j >= m || j < 0 || vis[i][j] || grid[i][j] == 0){
            return 0;
        }
        vis[i][j] = true;

        //find the most profitable directions for the current cell
        int[] directions = new int[4];
        directions[0] = dfs(i, j + 1, n, m, grid, vis);
        directions[1] = dfs(i, j - 1, n, m, grid, vis);
        directions[2] = dfs(i + 1, j, n, m, grid, vis);
        directions[3] = dfs(i - 1, j, n, m, grid, vis);
        vis[i][j] = false;
        int max = 0;
        for(int k = 0; k < 4; k++){
            max = Math.max(max, directions[k]);
        }

        //add the current cell's gold to the most profitable amount
        return max + grid[i][j];
    }
}