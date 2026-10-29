class WordDictionary {

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(curr.children[c-'a'] == null){ // ASCII conversions act as indexes therefore we know what char's TrieNode exists
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
        }
        curr.endOfWord = true;
    }

    public boolean search(String word) {
        return dfs(0, word, root);
    }

    // method consists of recursive and iterative portion 
    // any method call can enter any portion depending on what current char is
    private boolean dfs(int idx, String word, TrieNode node){
        TrieNode curr = node;
        for(int i = idx; i < word.length(); i++){
            char c = word.charAt(i);
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
                if(curr.children[c - 'a'] == null){
                    return false;
                }
                curr = curr.children[c - 'a'];
            }
        }
        return curr.endOfWord;
    }

    public class TrieNode{
        boolean endOfWord = false;
        TrieNode[] children = new TrieNode[26];
    }
}
