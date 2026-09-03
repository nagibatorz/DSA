class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>();
    Set<Integer> vis = new HashSet<>();
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for(int i = 0; i < numCourses; i++){
            map.put(i, new ArrayList<>());
        }
        for(int[] pre : prerequisites){
            map.get(pre[0]).add(pre[1]);
        }

        for(int c = 0; c < numCourses; c++){
            if(hasCycle(c)){
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int course){
        if(vis.contains(course)) return true;
        if(map.get(course).isEmpty()) return false;
        vis.add(course);
        for(int pre : map.get(course)){
            if(hasCycle(pre)){
                return true;
            }
        }
        vis.remove(course);
        map.put(course, new ArrayList<>());
        return false;
    }
}