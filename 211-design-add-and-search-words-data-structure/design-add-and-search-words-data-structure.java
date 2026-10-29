class WordDictionary {

    TrieNode root;
    boolean[] hasLength;

    public WordDictionary() {
        root = new TrieNode();
        hasLength = new boolean[505];
    }

    public void addWord(String word) {
        TrieNode curr = root;
        hasLength[word.length()] = true;
        for(int i = 0; i < word.length(); i++){
            int trie = word.charAt(i) - 'a'; // ASCII conversions act as indexes therefore we know what char's TrieNode exists
            if(curr.children[trie] == null){ 
                curr.children[trie] = new TrieNode();
            }
            curr = curr.children[trie];
        }
        curr.endOfWord = true;
    }

    public boolean search(String word) {
        if(!hasLength[word.length()]) return false;
        return dfs(0, word.toCharArray(), root);
    }

    // method consists of recursive and iterative portion 
    // any method call can enter any portion depending on what current char is
    private boolean dfs(int idx, char[] word, TrieNode node){
        TrieNode curr = node;
        for(int i = idx; i < word.length; i++){
            char c = word[i];
            // recursive portion
            if(c == '.'){
                for(TrieNode child : curr.children){
                    if(child != null && dfs(i + 1, word, child)){
                        return true;
                    }
                }
                return false;
            // iterative portion
            } else{
                int trie = c - 'a';
                if(curr.children[trie] == null){
                    return false;
                }
                curr = curr.children[trie];
            }
        }
        return curr.endOfWord;
    }

    public class TrieNode{
        boolean endOfWord = false;
        TrieNode[] children = new TrieNode[26];
    }
}
