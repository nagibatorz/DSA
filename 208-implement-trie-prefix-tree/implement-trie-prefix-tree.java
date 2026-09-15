class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            curr.leafs.putIfAbsent(c, new TrieNode());
            curr = curr.leafs.get(c);
        }
        curr.endOfWord = true;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(!curr.leafs.containsKey(c)){
                return false;
            }
            curr = curr.leafs.get(c);
        }
        return curr.endOfWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for(char c : prefix.toCharArray()){
            if(!curr.leafs.containsKey(c)){
                return false;
            }
            curr = curr.leafs.get(c);
        }
        return true;
    }


    class TrieNode{
        private boolean endOfWord = false;
        private Map<Character, TrieNode> leafs = new HashMap<>();
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */