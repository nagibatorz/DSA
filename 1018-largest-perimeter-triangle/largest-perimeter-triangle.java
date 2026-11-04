// Using triangle property l1+l2>l3, where l3 is the biggest side
// we sort the array and always set l3 to the left moft element and move the window by 1 to check all combinations of 3
class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for(int i = nums.length - 1; i >= 2; i--){
            int l3 = nums[i];
            int l2 = nums[i - 1];
            int l1 = nums[i - 2];
            if(l1+l2 > l3){
                return l1 + l2 + l3;
            }
        }
        return 0;
    }
}