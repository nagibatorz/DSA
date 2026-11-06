// Simplified approach using DFS and checking valid tree condition in the beginning
class Solution {
    List<List<Integer>> adj;
    boolean[] vis;
    public boolean validTree(int n, int[][] edges) {
        // if edges.length > n-1 -> there is a cycle
        // if edges.length < n-1 not all componets are connected
        if(edges.length != n-1) return false;
        adj = new ArrayList<>();
        vis = new boolean[n];
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            int n1 = edge[0], n2 = edge[1];
            adj.get(n1).add(n2);
            adj.get(n2).add(n1);
        }
        dfs(0);
        
        for(int i = 0; i < n; i++){
            if(!vis[i]) return false;
        }
        return true;
    }

    private void dfs(int node){
        vis[node] = true;
        for(int nei : adj.get(node)){
            if(!vis[nei]){
                dfs(nei);
            }
            
        }
    }
}
