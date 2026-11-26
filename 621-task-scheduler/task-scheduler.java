// Greedy Algorithm: count all idle slots for maximum tasks
class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(n == 0) return tasks.length;

        // count frequency of each task
        int[] count = new int[26];
        for(char task : tasks){
            count[task - 'A']++;
        }

        // sort for
        // iterating starting from the most frequent
        // get the highest frequency task for isle calculation
        // return early
        Arrays.sort(count);
        int maxFreq = count[25];
        int idleSlots = (maxFreq - 1) * n;


        for(int i = 24; i >= 0; i--){
            if(count[i] == 0 || idleSlots == 0) break;  // no more tasks to add || all spaces filled, can return length of tasks

            // if a task has the same frequency as max then the last occurence get prepended in the end
            if(count[i] == maxFreq){
                idleSlots -= maxFreq - 1;
            } else{ // fill in idle slots
                idleSlots -= count[i];
            }
            
        }

        // add computed idle slots to the number of tasks given
        return Math.max(0, idleSlots) + tasks.length;
    }
}