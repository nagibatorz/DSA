class Solution {
    private int[][] goldId;
    private int goldCount;
    private Map<Integer, Integer>[] memo;
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        goldId = new int[m][n];
        goldCount = 0;

        // Assign an ID from 0 to K-1 for every cell with gold
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    goldId[i][j] = goldCount++;
                } else {
                    goldId[i][j] = -1;
                }
            }
        }

        memo = new HashMap[goldCount];
        for (int i = 0; i < goldCount; i++) {
            memo[i] = new HashMap<>();
        }

        int maxGold = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    int id = goldId[i][j];
                    maxGold = Math.max(maxGold, dfs(grid, i, j, 1 << id));
                }
            }
        }

        return maxGold;
    }

    private int dfs(int[][] grid, int r, int c, int mask) {
        int id = goldId[r][c];
        if (memo[id].containsKey(mask)) {
            return memo[id].get(mask);
        }

        int bestNeighbor = 0;
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] > 0) {
                int nextId = goldId[nr][nc];
                if ((mask & (1 << nextId)) == 0) {
                    bestNeighbor = Math.max(bestNeighbor, dfs(grid, nr, nc, mask | (1 << nextId)));
                }
            }
        }

        int total = grid[r][c] + bestNeighbor;
        memo[id].put(mask, total);
        return total;
    }
}