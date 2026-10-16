import java.util.*;
import java.math.*;

class Solution {
    public List<List<String>> countRoleCooccurrences(List<List<String>> performances, int period) {
        // TODO: Implement countRoleCooccurrences logic

        // singer -> [role, year]
        Map<String, List<String[]>> bySinger = new HashMap<>();

        // get all roles -> tree set to ensure sorted order 
        Set<String> roles = new TreeSet<>();

        // get all roles
        // populate bySinger entries
        for(List<String> p : performances){
            String singer = p.get(0);
            String role = p.get(1);
            String year = p.get(2);
            roles.add(role);
            if(!bySinger.containsKey(singer)){
                bySinger.put(singer, new ArrayList<>());
            }
            bySinger.get(singer).add(new String[]{role, year});
        }

        // roleA -> roleB -> coCount
        Map<String, Map<String, Integer>> coCount = new HashMap<>();
        for(String role : roles){
            coCount.put(role, new HashMap<>());
        }

        // Count cooccurences
        for(List<String[]> roleYear : bySinger.values()){
            int n = roleYear.size();
            for(int i = 0; i < n; i++){

                // iterate through the rest of the roles per singer
                for(int j = i + 1; j < n; j++){
                    String roleA = roleYear.get(i)[0];
                    String roleB = roleYear.get(j)[0];
                    long yearA = Long.parseLong(roleYear.get(i)[1]);
                    long yearB = Long.parseLong(roleYear.get(j)[1]);

                    // check period validity
                    if(Math.abs(yearA - yearB) <= period){
                        // get a reference to role cocount for conveniece
                        Map<String, Integer> mapA = coCount.get(roleA);
                        Map<String, Integer> mapB = coCount.get(roleB);
                        mapA.put(roleB, mapA.getOrDefault(roleB, 0) + 1);
                        mapB.put(roleA, mapB.getOrDefault(roleA, 0) + 1);
                    }
                }
            }
        }
        

        List<List<String>> res = new ArrayList<>();
        for(String role : roles){
            Map<String, Integer> roleA = coCount.get(role);

            // no cocounts
            if(roleA.isEmpty()){
                List<String> lst = new ArrayList<>();
                lst.add(role);
                lst.add("None");
                lst.add("0");
                res.add(lst);
            } else{
                List<String> coRoles = new ArrayList<>(roleA.keySet());

                // sort cocounted roles
                Collections.sort(coRoles);
                for(String coRole : coRoles){
                    List<String> lst = new ArrayList<>();
                    lst.add(role);
                    lst.add(coRole);
                    lst.add(String.valueOf(roleA.get(coRole)));
                    res.add(lst);
                }
            }
        }
        return res;
    }
}
