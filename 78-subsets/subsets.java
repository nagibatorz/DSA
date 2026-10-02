// Backtracking approach
// Time: O(n * 2^n)
// Space: O(2^n)
class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //keep track of index to be added
        dfs(nums, res, 0, new ArrayList<>(), nums.length);
        return res;
    }

    // this method will branch out to all subsets in the base cases
    private void dfs(int[] nums, List<List<Integer>> res, int i, List<Integer> soFar, int n){
        if(i >= n){
            res.add(new ArrayList<>(soFar));
            return;
        }
        //explore the tree without nums[i] added
        dfs(nums, res, i + 1, soFar, n);

        soFar.add(nums[i]);

        //explore tree with the element added
        dfs(nums, res, i + 1, soFar, n);

        soFar.remove(soFar.size() - 1);
    }
}