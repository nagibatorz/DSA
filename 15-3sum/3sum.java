class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
       Arrays.sort(nums);
       List<List<Integer>> res = new ArrayList<>();
       int i = 0;
       int n = nums.length;
       while(i < n){
            if(nums[i] > 0) break;
           
            int l = i + 1, r = n - 1;
            while(l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == 0){
                    List<Integer> lst = new ArrayList<>();
                    lst.add(nums[i]);
                    lst.add(nums[l]);
                    lst.add(nums[r]);
                    res.add(lst);
                    r--;
                    l++;

                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if(sum > 0){
                    r--;
                } else{
                    l++;
                }
            }
            i++;
            while (i < n - 2 && nums[i] == nums[i - 1]) {
                i++;
            }
       }

       return res;
    }
}
