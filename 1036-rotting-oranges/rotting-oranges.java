class Solution {
    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        int freshOranges = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1) freshOranges++;
                if(grid[i][j] == 2){
                    int[] rottenOrange = new int[]{i, j, 0};
                    q.add(rottenOrange); 
                }
            }
        }

        if(freshOranges == 0) return 0;
        while(!q.isEmpty()){
            int[] curr = q.remove();

            for(int[] direction : directions){
                int newR = curr[0] + direction[0], newC = curr[1] + direction[1], newM = curr[2] + 1;
                if(newR >= 0 && newR < n && newC >= 0 && newC < m && grid[newR][newC] == 1){
                    freshOranges -= 1;
                    grid[newR][newC] = 2;
                    if (freshOranges == 0) return newM;
                    q.add(new int[]{newR, newC, newM});
                }
            }
        }
        return -1;
    }
}