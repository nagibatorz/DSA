class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = 0;
        //array of size number of windows
        // n - k + 1 = number of windows
        int[] res = new int[n - k + 1];
        Deque<Integer> q = new LinkedList<>();

        while(r < n){
            //keep the biggest in front of the q
            while(!q.isEmpty() && nums[q.getLast()] < nums[r]){
                q.removeLast();
            }
            //add the element to the q
            q.addLast(r);

            //if the greatest element is at l then we pop
            if(l > q.getFirst()){
                q.removeFirst();
            }

            //add to res and increment l only when we hit the window of correct size
            if((r + 1) >= k){
                res[l] = nums[q.getFirst()];
                l++;
            }
            r++;
        }
        return res;
    }
}