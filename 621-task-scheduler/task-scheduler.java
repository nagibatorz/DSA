// Greedy Algorithm: count all idle slots for maximum tasks
class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(n == 0) return tasks.length;

        int[] count = new int[26];
        for(char task : tasks){
            count[task - 'A']++;
        }
        Arrays.sort(count);
        int maxFreq = count[25];
        int idleSlots = (maxFreq - 1) * n;
        for(int i = 24; i >= 0; i--){
            if(count[i] == 0) break;
            idleSlots -= Math.min(maxFreq - 1, count[i]);
        }
        return Math.max(0, idleSlots) + tasks.length;
    }
}