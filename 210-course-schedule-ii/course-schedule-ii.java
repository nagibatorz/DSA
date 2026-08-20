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
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] p : prerequisites) {
            adj[p[0]].add(p[1]); // course -> prerequisite
        }

        int[] state = new int[numCourses]; // 0: unvisited, 1: visiting, 2: visited
        int[] ans = new int[numCourses];

        //all dfs calls share the same idx
        int[] idx = new int[]{0};

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(i, adj, state, ans, idx)) return new int[0];
            }
        }
        return ans;
    }

    private boolean hasCycle(int course, List<Integer>[] adj, int[] state, int[] ans, int[] idx) {
        if (state[course] == 1) return true;
        if (state[course] == 2) return false;

        state[course] = 1;
        for (int pre : adj[course]) {
            if (hasCycle(pre, adj, state, ans, idx)) return true;
        }

        state[course] = 2;
        ans[idx[0]++] = course; // All prerequisites fulfilled, safe to record
        return false;
    }
}