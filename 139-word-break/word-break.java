// Brute Force solution
/*
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> memo = new HashMap<>();
        return dp(s, wordDict, memo);
    }

    private boolean dp(String s, List<String> wordDict, Map<String, Boolean> memo){
        if(s.length() == 0) return true;
        for(String word : wordDict){
            if(s.indexOf(word) == 0){
                String suffix = s.substring(word.length());
                if(dp(suffix, wordDict, memo)){
                    return true;
                }
            }
        }
        return false;
    }
}
*/

//Dynamic Programming memoization approach
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> memo = new HashMap<>();
        return dp(memo, s, wordDict);
    }

    private boolean dp(Map<String, Boolean> memo, String s, List<String> wordDict){
        if(s.length() == 0) return true;
        if(memo.containsKey(s)) return memo.get(s);

        for(String w : wordDict){
            if(s.indexOf(w) == 0){
                String suffix = s.substring(w.length());
                if (dp(memo, suffix, wordDict)) {
                    memo.put(s, true);
                    return true;
                }
            }
        }
        memo.put(s, false);
        return false;
    }

    
}

