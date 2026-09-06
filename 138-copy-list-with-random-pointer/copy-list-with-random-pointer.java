/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
// HashMap one pass and placeholder technique
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        map.put(null, null);//handles when Node points to null case
        Node curr = head;

        // map.put(curr, new Node(0)); places a placeholde there
        while(curr != null){
            if(!map.containsKey(curr)){
                map.put(curr, new Node(0));
            }
            map.get(curr).val = curr.val;
            if(!map.containsKey(curr.next)){
                map.put(curr.next, new Node(0));
            }
            // set the pointer to either a placeholder or needed node
            map.get(curr).next = map.get(curr.next);
            if(!map.containsKey(curr.random)){
                map.put(curr.random, new Node(0));
            }
            // set the pointer to either a placeholder or needed node
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;

        } 
        return map.get(head);
    }
}