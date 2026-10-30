class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length, m = board[0].length;
        TrieNode root = new TrieNode();
        for(String s : words){
            root.addWord(s);
        }
        List<String> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dfs(i, j, root, res, m, n, board);
            }
        }
        return res;
    }


    private void dfs(int i, int j, TrieNode curr, List<String> res, int m, int n, char[][] board){
        if(i >= n || i < 0 || j >= m || j < 0 || board[i][j] == '#'){
            return;
        }
        char c = board[i][j];
        if(curr.children[c - 'a'] == null){
            return;
        }
        TrieNode next = curr.children[c - 'a'];
        if(curr.children[c - 'a'].word != null){
            res.add(curr.children[c - 'a'].word);
            next.word = null;
        }
        
        board[i][j] = '#';
        dfs(i + 1, j, next, res, m, n, board);
        dfs(i, j + 1, next, res, m, n, board);
        dfs(i - 1, j, next, res, m, n, board);
        dfs(i, j - 1, next, res, m, n, board);
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
            for(int i = 0; i < word.length(); i++){
                int idx = word.charAt(i) - 'a';
                if(curr.children[idx] == null){
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
            }
            curr.word = word;
        }
    }
