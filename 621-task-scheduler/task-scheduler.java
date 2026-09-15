class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(n == 0) return tasks.length;

        int[] freq = new int[26];
        for(char c : tasks){
            freq[c - 'A']++;
        }
        Arrays.sort(freq);
        int maxf = freq[25];
        int idleTotal = (maxf - 1) * n;
        for(int i = 24; i >= 0; i--){
            idleTotal -= Math.min(maxf - 1, freq[i]);
        }
        return Math.max(0, idleTotal) + tasks.length;
    }
}