class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord) || beginWord.equals(endWord)) return 0;
        Map<String, List<String>> adj = new HashMap<>();
        int len = beginWord.length();
        wordList.add(beginWord);

        for(String w : wordList){
            for(int j = 0; j < len; j++){
                String pattern = w.substring(0, j) + "*" + w.substring(j+1);
                adj.computeIfAbsent(pattern, k -> new ArrayList<>()).add(w);
            }
        }

        Set<String> vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        int res = 1;
        q.add(beginWord);
        vis.add(beginWord);
        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                String w = q.poll();
                if(w.equals(endWord)){
                    return res;
                }
                for(int j = 0; j < len; j++){
                    String pattern = w.substring(0, j) + "*" + w.substring(j+1);
                    for(String nei : adj.get(pattern)){
                        if(!vis.contains(nei)){
                            vis.add(nei);
                            q.offer(nei);
                        }
                    }
                }
            }
            res++;
        }
        return 0;
    }
}