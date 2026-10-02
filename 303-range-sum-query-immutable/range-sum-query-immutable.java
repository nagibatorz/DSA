//Store prefix sums
class NumArray {

    private int[] pre;
    public NumArray(int[] nums) {
        this.pre = new int[nums.length+1];
        //calculate prefic sum for each index
        for(int i = 0; i < nums.length; i++){
            pre[i+1] = pre[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        //in order to efficiently return sum from left to right we return the difference in prefix sums
        return pre[right+1] - pre[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */