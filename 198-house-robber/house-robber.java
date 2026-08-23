// House robber serves as almost like a template DP problem
class Solution { 
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int n = nums.length;
        int rob1 = 0, rob2 = 0;
        
        for(int i = 0; i < n; i++){
            //rob curr or skip
            int newRob = Math.max(rob2, rob1 + nums[i]);
            rob1 = rob2;
            rob2 = newRob;
        }
        return rob2;
    }
}