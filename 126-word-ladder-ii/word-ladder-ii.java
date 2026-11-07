class Solution {
    Map<String, List<String>> dag;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // check if problem can be solved
        if(beginWord.length() != endWord.length()){
            return new ArrayList<>();
        }
        int len = beginWord.length();
        dag = new HashMap<>();
        Set<String> words = new HashSet<>(wordList);
        Set<String> currLayer = new HashSet<>();
        currLayer.add(beginWord);
        if(words.contains(beginWord)) words.remove(beginWord);
        boolean found = false;
        while(!currLayer.isEmpty() && !found){
            Set<String> nextLayer = new HashSet<>();
            for(String curr : currLayer){
                char[] carr = curr.toCharArray();
                for(int j = 0; j < len; j++){
                    char original = carr[j];
                    for(char c = 'a'; c <= 'z'; c++){
                        carr[j] = c;
                        String neighbor = new String(carr);
                        if(words.contains(neighbor)){
                            if(neighbor.equals(endWord)) found = true;
                            dag.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(curr);                    
                            nextLayer.add(neighbor);
                        }
                    }
                    carr[j] = original;
                }
            }
            words.removeAll(nextLayer);
            currLayer = nextLayer;
        }
        
        if(!found) return new ArrayList<>();

        List<List<String>> res = new ArrayList<>();
        List<String> start = new ArrayList<>();
        start.add(endWord);
        bt(endWord, res, start, beginWord);

        return res.isEmpty() ? new ArrayList<>() : res;
    }

    private void bt(String curr, List<List<String>> res, List<String> path, String beginWord){
        if(curr.equals(beginWord)){
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            res.add(validPath);
            return;
        }
        for(String s : dag.getOrDefault(curr, Collections.emptyList())){
            path.add(s);
            bt(s, res, path, beginWord);
            path.remove(path.size() - 1);
        }
    }
}



// Alphabet Probing - MLE, TLE

// class Solution {
//     public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//         // check if problem can be solved
//         if(beginWord.length() != endWord.length()){
//             return new ArrayList<>();
//         }

//         Set<String> vis = new HashSet<>();
//         Deque<List<String>> q = new ArrayDeque<>();
//         List<String> lst = new ArrayList<>();
//         Set<String> wordSet = new HashSet<>(wordList);
//         if(!wordSet.contains(endWord)) return new ArrayList<>();

//         lst.add(beginWord);
//         q.add(lst);
//         vis.add(beginWord);
//         List<List<String>> res = new ArrayList<>();
//         while(!q.isEmpty()){
//             int size = q.size();
//             Set<String> layerVis = new HashSet<>();
//             for(int i = 0; i < size; i++){
//                 List<String> curr = q.poll();
//                 String currWord = curr.get(curr.size() - 1);
//                 if(currWord.equals(endWord)){
//                     res.add(curr);
//                     continue;
//                 }
//                 char[] chars = currWord.toCharArray();
//                 for (int j = 0; j < chars.length; j++) {
//                     char original = chars[j];
//                     for (char c = 'a'; c <= 'z'; c++) {
//                         if (c == original) continue;
//                         chars[j] = c;
//                         String neighbor = new String(chars);
//                         if (wordSet.contains(neighbor) && !vis.contains(neighbor)) {
//                             List<String> newLst = new ArrayList<>(curr);
//                             newLst.add(neighbor);
//                             q.add(newLst);
//                             layerVis.add(neighbor);
//                         }
//                     }
//                     chars[j] = original; // Backtrack
//                 }
//             }
//             if(!res.isEmpty()) return res;
//             vis.addAll(layerVis);
//         }
//         return new ArrayList<>();
//     }
// }