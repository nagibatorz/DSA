class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        //dp[n] = 0 - base case
        int[] dp = new int[n+1];
        int[] passes = new int[]{1, 7, 30};
        for(int i = n - 1; i >= 0; i--){
            dp[i] = Integer.MAX_VALUE;
            int idx = 0, j = i;
            for(int pass : passes){
                while(j < n && days[j] < days[i] + pass){
                    j++;
                }
                //compare current spent with future day spent
                // costs[idx] + dp[j] - check the price at the day when our pass ends
                dp[i] = Math.min(dp[i], costs[idx] + dp[j]);
                // navigate within costs array
                idx++;
            }
        }
        return dp[0];
    }
}