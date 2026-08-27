// Dynamic Programming approach

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for(int num : nums){
            for(int j = target; j >= num; j--){
                //check is sum of j can be computed if not check if j-num could be computed
                dp[j] = dp[j] || dp[j - num];
                // early return if we have found sum / 2
                if(dp[target]){
                    return true;
                }
            }
        }

        // dp[target] indicates whether there was a subset of elements that summed up to target
        return dp[target];
    }
}