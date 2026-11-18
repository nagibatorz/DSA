class Solution {
    public long putMarbles(int[] weights, int k) {
        if(weights.length == k) return 0;

        // calculate the sum of each split
        List<Integer> splits = new ArrayList<>();
        for(int i = 0; i < weights.length - 1; i++){
            splits.add(weights[i+1] + weights[i]);
        }

        // sort splits
        Collections.sort(splits);

        // calculate minimum and maximum splits and return diff
        long minScore = 0;
        long maxScore = 0;
        for(int i = 0; i < k - 1; i++){
            minScore += splits.get(i);
        }
        for(int i = splits.size() - 1; i > splits.size() - k; i--){
            maxScore += splits.get(i);
        }
        return maxScore - minScore;
    }
}