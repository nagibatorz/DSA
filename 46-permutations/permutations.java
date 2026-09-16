// Backtracking
class Solution {
    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        if(nums.length == 0) return res;
        backtrack(new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private void backtrack(List<Integer> soFar, int[] nums, boolean[] vis){
        if(soFar.size() == nums.length){
            res.add(new ArrayList<>(soFar));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!vis[i]){//do not explore index that is already added
                soFar.add(nums[i]);
                vis[i] = true;
                backtrack(soFar, nums, vis);
                soFar.remove(soFar.size() - 1);
                vis[i] = false;
            }
        }
    }
}