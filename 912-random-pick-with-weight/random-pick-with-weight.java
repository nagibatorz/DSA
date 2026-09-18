class Solution {

    private double[] p;
    private int[] weights;
    private Random rand = new Random();

    public Solution(int[] w) {
        this.weights = w;
        int n = weights.length;
        this.p = new double[n];

        double sum = 0; //prevent truncation to 0

        for(int i = 0; i < n; i++) sum += weights[i]; //calculate sum

        p[0] = (double)(weights[0] / sum);//fence post

        for(int i = 1; i < n; i++){
            p[i] = ((double)(weights[i] / sum)) + p[i-1]; //populate ranges of probabilities of picking i
        }
    }
    

    public int pickIndex() {
        // pick a probability -> run binary search and find a probability that it falls under
        double chance = rand.nextDouble(); // [0.0, 1.0] by default
        int l = 0, r = weights.length - 1;
        int mid = 0;
        while(l < r){
            mid = l + (r - l) / 2;
            if(p[mid] < chance){ //exceeds p[mid] -> mid cannot be potential answer
                l = mid + 1;
            }else {
                r = mid; //found valid upper bound but there might be tighter bound
            }
        }
        // the tighest bound is at both l and r
        return l;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */