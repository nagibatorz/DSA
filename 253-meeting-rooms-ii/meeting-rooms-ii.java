/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

// Time Complexity: O(nlogn) - sort 
// Space Complexity: O(n) - heap
class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        if(intervals.size() == 0) return 0;
        if(intervals.size() == 1) return 1;
        Collections.sort(intervals, (a, b) ->{
            if(a.start != b.start){
                return Integer.compare(a.start, b.start);
            }
            return Integer.compare(a.end, b.end);
        });

        // max variable
        int max = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Interval i : intervals){
            int start = i.start, end = i.end;

            // free all meetings that have ended before the current one started
            while(!pq.isEmpty() && pq.peek() <= start){
                pq.poll();
            }

            // add current meeting to occupancy
            pq.offer(end);
            max = Math.max(max, pq.size());
        }
        return max;
    }
}
