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

        // sort based on position and iterate starting from the farthest
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        for(int i = n - 1; i >= 0; i--){
            double time = (double) (target - arr[i][0]) / arr[i][1];
            if(st.isEmpty() || st.peek() < time){ //push on the stack if not a fleet
                st.push(time);
            }
        }

        //stack has the number of fleets
        return st.size();
    }
}
