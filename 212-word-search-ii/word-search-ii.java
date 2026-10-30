class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length, m = board[0].length;
        TrieNode root = new TrieNode();
        for(String s : words){
            root.addWord(s);
        }
        List<String> res = new ArrayList<>();
        // explore from every cell
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // pass in current root so we can check next
                dfs(i, j, root, res, m, n, board);
            }
        }
        return res;
    }


    private void dfs(int i, int j, TrieNode curr, List<String> res, int m, int n, char[][] board){
        // boundary checks
        if(i >= n || i < 0 || j >= m || j < 0 || board[i][j] == '#'){
            return;
        }
        // read the character
        char c = board[i][j];
        // check whether the character appears after curr in any word after 
        if(curr.children[c - 'a'] == null){
            return;
        }
        // grab the node
        TrieNode next = curr.children[c - 'a'];

        // check whether it is an end of the word
        if(curr.children[c - 'a'].word != null){
            res.add(curr.children[c - 'a'].word);
            // prevent duplicate word discovery
            next.word = null;
        }
        
        // mark as visited and visit neighbors
        board[i][j] = '#';
        dfs(i + 1, j, next, res, m, n, board);
        dfs(i, j + 1, next, res, m, n, board);
        dfs(i - 1, j, next, res, m, n, board);
        dfs(i, j - 1, next, res, m, n, board);
        // Prune the Trie: if 'next' has no remaining words or children, cut it off
        boolean hasChildren = false;
        for (TrieNode child : next.children) {
            if (child != null) {
                hasChildren = true;
                break;
            }
        }
        // cut off the node because we already explore there and added the word.
        if (!hasChildren && next.word == null) {
            curr.children[c - 'a'] = null;
        }   
        // restore boards state
        board[i][j] = c;
        }   
    }

    class TrieNode{
        public TrieNode[] children;
        public String word;
        
        public TrieNode(){
            children = new TrieNode[26];
        }
        
        public void addWord(String word){
            TrieNode curr = this;
            // create subsequent letters as children of the current letter
            for(int i = 0; i < word.length(); i++){
                int idx = word.charAt(i) - 'a';
                if(curr.children[idx] == null){
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
            }
            // for the last character mark it as the end of the word by storing the word there
            // storing the word instead of boolean simplifies adding to the result list when we find word in board
            curr.word = word;
        }
    }
