class Solution { // DP with O(1) memory optimization
    public int rob(int[] nums) {
        // no need for circular array approach -> we just check what is max if we rob nums[0] and what is max if we don't rob nums[0];
        return Math.max(comp(nums, 0, nums.length - 1), comp(nums, 1, nums.length));
    }

    public int comp(int[] nums, int s, int e) {
        if(nums.length == 1) return nums[0];
        int n = nums.length;
        int rob1 = 0, rob2 = 0;
        
        for(int i = s; i < e; i++){
            //rob curr or skip
            int newRob = Math.max(rob2, rob1 + nums[i]);
            rob1 = rob2;
            rob2 = newRob;
        }
        return rob2;
    }
}