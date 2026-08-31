class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // exactly 1 way to get 0

        // Process one coin at a time to get the number of Combinations
        // if we process amount in the outer loop we get Permutations
        for(int coin : coins){
            for(int i = coin; i <= amount; i++){
                if(i - coin >= 0){
                    dp[i] = dp[i] + dp[i-coin];
                }
            }
        }
        return dp[amount];
    }
}