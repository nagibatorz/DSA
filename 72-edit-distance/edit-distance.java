// DP Tabulation approach
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        char[] carr1 = word1.toCharArray();
        char[] carr2 = word2.toCharArray();

        // Transform "" to word 1
        for(int i = 0; i <= n; i++){
            dp[i][0] = i;
        }

        // Transform "" to word 2
        for(int i = 0; i <= m; i++){
            dp[0][i] = i;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(carr1[i-1] == carr2[j-1]){ // Reuse the previous string - char is the same
                    dp[i][j] = dp[i-1][j-1];
                } else{ // Pick the most efficient operation
                    dp[i][j] = 1+ Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[n][m];
    }
}