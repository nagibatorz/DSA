// Two pointers
class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int leftMax = 0, rightMax = height[r];
        int trappedWater = 0;
        
        while (l < r) {

            // We need to find bottleneck side -> compute how much water is trapped at that smaller side

            if (height[l] <= height[r]) {
                // minimize number of Math.max operations
                if (height[l] >= leftMax) {
                    leftMax = height[l]; // Update max
                } else {
                    trappedWater += leftMax - height[l]; // Trap water
                }
                l++;
            } else {
                if (height[r] >= rightMax) {
                    rightMax = height[r]; // Update max
                } else {
                    trappedWater += rightMax - height[r]; // Trap water
                }
                r--;
            }
        }
        return trappedWater;
    }
}