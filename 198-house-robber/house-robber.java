// House robber serves as almost like a template DP problem
class Solution { 
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0; // robbed 0 houses -> 0 money
        dp[1] = nums[0];
        
        for(int i = 1; i < n; i++){
            //rob curr or skip
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        return dp[n];
    }
}