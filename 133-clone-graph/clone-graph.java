/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        // map of original nodes to clone
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        map.put(node, new Node(node.val));

        while(!q.isEmpty()){
            Node curr = q.remove();
            for(Node nei : curr.neighbors){
                if(!map.containsKey(nei)){
                    //clone when we encounter an unseen node
                    map.put(nei, new Node(nei.val));
                    q.add(nei);
                }
                // add to the neighbors of the copy
                map.get(curr).neighbors.add(map.get(nei));
            }
        }
        return map.get(node);
    }
}