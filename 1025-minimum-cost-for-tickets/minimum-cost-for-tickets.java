class Solution {
    public int mincostTickets(int[] days, int[] costs) {
    // Get the very last day we are traveling
    int lastDay = days[days.length - 1];
    
    // Fast O(1) lookup to check if a calendar day is a travel day
    boolean[] isTravelDay = new boolean[lastDay + 1];
    for (int day : days) {
        isTravelDay[day] = true;
    }

    // dp[i] will store the minimum cost to travel up to calendar day i
    int[] dp = new int[lastDay + 1];

    for (int i = 1; i <= lastDay; i++) {
        // If we aren't traveling today, the cost is exactly the same as yesterday
        if (!isTravelDay[i]) {
            dp[i] = dp[i - 1];
            continue;
        }

        // Compare buying a 1, 7, or 30 day pass ending on day i
        int cost1  = dp[i - 1] + costs[0];
        int cost7  = dp[Math.max(0, i - 7)] + costs[1];
        int cost30 = dp[Math.max(0, i - 30)] + costs[2];

        dp[i] = Math.min(cost1, Math.min(cost7, cost30));
    }

    return dp[lastDay];
}
}