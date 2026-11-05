class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.length() != endWord.length()){
            return 0;
        }
        wordList.add(beginWord);
        int len = beginWord.length();
        boolean present = false;
        Map<String, List<String>> adj = new HashMap<>();
        for(String word : wordList){
            if(word.equals(endWord)) present = true;
            for(int i = 0; i < len; i++){
                String key = word.substring(0, i) + "*" + word.substring(i + 1, len);
                adj.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
            }
        }
        if(!present) return 0;
        Deque<String> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        q.add(beginWord);
        vis.add(beginWord);
        int res = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String curr = q.remove();
                if(curr.equals(endWord)) return res;
                for(int j = 0; j < len; j++){
                    String key = curr.substring(0, j) + "*" + curr.substring(j + 1, len);
                    for(String nei : adj.get(key)){
                        if(!vis.contains(nei)){
                            vis.add(nei);
                            q.add(nei);
                        }
                    }
                }
            }
            res++;
        }
        return 0;
    }
}