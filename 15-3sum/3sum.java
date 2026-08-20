class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
       int n = nums.length;
       Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
       for(int i = 0; i < n; i++){
        if(nums[i] > 0) break; //exit early
        if(i > 0 && nums[i] == nums[i-1]){
            continue;
        }
        int l = i + 1, r = n -1;
        while(l < r){ //not binary search
            int sum = nums[i] + nums[l] + nums[r];
            if(sum > 0){
                r--;
            } else if(sum < 0){
                l++;
            } else{
                res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                l++;
                r--;
                //keep incrementing until numbers are distinct
                while(l < r && nums[l] == nums[l - 1]){
                    l++;
                }
            }
        }
       }
       return res;
    }
}
