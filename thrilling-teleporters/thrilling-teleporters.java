import java.util.*;
import java.math.*;

class Solution {
    public List<Integer> destinations(String[] teleporters, int dieSides, int currentSquare, int lastSquare) {
        // TODO: Implement destinations logic

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> res = new HashSet<>();
        for(String teleport : teleporters){
            String[] cells = teleport.split(",");
            int from = Integer.parseInt(cells[0]);
            int to = Integer.parseInt(cells[1]);
            map.put(from, to);
        }
        for(int i = 1; i <= dieSides; i++){
            int newCurr = currentSquare + i;
            if(newCurr >= lastSquare){
                res.add(lastSquare);
                break;
            }
            if(map.containsKey(newCurr)){
                res.add(map.get(newCurr));
            } else{
                res.add(newCurr);
            }
        } 
        return new ArrayList<>(res);
    }
};
