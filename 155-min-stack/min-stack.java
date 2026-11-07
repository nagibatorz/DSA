class MinStack {
    private Deque<Integer> st;
    private Deque<Integer> min;

    public MinStack() {
        st = new ArrayDeque<>();
        min = new ArrayDeque<>();
    }
    
    public void push(int val) {
        st.push(val);
        if(min.isEmpty() || val <= min.peek()){
            min.push(val);
        }
    }
    
    public void pop() {
        if(st.isEmpty()) return;
        int top = st.pop();
        if(top == min.peek()){
            min.pop();
        }

    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}
