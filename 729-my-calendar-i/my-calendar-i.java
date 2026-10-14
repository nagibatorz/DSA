class MyCalendar {
    Node root;

    public MyCalendar() {
    }
    
    public boolean book(int startTime, int endTime) {
        if(root == null){
            root = new Node(startTime, endTime);
            return true;
        }else{
            return root.insert(new Node(startTime, endTime));
        }
    }

    class Node{
        Node left;
        Node right;
        int start;
        int end;

        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }

        public boolean insert(Node other){
            if(other.start >= this.end){
                if(this.right == null){
                    this.right = other;
                    return true;
                }
                return this.right.insert(other);
            } else if(other.end <= this.start){
                if(this.left == null){
                    this.left = other;
                    return true;
                }
                return this.left.insert(other);
            }
            return false;
        }
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(startTime,endTime);
 */