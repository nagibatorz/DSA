import java.util.*;
import java.math.*;

class Solution {

    public List<String> loadFactorCalc(List<String> serviceList, String entryPoint) {
        // TODO: Implement loadFactorCalc logic
        // populate adjacency in two passes:
        // get dependecies
        // drop undefined dependencies

        // node -> dependecies
        Map<String, List<String>> adj = new HashMap<>();
        for(String s : serviceList){
            String key = s.substring(0, s.indexOf("="));
            adj.put(key, new ArrayList<>());
        }
        for(String s : serviceList){
            int eqIdx = s.indexOf("=");
            String[] dependencies = s.substring(eqIdx + 1).split(",");
            String parent = s.substring(0, eqIdx);
            for(String dependency : dependencies){
                if(adj.containsKey(dependency)){
                    adj.get(parent).add(dependency);
                }
            }
        }

        Set<String> reachable = new HashSet<>();
        Deque<String> q = new ArrayDeque<>();
        reachable.add(entryPoint);
        q.add(entryPoint);
        while(!q.isEmpty()){
            for(String nei : adj.get(q.remove())){
                if(!reachable.contains(nei)){
                    reachable.add(nei);
                    q.add(nei);
                }
            }
        }

        Map<String, Integer> indegree = new HashMap<>();
        for(String s : reachable){
            for(String p : adj.get(s)){
                indegree.put(p, indegree.getOrDefault(p, 0) + 1);
            }
        }

        Map<String, Long> load = new HashMap<>();
        load.put(entryPoint, 1L);
        q.add(entryPoint);
        while(!q.isEmpty()){
            String curr = q.remove();
            long currLoad = load.get(curr);
            for(String nei : adj.get(curr)){
                load.put(nei, load.getOrDefault(nei, 0L) + currLoad);
                int oldIn = indegree.get(nei);
                indegree.put(nei, oldIn - 1);
                if((oldIn - 1) == 0){
                    q.add(nei);
                }
            }
        }
        List<String> services = new ArrayList<>();
        for(Map.Entry<String, Long> service : load.entrySet()){
            services.add(service.getKey() + "*" + service.getValue());
        }
        Collections.sort(services);
        return services;
     }
}
