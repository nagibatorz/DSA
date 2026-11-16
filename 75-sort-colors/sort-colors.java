class Solution {
    public void sortColors(int[] nums) {
        if(nums.length == 1) return;
        int l = 0, i = 0, r = nums.length - 1;
        while(i <= r){
            if(nums[i] == 0){
                nums[i] = nums[l];
                nums[l] = 0;
                l++;
                i++;
            } else if(nums[i] == 2){
                nums[i] = nums[r];
                nums[r] = 2;
                r--;
            } else{
                i++;
            }
            
        }
    }
}