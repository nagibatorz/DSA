class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int leftMax = height[0], rightMax = height[n-1];
        int trappedWater = 0;
        while(l < r){

            
            
            
            if(height[l] > height[r]){
                rightMax = Math.max(rightMax, height[r]);
                int water = rightMax - height[r];
                trappedWater += water;
                r--;
            } else{
                leftMax = Math.max(leftMax, height[l]);
                int water = leftMax - height[l];
                trappedWater += water;
                l++;
            }
        }
        return trappedWater;
    }
}
