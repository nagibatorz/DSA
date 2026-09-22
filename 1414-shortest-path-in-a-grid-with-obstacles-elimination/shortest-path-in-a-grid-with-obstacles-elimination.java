//BFS + optimization array
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        q.add(new int[]{0, 0, 0, 0});
        int[][] minElim = new int[n][m];
        
        //prepopulate with inf to allow cell that initially enters new cell overwrite the value
        for(int[] row : minElim) Arrays.fill(row, Integer.MAX_VALUE);
        minElim[0][0] = 0;

        while(!q.isEmpty()){
            int[] curr = q.remove();
            int r = curr[0], c = curr[1], e = curr[2], d = curr[3];

            // first path to reach bottom-right is the shortest path due to BFS logic. 
            if(r == n - 1 && c == m - 1) return d;

            for(int[] direction : directions){
                int newR = r + direction[0], newC = c + direction[1];
                if(newR >= 0 && newR < n && newC >= 0 && newC < m){
                    int newE = e + (grid[newR][newC] == 1 ? 1 : 0); 

                    //Only scan next cell if we have eliminations left and it uses less available eliminations
                    //no need for else to push ti q because if cell eliminates nothing it will still update the minElim entry because that one is initially inf
                    if(newE <= k && minElim[newR][newC] > newE){
                        minElim[newR][newC] = newE;
                        q.add(new int[]{newR, newC, newE, d + 1});
                    }
                }
            }

        }

        return -1;
    }
}