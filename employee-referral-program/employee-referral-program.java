import java.util.*;
import java.math.*;

class Solution {
    // referrer -> list of referrals
    Map<String, List<String>> adj;

    // referrer -> computed count of referrals
    Map<String, Integer> memo = new HashMap<>();
    public List<String> empRefProgram(List<String> referrers, List<String> referrals) {
        // TODO: Implement empRefProgram logic
        adj = new HashMap<>();
        //populate adjacency map referrer -> list of referrals
        for(int i = 0; i < referrers.size(); i++){
            String referrer = referrers.get(i);
            String referral = referrals.get(i);
            adj.computeIfAbsent(referrer, k -> new ArrayList<>()).add(referral);
        }

        // sort based on count of referrals -> break tie aplhabetically
        // referrer -> count of referrals
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> !a.getValue().equals(b.getValue()) 
                ? b.getValue() - a.getValue() 
                : a.getKey().compareTo(b.getKey())
            );
        
        // compute counts for every referrer, iterate over key set to ensure unique referrers
        for(String referrer : adj.keySet()){
            int count = dfs(referrer);
            maxHeap.offer(Map.entry(referrer, count));
        }

        // construct list of top 3
        List<String> leaderboard = new ArrayList<>();
        for(int i = 0; i < 3 && !maxHeap.isEmpty(); i++){
            Map.Entry<String, Integer> referrEntry = maxHeap.poll();
            String score = referrEntry.getKey() + " " + referrEntry.getValue();
            leaderboard.add(score);
        }
        return leaderboard;
    }

    public int dfs(String referrer){
        // check if computed 
        if(memo.containsKey(referrer)){
            return memo.get(referrer);
        }
        int count = 0;
        // compute other referrals
        // empty list if a person never referred anyone
        for(String nei : adj.getOrDefault(referrer, List.of())){
            count += 1 + dfs(nei); //  count the neighbo and people referred by them
        }
        memo.put(referrer, count); //  memoize
        return count;
    }
}
