class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        if(position.length == 0) return 0;
        int n = position.length;
        Deque<Double> st = new ArrayDeque<>();
        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++){
            arr[i][0] = position[i];
            arr[i][1] = speed[i];
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        st.push((double)(target - arr[n-1][0]) / arr[n-1][1]);
        for(int i = n - 2; i >= 0; i--){
            double time = (double) (target - arr[i][0]) / arr[i][1];
            if(!st.isEmpty() && st.peek() < time){
                st.push(time);
            }
        }
        return st.size();
    }
}
