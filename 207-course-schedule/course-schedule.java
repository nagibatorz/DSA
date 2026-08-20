class Solution {
    private Map<Integer, List<Integer>> map = new HashMap<>();
    private Set<Integer> vis = new HashSet<>();
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0){
            return true;
        }
        for(int i = 0; i < numCourses; i++){
            map.put(i, new ArrayList<>());
        }
        for(int[] curr : prerequisites){
            map.get(curr[0]).add(curr[1]);
        }

        for(int c = 0; c < numCourses; c++){
            if(!dfs(c)){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int crs){
        if(vis.contains(crs)){
            return false;
        }
        if(map.get(crs).isEmpty()){
            return true;
        }
        vis.add(crs);
        for(int pre : map.get(crs)){
            if(!dfs(pre)){
                return false;
            }
        }
        vis.remove(crs);
        map.put(crs, new ArrayList<>());
        return true;
    }
}