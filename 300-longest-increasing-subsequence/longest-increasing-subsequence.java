class Solution { // Dynamic Programming approach -- Tabulation
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 1) return 1;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // LIS for a single index is 1
        int max = 1;

        //We compute longest possible LIS for every index starting from the back
        for(int i = n -1; i >= 0; i--){
            for(int j = i+1; j < n; j++){
                if(nums[i] < nums[j]){
                    // what is currently at dp[i] vs potential candidate
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}