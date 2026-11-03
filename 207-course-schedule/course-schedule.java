class Solution {
    List<List<Integer>> adj;
    Set<Integer> vis;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] pair : prerequisites){
            adj.get(pair[1]).add(pair[0]);
        }
        vis = new HashSet<>();
        for(int c = 0; c < numCourses; c++){
            if(!dfs(c)){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int c){
        if(vis.contains(c)){
            return false;
        }
        if(adj.get(c).isEmpty()){
            return true;
        }
        vis.add(c);
        for(int nei : adj.get(c)){
            if(!dfs(nei)){
                return false;
            }
        }
        vis.remove(c);
        adj.set(c, new ArrayList<>());
        return true;
    }
}