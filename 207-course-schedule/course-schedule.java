// DFS cycle detection approach
// if graph has a cycle than it is impossible to take all of the courses
class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>(); //adjacency map
    Set<Integer> vis = new HashSet<>(); // set of currently visiting nodes
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // populate map with empty entries
        for(int i = 0; i < numCourses; i++){
            map.put(i, new ArrayList<>());
        }
        // populate map with prereqs
        for(int[] pre : prerequisites){
            map.get(pre[0]).add(pre[1]);
        }

        // traverse through the courses and look for cycles
        for(int c = 0; c < numCourses; c++){
            if(hasCycle(c)){
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int course){
        //cycle detected
        if(vis.contains(course)) return true;
        //already traversed the node
        if(map.get(course).isEmpty()) return false;
        vis.add(course);
        //scan the neighbors if thsie course
        for(int pre : map.get(course)){
            if(hasCycle(pre)){
                return true;
            }
        }
        //remove the course from currently visiting because we traversed its neighbors
        vis.remove(course);
        //indicate the course doesn't have anymore neighbors to visit
        map.put(course, new ArrayList<>());
        return false;
    }
}