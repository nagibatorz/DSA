// Binary Search Tree approach
class MyCalendar {
    Node root;

    // object is empty before any meetings have beem scheduled
    public MyCalendar() {
    }
    
    public boolean book(int startTime, int endTime) {
        if(root == null){
            root = new Node(startTime, endTime);
            return true;
        }else{
            // add meeting to a BST calendar
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

        // keep meetings that end early on the left and meetings that start late on the right
        public boolean insert(Node other){

            //look for a valid position in the bst and insert there
            // if there is overlap the recursion will hit false
            if(other.start >= this.end){

                //found a spot in right subtree
                if(this.right == null){
                    this.right = other;
                    return true;
                }
                return this.right.insert(other);
            } else if(other.end <= this.start){

                //found a spot in left subtree
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