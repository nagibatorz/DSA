import java.util.*;
import java.math.*;

// refactor initial code into a follow up
class Solution {
    public List<Integer> findLinkedUsers(String[][] records, String[] weights, double threshold, int targetUserId) {
        // TODO: Implement findLinkedUsers logic
        // col name -> idx of col
        Map<String, Integer> nameToPos = new HashMap<>();
        for(int i = 0; i < records[0].length; i++){
            nameToPos.put(records[0][i], i);
        }

        //speed up by having two arrays, one for column ids in records and one for weights for given column
        // speeds up iteration in the helper method
        Map<String, Double> colToWeight = new HashMap<>();
        for(String s : weights){
            int idx = s.indexOf(":");
            String colName = s.substring(0, idx);
            double weight = Double.parseDouble(s.substring(idx+1));
            colToWeight.put(colName, weight);
        }
        int idIdx = nameToPos.get("id");
        // potentially drop this map
        Map<String, Integer> ids = new HashMap<>();
        for(int i = 1; i < records.length; i++){
            ids.put(records[i][idIdx], i);
        }
        if(!ids.containsKey(String.valueOf(targetUserId))) return new ArrayList<>();
        String[] target = records[ids.get(String.valueOf(targetUserId))];
        
        boolean[] vis = new boolean[records.length];
        vis[0] = true;
        vis[ids.get(String.valueOf(targetUserId))] = true;
        List<Integer> res = new ArrayList<>();
        Dequeue<Integer> q = new ArrayDeque<>();
        q.add(targetUserId);
        while(!q.isEmpty()){
            int id = q.remove();
            String[] curr = records[ids.get(String.valueOf(id))];
            for(int i = 1; i < records.length; i++){
                if(!vis[i] && isLinked(records[i], curr, threshold, colToWeight, nameToPos)){
                    vis[i] = true;
                    // if ids map is dropped make the q hold indices whithin records, still add the actual id to res list
                    q.add(Integer.parseInt(records[i][idIdx]));
                    res.add(Integer.parseInt(records[i][idIdx]));
                }
            }
        } 
        return res;
    }

    public boolean isLinked(String[] other, String[] target, double threshold, Map<String, Double> colToWeight, Map<String, Integer> nameToPos){
            double score = 0.0;
            for(Map.Entry<String, Double> rule : colToWeight.entrySet()){
                String field = rule.getKey();
                int fieldId = nameToPos.get(field);
                if(other[fieldId].equals(target[fieldId])){
                    score += rule.getValue();
                }
            }
            return score >= threshold;
    }
}
