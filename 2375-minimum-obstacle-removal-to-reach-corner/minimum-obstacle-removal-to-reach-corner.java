class Solution {
    public int minimumObstacles(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Deque<int[]> q = new ArrayDeque<>();
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        q.offerFirst(new int[]{0, 0, 0});
        int[][] minElim = new int[n][m];
        
        //prepopulate with inf to allow cell that initially enters new cell overwrite the value
        for(int[] row : minElim) Arrays.fill(row, Integer.MAX_VALUE);
        minElim[0][0] = 0;

        while(!q.isEmpty()){
            int[] curr = q.pollFirst();
            int r = curr[0], c = curr[1], e = curr[2];

            // first path to reach bottom-right is the shortest path due to BFS logic. 
            if(r == n - 1 && c == m - 1) return e;

            for(int[] direction : directions){
                int newR = r + direction[0], newC = c + direction[1];
                if(newR >= 0 && newR < n && newC >= 0 && newC < m){
                    int newE = e + grid[newR][newC]; 

                    //Only scan next cell if we have eliminations left and it uses less available eliminations
                    //no need for else to push ti q because if cell eliminates nothing it will still update the minElim entry because that one is initially inf
                    if(minElim[newR][newC] > newE){
                        minElim[newR][newC] = newE;
                        if(grid[newR][newC] == 1){
                            q.offerLast(new int[]{newR, newC, newE});
                        } else{
                            q.offerFirst(new int[]{newR, newC, newE});
                        }
                    }
                }
            }

        }

        return -1;
    }
}