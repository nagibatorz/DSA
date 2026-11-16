// Two pointers and a traveling pointer
class Solution {
    public void sortColors(int[] nums) {
        if(nums.length == 1) return;
        // l - where to insert 0's
        // r - where to insert 2's
        // i - scans elements
        int l = 0, i = 0, r = nums.length - 1;
        while(i <= r){
            // swap to 0 and increment because we already scanned everything behind i
            if(nums[i] == 0){
                nums[i] = nums[l];
                nums[l] = 0;
                l++;
                i++;
            // swap but do not increment because there might be a zero on the next iteration
            } else if(nums[i] == 2){
                nums[i] = nums[r];
                nums[r] = 2;
                r--;
            // it is a 1 we increment just i
            } else{
                i++;
            }
            
        }
    }
}