// Dynamic Sliding Window + Frequency map approach
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        if(nums.length == 0) return 0;
        if(k > nums.length) return nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        int max = 0;
        int l = 0;
        for(int i = 0; i < nums.length; i++){
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            
            // if k frequency exceeded keep shrinking until the subarray is valid
            while(freq.get(nums[i]) > k){
                freq.put(nums[l], freq.get(nums[l]) - 1);
                l++;
            }
            max = Math.max(max, i - l + 1);
        }
        return max;
    }
}