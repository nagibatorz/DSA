class Solution {
    public int minimumObstacles(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offerFirst(new int[]{0, 0, 0}); // r, c, cost

        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while (!dq.isEmpty()) {
            int[] curr = dq.pollFirst();
            int r = curr[0], c = curr[1], cost = curr[2];

            // Mark on POP (Dijkstra property)
            if (vis[r][c]) continue;
            vis[r][c] = true;

            if (r == n - 1 && c == m - 1) return cost;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !vis[nr][nc]) {
                    if (grid[nr][nc] == 0) {
                        dq.offerFirst(new int[]{nr, nc, cost});     // 0 weight -> front
                    } else {
                        dq.offerLast(new int[]{nr, nc, cost + 1});  // 1 weight -> back
                    }
                }
            }
        }
        return -1;
    }
}