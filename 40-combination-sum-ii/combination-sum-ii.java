class Solution { // However Backtracking is more efficient for this problem
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            // Skip duplicates on the same level (horizontal)
            // We use 'i > start' to ensure we can still use duplicates moving down a path (vertical)
            if(i > start && candidates[i] == candidates[i-1]){
                continue;
            }
            // Use choose -> explore -> unchoose pattern
            soFar.add(candidates[i]);
            bt(candidates, target - candidates[i], i + 1, soFar, res);
            soFar.remove(soFar.size() - 1);
        }

    }
}
