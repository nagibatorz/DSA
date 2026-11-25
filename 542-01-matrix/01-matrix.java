class Solution {
     // Multi-source bfs
    // public int[][] updateMatrix(int[][] mat) {
    //     int n = mat.length, m = mat[0].length;
    //     int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    //     int[][] dists = new int[n][m];
    //     boolean[][] vis = new boolean[n][m];
    //     Queue<int[]> q = new ArrayDeque<>();

    //     // find all 0's and traverse starting from them
    //     for(int i = 0; i < n; i++){
    //         for(int j = 0; j < m; j++){
    //             if(mat[i][j] == 0){
    //                 vis[i][j] = true;
    //                 q.add(new int[]{i, j, 0});
    //             }
    //         }
    //     }

    //     // compute the shortest distance to every 0
    //     while(!q.isEmpty()){
    //         int[] curr = q.remove();
    //         int r = curr[0], c = curr[1], d = curr[2];
            
    //         for(int[] direction : directions){
    //             int newR = r + direction[0], newC = c + direction[1], newD = d + 1;
    //             if(newR >= 0 && newR < n && newC >= 0 && newC < m && !vis[newR][newC]){
    //                 vis[newR][newC] = true;
    //                 dists[newR][newC] = newD;
    //                 q.add(new int[]{newR, newC, newD});
    //             }
    //         }
    //     }
    //     return dists;
    // }

    // 2-pass DP
    public int[][] updateMatrix(int[][] mat){
        int n = mat.length, m = mat[0].length;
        int[][] dists = new int[n][m];
        int maxPoss = m + n;

        // Top-left computation
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 1){
                    dists[i][j] = (i > 0) ? Math.min(dists[i - 1][j] + 1, maxPoss) : maxPoss;
                    dists[i][j] = (j > 0) ? Math.min(dists[i][j - 1] + 1, dists[i][j]) : dists[i][j];
                }
            }
        }

        // bottom-right computation
        for(int i = n - 1; i >= 0; i--){
            for(int j = m - 1; j >= 0; j--){
                if(mat[i][j] == 1){
                    dists[i][j] = (i < n - 1) ? Math.min(dists[i + 1][j] + 1, dists[i][j]) : dists[i][j];
                    dists[i][j] = (j < m - 1) ? Math.min(dists[i][j + 1] + 1, dists[i][j]) : dists[i][j];
                }
            }
        }
        return dists;
    }
}