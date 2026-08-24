class LRUCache {

    final Map<Integer, Node> cache;
    final int capacity;
    final Node head;
    final Node tail;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        // connect doubly pointers
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        int res = -1;
        Node node = cache.get(key);
        if(node != null){
            remove(node);
            add(node);
            res = node.val;
        }
        return res;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){ //map contains key -- still mark as most recently used
            Node node = cache.get(key);
            remove(node);
        } else if(cache.size() == capacity){ //remove least recently used
            cache.remove(tail.prev.key);
            remove(tail.prev);
        }
        Node newNode = new Node(value, key);
        cache.put(key, newNode);
        add(newNode);
    }

    // These are crucial for simplifying the problem
    // P.S. we always add to the front, but we might remove any node if we are updating key, value pair
    private void remove(Node node){
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void add(Node node){
        Node headNext = head.next;
        node.prev = head;
        head.next = node;
        headNext.prev = node;
        node.next = headNext;
    }

    class Node{
        int val;
        int key;
        Node prev;
        Node next;

        public Node(int val, int key){
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