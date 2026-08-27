class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length, m  = heights[0].length;
        boolean[][] pac = new boolean[n][m];
        boolean[][] atl = new boolean[n][m];
        for(int i = 0; i < n; i++){
            dfs(i, 0, n, m, heights, heights[i][0], pac);
            dfs(i, m - 1, n, m, heights, heights[i][m-1], atl);
        }
        for(int j = 0; j < m; j++){
            dfs(0, j, n, m, heights, heights[0][j], pac);
            dfs(n - 1 , j, n, m, heights, heights[n - 1][j], atl);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(atl[i][j] && pac[i][j]){
                    res.add(List.of(i, j));
                }
            }
        }
        return res;
    }


    private void dfs(int i, int j, int n, int m, int[][] heights, int prev, boolean[][] oc){
        if(i >= n || j >= m || i < 0 || j < 0 || oc[i][j] || heights[i][j] < prev){
            return;
        }
        oc[i][j] = true;
        dfs(i - 1, j, n, m, heights, heights[i][j], oc);
        dfs(i + 1, j, n, m, heights, heights[i][j], oc);
        dfs(i, j + 1, n, m, heights, heights[i][j], oc);
        dfs(i, j - 1, n, m, heights, heights[i][j], oc);
    }
}
