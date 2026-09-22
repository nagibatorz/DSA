// BFS with a Minimum Eliminations Array and Double-ended Queue
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

                    //check whether new path eliminates less obstacles
                    if(minElim[newR][newC] > newE){
                        //update minimum eliminations to reach cell at (newR, newC)
                        minElim[newR][newC] = newE;

                        //if cell is a 1 we add to the back of the q
                        // if it is a zero to the front
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