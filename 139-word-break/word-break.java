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

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> memo = new HashMap<>();
        return dp(s, wordDict, memo);
    }

    private boolean dp(String s, List<String> wordDict, Map<String, Boolean> memo){
        if(s.length() == 0) return true;
        if(memo.containsKey(s)) return memo.get(s);
        for(String word : wordDict){
            if(s.indexOf(word) == 0){
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

