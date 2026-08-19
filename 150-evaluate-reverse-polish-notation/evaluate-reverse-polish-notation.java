class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> st = new ArrayDeque<>();
        for(String s : tokens){
            if(s.equals("+")){
                st.push(st.pop() + st.pop());
            } else if(s.equals("-")){
                int b = st.pop();
                int a = st.pop();
                st.push(a - b);
            }
            else if(s.equals("*")){
                st.push(st.pop() * st.pop());
            }else if(s.equals("/")){
                int b = st.pop();
                int a = st.pop();
                st.push(a / b);
            } else{
                st.push(Integer.parseInt(s));
            } 
        }
        return st.pop();
    }
}