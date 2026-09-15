class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return 1;
        int n = nums.length;
        Arrays.sort(nums);

        int i = 0;
        int curr = nums[0];
        int streak = 0;
        int max = 0;
        while(i < n){
            if(nums[i] != curr){
                curr = nums[i];
                streak = 0;
            }
            while(i < n && nums[i] == curr){
                i++;
            }
            curr++;
            streak++;
            max = Math.max(max, streak);
        }
        return max;
    }
}