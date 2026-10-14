// Shortest Path Faster Algorithm (SPFA)
// class Solution {
//     public int swimInWater(int[][] grid) {
//         int n = grid.length;
//         int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
//         int[][] track = new int[n][n];
//         for(int i = 0; i < n; i++){
//             for(int j = 0; j < n; j++){
//                 track[i][j] = Integer.MAX_VALUE;
//             }
//         }

//         Queue<int[]> q = new LinkedList<>();
//         q.add(new int[]{0, 0, grid[0][0]});
//         track[0][0] = grid[0][0];

//         while(!q.isEmpty()){
//             int[] curr = q.remove();
//             int r = curr[0], c = curr[1], t = curr[2];

//             /*no early return to process all paths*/

//             for(int[] dir : dirs){
//                 int newR = r + dir[0], newC = c + dir[1];
//                 if(newR >= 0 && newR < n && newC >= 0 && newC < n){
//                     int newT = Math.max(t, grid[newR][newC]);
//                     if(track[newR][newC] > newT){
//                         track[newR][newC] = newT;
//                         q.add(new int[]{newR, newC, newT});
//                     }
//                 }
//             }
//         }
//         return track[n-1][n-1];
//     }
// }

// Dijkstra to avoid reprocessing cells
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] vis = new boolean[n][n];

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        q.add(new int[]{0, 0, grid[0][0]});
        vis[0][0] = true;

        while(!q.isEmpty()){
            int[] curr = q.remove();
            int r = curr[0], c = curr[1], t = curr[2];

            if(r == n - 1 && c == n - 1) return t;

            for(int[] dir : dirs){
                int newR = r + dir[0], newC = c + dir[1];
                if(newR >= 0 && newR < n && newC >= 0 && newC < n && !vis[newR][newC]){
                    vis[newR][newC] = true;
                    q.add(new int[]{newR, newC, Math.max(t, grid[newR][newC])});
                }
            }
        }
        return -1;
    }
}