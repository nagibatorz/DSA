// class Solution { //my take on Dynamic Programming solution
//     public List<List<Integer>> combinationSum(int[] candidates, int target) {
//         List<List<Integer>>[] dp = new ArrayList[target+1];
//         for(int i = 0; i <= target; i++){
//             dp[i] = new ArrayList<>();
//         }

//         dp[0].add(new ArrayList<>());
//         for(int candidate : candidates){
//             for(int t = candidate; t <= target; t++){
//                 for(List<Integer> prevComb : dp[t - candidate]){ //take an existing valid combination.
//                     List<Integer> newComb = new ArrayList<>(prevComb);
//                     //empty lists are prevented from being added
//                     newComb.add(candidate);
//                     dp[t].add(newComb);
//                 }
//             }
//         }
//         return dp[target];
//     }
// }

class Solution { // However Backtracking is more efficient for this problem
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        bt(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void bt(int[] candidates, int target, int start, List<Integer> soFar, List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(soFar));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            if(candidates[i] > target){
                break;
            }
            soFar.add(candidates[i]);
            bt(candidates, target - candidates[i], i, soFar, res);
            soFar.remove(soFar.size() - 1);
        }

    }
}