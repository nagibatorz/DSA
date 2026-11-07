// Layer by layer BFS, bipartite graph
// Wild card String as keys; * - wild card
// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         // check if problem can be solved
//         if(beginWord.length() != endWord.length()){
//             return 0;
//         }

//         // problem states that beginword might not be in the wordList but we need to build out its combinations with * too
//         wordList.add(beginWord);
//         int len = beginWord.length();
//         boolean present = false;
//         // <string with wildcard> = <possible strings that can be generated from that wildcard>
//         Map<String, List<String>> adj = new HashMap<>();
//         // build out adjacency list
//         for(String word : wordList){
//             if(word.equals(endWord)) present = true;
//             for(int i = 0; i < len; i++){
//                 // put * at each position of the string 
//                 String key = word.substring(0, i) + "*" + word.substring(i + 1, len);
//                 adj.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
//             }
//         }
//         if(!present) return 0;
//         Deque<String> q = new ArrayDeque<>();
//         Set<String> vis = new HashSet<>();
//         // start with word
//         q.add(beginWord);
//         vis.add(beginWord);
//         int res = 1;

//         while(!q.isEmpty()){
//             int size = q.size();
            
//             // pop layer-by-layer, makes the q easier to handle
//             for(int i = 0; i < size; i++){
//                 // get word
//                 String curr = q.remove();
//                 if(curr.equals(endWord)) return res;
//                 // generate pattern -> get children of that pattern -> 
//                 // -> loop thru every child -> push unvisited 
//                 for(int j = 0; j < len; j++){
//                     String key = curr.substring(0, j) + "*" + curr.substring(j + 1, len);
//                     List<String> neighbors = adj.remove(key);  // the pattern is explored -> remove
//                     if(neighbors != null){
//                         for(String nei : neighbors){
//                             if(!vis.contains(nei)){ //  same word can be in differnet patterns so we need to prevent duplicate processing of words too
//                                 vis.add(nei);
//                                 q.add(nei);
//                             }
//                         }
//                     }
//                 }
//             }
//             res++;
//         }
//         return 0;
//     }
// }

// Alphabet Probing Optimization
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // check if problem can be solved
        if(beginWord.length() != endWord.length()){
            return 0;
        }

        Set<String> vis = new HashSet<>();
        Deque<String> q = new ArrayDeque<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) return 0;
        q.add(beginWord);
        int res = 1;
        vis.add(beginWord);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String curr = q.poll();
                if(curr.equals(endWord)){
                   return res;
                }
                char[] chars = curr.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[j] = c;
                        String neighbor = new String(chars);
                        if (wordSet.contains(neighbor) && !vis.contains(neighbor)) {
                            if(!vis.contains(neighbor)){
                                q.add(neighbor);
                                vis.add(neighbor);
                            }
                        }
                    }
                    chars[j] = original; // Backtrack
                }
            }
            res++;
        }
        return 0;
    }
}