class LRUCache {
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int capacity;



   
    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        int val = -1;
        if(cache.containsKey(key)){
            Node valNode = cache.get(key);
            val = valNode.val;

            // bump recentness
            remove(valNode);
            add(valNode);
        }
        return val;
    }
    
    public void put(int key, int value) {
        Node node;
        if(cache.containsKey(key)){ // key exists -> just change the value
            node = cache.get(key);
            node.val = value;
            remove(node); // remove from the list
        } else{
            // cache only growing here
            if(cache.size() == capacity){
                cache.remove(tail.prev.key);
                remove(tail.prev);
            }
            node = new Node(key, value);
            cache.put(key, node);
        }
        // bump up recentness / add to the list
        add(node);
    }

    public void add(Node node){
        // add node to head
        Node temp = head.next;
        head.next = node;
        temp.prev = node;
        node.prev = head;
        node.next = temp;
    }

    public void remove(Node node){
        // set pointers to skip given node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    class Node{
        int key; // store key for deletion from map
        int val;
        Node prev;
        Node next;
        public Node(int key, int val){
            this.val = val;
            this.key = key;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */