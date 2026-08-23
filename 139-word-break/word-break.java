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
        return dp(s, wordDict, memo);
    }

    private boolean dp(String s, List<String> wordDict, Map<String, Boolean> memo){
        if(s.length() == 0) return true;
        // check if the answer was previously computed
        if(memo.containsKey(s)) return memo.get(s);
        for(String word : wordDict){
            if(s.indexOf(word) == 0){ //check if word is a prefix of s
                // recurse on the rest
                String suffix = s.substring(word.length()); 
                if(dp(suffix, wordDict, memo)){
                    memo.put(s, true);
                    return true;
                }
            }
        }
        memo.put(s, false);
        return false;
    }
}

