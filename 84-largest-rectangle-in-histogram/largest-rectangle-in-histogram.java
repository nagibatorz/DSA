class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<int[]> st = new ArrayDeque<>();
        int max = 0;

        for(int i = 0; i < n; i++){
            int start = i;

            while(!st.isEmpty() && heights[i] < st.peek()[0]){
                int[] curr = st.pop();
                // get area of the rectangle from i to the rectangles index
                max = Math.max(max, curr[0] * (i - curr[1]));
                // extend the rectabgle to the left
                start = curr[1];
            }
            st.push(new int[]{heights[i], start});
        }
        for(int[] curr : st){

            //get area of the rectangles remaining and extend right
            max = Math.max(max, curr[0] * (n - curr[1]));
        }
        return max;
    }
}
