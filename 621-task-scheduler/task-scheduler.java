// Greedy Algorithm: count all idle slots for maximum tasks
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

            //exact ties case
            idleTotal -= Math.min(maxf - 1, freq[i]); //the task doesn't consume all idle slots and goes in the end
        }
        return Math.max(0, idleTotal) + tasks.length;
    }
}