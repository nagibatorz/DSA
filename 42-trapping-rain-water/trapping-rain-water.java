class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int leftMax = height[0], rightMax = height[n-1];
        int trappedWater = 0;
        while(l < r){

            rightMax = Math.max(rightMax, height[r]);
            leftMax = Math.max(leftMax, height[l]);
            
            if(leftMax > rightMax){
                
                int water = rightMax - height[r];
                trappedWater += water;
                r--;
            } else{
                
                int water = leftMax - height[l];
                trappedWater += water;
                l++;
            }
        }
        return trappedWater;
    }
}
