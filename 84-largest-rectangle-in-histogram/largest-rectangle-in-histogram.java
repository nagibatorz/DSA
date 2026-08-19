class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<int[]> st = new ArrayDeque<>();
        int n = heights.length;
        int max = 0;
        for(int i = 0; i < heights.length; i++){
            int start = i;
            while(!st.isEmpty() && heights[i] < st.peek()[1]){
                int[] curr = st.pop();
                max = Math.max(max, curr[1] * (i - curr[0]));
                start = curr[0];
            }
            st.push(new int[]{start, heights[i]});
        }
        while(!st.isEmpty()){
            int[] curr = st.pop();
            max = Math.max(max, curr[1] * (n - curr[0]));
        }
        return max;

    }
}
