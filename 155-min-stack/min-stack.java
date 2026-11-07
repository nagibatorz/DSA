class MinStack {

    Deque<Integer> st;
    Deque<Integer> mins;

    public MinStack() {
        st = new ArrayDeque<>();
        mins = new ArrayDeque<>();
    }
    
    public void push(int val) {
        st.push(val);
        if(mins.isEmpty() || mins.peek() > val){
            mins.push(val);
        } else{
            mins.push(mins.peek());
        }
    }
    
    public void pop() {
        st.pop();
        mins.pop();
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }
}