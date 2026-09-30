// Multi-Source BFS approach
class Solution {
    public void islandsAndTreasure(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        // get the sources for bfs
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 0){
                    q.add(new int[]{i, j, 0});
                    vis[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()){
            int[] curr = q.remove();
            int r = curr[0], c = curr[1], d = curr[2];
          //modify the grid
            grid[r][c] = d;
            for(int[] dir : dirs){
                int newR = r + dir[0], newC = c + dir[1], newD = d + 1;
                if(newR >= 0 && newR < n && newC >= 0 && newC < m && grid[newR][newC] != -1 && !vis[newR][newC]){
                    vis[newR][newC] = true;
                    q.add(new int[]{newR, newC, newD});
                }
            }
        }

    }
}
