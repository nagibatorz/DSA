class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, 0, new ArrayList<>(), nums.length);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, int i, List<Integer> soFar, int n){
        if(i >= n){
            res.add(new ArrayList<>(soFar));
            return;
        }
        dfs(nums, res, i + 1, soFar, n);
        soFar.add(nums[i]);
        dfs(nums, res, i + 1, soFar, n);
        soFar.remove(soFar.size() - 1);
    }
}