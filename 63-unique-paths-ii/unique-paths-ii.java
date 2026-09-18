class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] paths = new int[m];
        if(obstacleGrid[n-1][m-1] == 1) return 0;
        paths[m-1] = 1;
        for(int i = n - 1; i >= 0; i--){
            for(int j = m - 1; j >= 0; j--){
                if(obstacleGrid[i][j] == 1){
                    paths[j] = 0;
                }
                else if((j + 1) < m){
                    paths[j] = paths[j] + paths[j+1];
                }
            }
        }
        return paths[0];
    }
}