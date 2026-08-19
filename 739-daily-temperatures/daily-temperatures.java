class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Deque<Integer> st = new ArrayDeque<>();
       int[] res = new int[n]; 
       for(int i = 0; i < n; i++){
            while(!st.isEmpty() && temperatures[i] > temperatures[st.peek()]){
                int curr = st.pop();
                res[curr] = i-curr;
            }
            st.push(i);
       }
       return res;
    }
}