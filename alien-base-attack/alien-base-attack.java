import java.util.*;
import java.math.*;

class AlienBaseAttack {
    private Map<Integer, double[]> remaining;
    public AlienBaseAttack(double base) {
        // TODO: Initialize AlienBaseAttack
        remaining = new HashMap<>();
        for(int i = 0; i < base; i++){
            remaining.put(i, new double[]{i, i+1});
        }
    }

    public void attack(double x) {
        // TODO: Implement attack logic
        int tail = (int) x;
        if(remaining.containsKey(tail)){
            double[] range = remaining.get(tail);
            double start = range[0], end = range[1];
            if(x <= start){
                remaining.remove(tail);
            } else if(x < end){
                remaining.put(tail, new double[]{start, x});
            }
        }

        int head = tail + 1;
        if(remaining.containsKey(head)){
            double[] range = remaining.get(head);
            double start = range[0], end = range[1];
            double newStart = x + 1.0;
            if(newStart >= end){
                remaining.remove(head);
            } else if(newStart > start){
                remaining.put(head, new double[]{newStart, end});
            }
        }
     }

    public boolean isGameOver() {
        // TODO: Implement isGameOver logic
        return remaining.size() == 0;
     }
}
