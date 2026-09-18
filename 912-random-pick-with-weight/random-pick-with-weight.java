class Solution {

    private double[] p;
    private int[] weights;
    private Random rand = new Random();

    public Solution(int[] w) {
        this.weights = w;
        int n = weights.length;
        this.p = new double[n];
        double sum = 0;
        for(int i = 0; i < n; i++) sum += weights[i];
        p[0] = (double)(weights[0] / sum);
        for(int i = 1; i < n; i++){
            p[i] = ((double)(weights[i] / sum)) + p[i-1];
        }
    }
    
    public int pickIndex() {
        double chance = rand.nextDouble();
        int l = 0, r = weights.length - 1;
        int mid = 0;
        while(l < r){
            mid = l + (r - l) / 2;
            if(p[mid] < chance){
                l = mid + 1;
            }else {
                r = mid;
            }
        }
        return l;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */