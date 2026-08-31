// Dynamic Programming Tabulation approach, Optimized Space Complexity
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // we keep one row in memory at a time
        dp[0] = 1; // exactly 1 way to get 0

        // Process one coin at a time to get the number of Combinations
        // if we process amount in the outer loop we get Permutations
        for(int coin : coins){
            for(int i = coin; i <= amount; i++){
                if(i - coin >= 0){
                    // dp[i] - check how many ways there are to compute i;
                    // if i-coin is valid -> we add however many ways there are to compute i-coin
                    dp[i] = dp[i] + dp[i-coin];
                }
            }
        }
        return dp[amount];
    }
}