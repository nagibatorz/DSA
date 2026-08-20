class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>();
    List<Integer> ord = new ArrayList<>();
    Set<Integer> cycle = new HashSet<>();
    Set<Integer> processed = new HashSet<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 0) return new int[0];
        for(int i = 0; i < numCourses; i++){
            map.put(i, new ArrayList<>());
        }
        for(int[] pair : prerequisites){
            map.get(pair[0]).add(pair[1]);
        }
        for(int c = 0; c < numCourses; c++){
            if(!dfs(c)) return new int[0];
        }
        int[] ans = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            ans[i] = ord.get(i);
        }
        return ans;
    }

    private boolean dfs(int course){
        if(cycle.contains(course)){
            return false;
        }
        if(processed.contains(course)){
            return true;
        }
        cycle.add(course);
        for(int pre : map.get(course)){
            if(!dfs(pre)){
                return false;
            }
        }
        cycle.remove(course);
        processed.add(course);
        ord.add(course);
        return true;
    }
}