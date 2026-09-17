// Time Complexity: O(m * n)
// Space Complexity: O(m * n)

public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    public int numberofDistinctIslands(int[][] grid) {
        if(grid.length == 0) return 0;
        int n = grid.length, m = grid[0].length;
        Set<String> distinctIslands = new HashSet<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();
                    sb.append("X");
                    String path = dfs(i, j, n, m, grid, sb);
                    distinctIslands.add(path);
                }
            }
        }
        return distinctIslands.size();
    }

    private String dfs(int i, int j, int n, int m, int[][] grid, StringBuilder path){
        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 0){
            return "O";
        }
        grid[i][j] = 0;
        String west = dfs(i, j - 1, n, m, grid, "W");
        String east = dfs(i, j + 1, n, m, grid, "E");
        String north = dfs(i - 1, j, n, m, grid, "N");
        String south = dfs(i + 1, j, n, m, grid, "S");
        path.append(west).append(east).append(north).append(south);
        return path.toString();
    }
}
