// class Solution {
//     Map<Integer, List<Integer>> map = new HashMap<>();
//     List<Integer> ord = new ArrayList<>();
//     Set<Integer> cycle = new HashSet<>();
//     Set<Integer> processed = new HashSet<>();
//     public int[] findOrder(int numCourses, int[][] prerequisites) {
//         if(numCourses == 0) return new int[0];
//         for(int i = 0; i < numCourses; i++){
//             map.put(i, new ArrayList<>());
//         }
//         for(int[] pair : prerequisites){
//             map.get(pair[0]).add(pair[1]);
//         }
//         for(int c = 0; c < numCourses; c++){
//             if(!dfs(c)) return new int[0];
//         }
//         int[] ans = new int[numCourses];
//         for(int i = 0; i < numCourses; i++){
//             ans[i] = ord.get(i);
//         }
//         return ans;
//     }

//     private boolean dfs(int course){
//         if(cycle.contains(course)){
//             return false;
//         }
//         if(processed.contains(course)){
//             return true;
//         }
//         cycle.add(course);
//         for(int pre : map.get(course)){
//             if(!dfs(pre)){
//                 return false;
//             }
//         }
//         cycle.remove(course);
//         processed.add(course);
//         ord.add(course);
//         return true;
//     }
// }

//optimized DS usage:
class Solution {
    List<List<Integer>> adj;
    int[] vis;
    int idx = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // need to add all course without prereqs to the final result

        adj = new ArrayList<>();
        // build out adjacency list
        // populate with empty entries to prevent null exception
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        // populate prerequisites into the adjacency list
        // course -> prerequisite
        for(int[] pair : prerequisites){
            adj.get(pair[0]).add(pair[1]);
        }

        int[] output = new int[numCourses];
        vis = new int[numCourses];
        // use DFS to find valid order to take the courses
        // traverse starting from each node -> mark visited nodes that are reachable from that node
        for(int i = 0; i < numCourses; i++){
            if(hasCycleAndProcess(i, output)){
                return new int[0];
            }
        }
        return output;
    }

    private boolean hasCycleAndProcess(int i, int[] output){
        if(vis[i] == 1) return true;
        if(vis[i] == 2) return false;
        vis[i] = 1;
        for(int pre : adj.get(i)){
            if(hasCycleAndProcess(pre, output)){
                return true;
            }
        }
        vis[i] = 2;
        output[idx] = i;
        idx++;
        return false;
    }
}
