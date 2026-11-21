class Solution {
    // for scanning 4-directional adjacent cells
    int[][] directions = new int[][]{{-1, 0},{0,-1},{1, 0},{0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                // island found -> compute area -> update max
                if(grid[i][j] == 1){
                    max = Math.max(max, dfs(i, j, n, m, grid));
                }
            }
        }
        return max;
    }

    private int dfs(int i, int j, int n, int m, int[][] grid){
        // check bounds and whether the cell is 0
        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != 1){
            return 0;
        }
        // mark the cell as visited to prevent duplicate processing
        grid[i][j] = 0;

        // count area of the current cell so start at 1
        int area = 1;
        for(int[] direction : directions){
            area += dfs(i + direction[0], j + direction[1], n, m, grid);
        }
        return area;
    }
}