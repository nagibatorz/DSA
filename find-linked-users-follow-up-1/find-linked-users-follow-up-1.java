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

        Map<String, Double> colToWeight = new HashMap<>();
        for(String s : weights){
            int idx = s.indexOf(":");
            String colName = s.substring(0, idx);
            double weight = Double.parseDouble(s.substring(idx+1));
            colToWeight.put(colName, weight);
        }
        int idIdx = nameToPos.get("id");
        Map<String, Integer> ids = new HashMap<>();
        for(int i = 1; i < records.length; i++){
            ids.put(records[i][idIdx], i);
        }
        if(!ids.containsKey(String.valueOf(targetUserId))) return new ArrayList<>();
        String[] target = records[ids.get(String.valueOf(targetUserId))];
        List<String[]> direct = new ArrayList<>();
        for(int i = 1; i < records.length; i++){
            int id = Integer.parseInt(records[i][idIdx]);
            if(id == targetUserId) continue;
            if(isLinked(records[i], target, threshold, colToWeight, nameToPos)){
                direct.add(records[i]);
            }
        }
        Set<Integer> res = new HashSet<>();
        for(String[] curr : direct){
            int currId = Integer.parseInt(curr[idIdx]);
            res.add(currId);
            for(int i = 1; i < records.length; i++){
                int id = Integer.parseInt(records[i][idIdx]);
                if(id == targetUserId || id == currId) continue;
                if(isLinked(records[i], curr, threshold, colToWeight, nameToPos)){
                    res.add(id);
                }
            }
        }
        return new ArrayList<>(res);
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
