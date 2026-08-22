class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>[] dp = new ArrayList[target+1];
        for(int i = 0; i <= target; i++){
            dp[i] = new ArrayList<>();
        }

        dp[0].add(new ArrayList<>());
        for(int candidate : candidates){
            for(int t = candidate; t <= target; t++){
                for(List<Integer> prevComb : dp[t - candidate]){ //take an existing valid combination.
                    List<Integer> newComb = new ArrayList<>(prevComb);
                    //empty lists are prevented from being added
                    newComb.add(candidate);
                    dp[t].add(newComb);
                }
            }
        }
        return dp[target];
    }
}