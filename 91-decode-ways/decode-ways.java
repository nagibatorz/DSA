class Solution { // Bottom-Up DP with optimized space complexity
    public int numDecodings(String s) {
        int dp = 0, dp2 = 0;
        int dp1 = 1;
        int len = s.length();
        for(int i = len-1; i >= 0; i--){
            if(s.charAt(i) == '0'){
                dp = 0; // 0 ways to decode
            } else{
                dp = dp1; // move one char
                if(i + 1 < len && (s.charAt(i) == '1' ||(s.charAt(i) == '2' && s.charAt(i+1) < '7'))){
                    dp += dp2; // move two chars
                }
            }
            dp2 = dp1;
            dp1 = dp;
            dp = 0;
        }
        return dp1;
    }
}