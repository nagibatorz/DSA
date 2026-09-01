class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> st = new ArrayDeque<>();
        for(String token : tokens){
            if(token.equals("+")){
                st.push(st.pop() + st.pop());
            } else if(token.equals("-")){
                int temp = st.pop();
                st.push(st.pop() - temp);
            } else if(token.equals("*")){
                st.push(st.pop() * st.pop());
            } else if(token.equals("/")){
                int temp = st.pop();
                st.push(st.pop() / temp);
            } else{
                st.push(Integer.parseInt(token));
            }
        }
        return st.pop();
    }
}