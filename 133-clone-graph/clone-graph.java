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
        Queue<Node> q = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>();
        //add first node
        q.add(node);
        map.put(node, new Node(node.val));

        while(!q.isEmpty()){
            Node curr = q.remove();
            
            //complete neighbor list
            for(Node nei : curr.neighbors){
                //make sure the entry exists 
                if(!map.containsKey(nei)){
                    map.put(nei, new Node(nei.val));
                    q.add(nei);
                }

                //add neighbor to the list
                map.get(curr).neighbors.add(map.get(nei));
            }
        }
        return map.get(node);
    }
}