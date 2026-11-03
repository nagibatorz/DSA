// DFS cycle detection approach
// if graph has a cycle than it is impossible to take all of the courses
class Solution {
    List<List<Integer>> adj; // adjacency list representation of the graph
    Set<Integer> vis; // set of currently visiting nodes

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adj = new ArrayList<>();
        // populate adj with empty lists
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }
        // populate adj with prereqs
        for(int[] pair : prerequisites){
            adj.get(pair[1]).add(pair[0]);
        }
        vis = new HashSet<>();
        // traverse through the courses and look for cycles
        for(int c = 0; c < numCourses; c++){
            if(!dfs(c)){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int c){
        // cycle detected
        if(vis.contains(c)){
            return false;
        }
        // already traversed the node
        if(adj.get(c).isEmpty()){
            return true;
        }
        vis.add(c);
        // scan the neighbors of this course
        for(int nei : adj.get(c)){
            if(!dfs(nei)){
                return false;
            }
        }
        // remove the course from currently visiting because we traversed its neighbors
        vis.remove(c);
        // indicate the course doesn't have anymore neighbors to visit
        adj.set(c, new ArrayList<>());
        return true;
    }
}