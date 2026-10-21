import java.util.*;
import java.math.*;

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

        List<Integer> res = new ArrayList<>();
        for(int i = 1; i < records.length; i++){
            int id = Integer.parseInt(records[i][idIdx]);
            if(id == targetUserId) continue;
            double score = 0.0;
            for(Map.Entry<String, Double> rule : colToWeight.entrySet()){
                String field = rule.getKey();
                int fieldId = nameToPos.get(field);
                if(records[i][fieldId].equals(target[fieldId])){
                    score += rule.getValue();
                }
            }
            if(score >= threshold) res.add(id);
        }
        return res;
    }
}
