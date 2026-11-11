import java.util.*;
import java.math.*;

class Solution {

    
    public List<List<String>> jobScheduler(List<List<String>> jobs) {
        // TODO: Implement jobScheduler logic
        List<List<String>> res = new ArrayList<>();
        int count = 0;
        // [finishTime, machineId]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
                if(a[0] != b[0]){
                    return Integer.compare(a[0], b[0]);
                } 
                return Integer.compare(a[1], b[1]);
            });

        // store machineId only because this heap store available machines
        PriorityQueue<Integer> available = new PriorityQueue<>();
        for(List<String> job : jobs){
            String jId = job.get(0);

            // parse start time
            int hour = Integer.parseInt(job.get(1).substring(0, 2));
            int minutes = Integer.parseInt(job.get(1).substring(2));
            int start = hour * 60 + minutes;
            
            // calculate finish time
            int finishTime = start + Integer.parseInt(job.get(2));

            // at this point of time get all machines that available
            while(!busy.isEmpty() && busy.peek()[0] <= start){
                available.offer(busy.poll()[1]);
            }

            int mId;
            // at this point we should have all possible machines on available heap
            if(!available.isEmpty()){
                mId = available.poll();
            } else{ // if no machines available we create a new one
                count++;
                mId = count;
                
            }
            // update busy machines with the new addition
            busy.offer(new int[]{finishTime, mId});
            
            //record the assignment in the result
            List<String> assignJob = new ArrayList<>();
            assignJob.add(jId);
            assignJob.add("M" + mId);
            res.add(assignJob);


        }

        return res;
     }
}
