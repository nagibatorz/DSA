class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        // Sort the array to simplify skipping duplicates
        Arrays.sort(nums);
        bt(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void bt(int[] nums, List<List<Integer>> res, List<Integer> soFar, int idx){
        // add to subsets unconditionally since power set contains sets of all possible lengths
        res.add(new ArrayList<>(soFar));  
        
        for(int i = idx; i < nums.length; i++){
            //skip duplicates on the recursion level
            if(i > idx && nums[i-1] == nums[i]){
                continue;
            }

            soFar.add(nums[i]);
            //explore next element
            bt(nums, res, soFar, i + 1);
            soFar.remove(soFar.size() - 1);
        }
    }
}