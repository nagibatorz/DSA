import java.util.*;
import java.math.*;

/**
 * Solution
sort the input based on first index in ascending order

iterate thru entries per one entry in output we iterate thru input while timestamp <= currTimeStamp + 10

per unit 10 we keep track of min, max,; capture opening and closing before and after while loop iterations; timestamps are just multiples of 10
 */
class Solution {
    public List<List<Integer>> getCandlesticks(List<List<Integer>> timedPrices) {
        // TODO: Implement getCandlesticks logic
        timedPrices.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));
        List<List<Integer>> res = new ArrayList<>();

        int idx = 0;
        int prevBucket = -1;
        int prevClose = 0;

        while(idx < timedPrices.size()){
            int bucket = timedPrices.get(idx).get(0) / 10;

            if(prevBucket != -1){
                for(int i = prevBucket + 1; i < bucket; i++){
                    res.add(Arrays.asList(i * 10, prevClose, prevClose, prevClose, prevClose));
                }
            }

            int open = timedPrices.get(idx).get(1);
            int close = open, min = open, max = open;
            while(idx < timedPrices.size() && timedPrices.get(idx).get(0) / 10 == bucket){
                int price = timedPrices.get(idx).get(1);
                max = Math.max(max, price);
                min = Math.min(min, price);
                close = price;
                idx++;
            }
            res.add(Arrays.asList(bucket * 10, max, min, open, close));
            prevBucket = bucket;
            prevClose = close;
        }
        return res;
     }
}
