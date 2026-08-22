class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = 0;
        for(int[] edge : edges){
            n = Math.max(n, edge[0]);
            n = Math.max(n, edge[1]);
        }
        UF disjointSet = new UF(n + 1); //the problem is 1 indexed
        int[] res = new int[2];
        for(int[] edge : edges){
            if(!disjointSet.union(edge[0], edge[1])){
                res = edge; // return the edge that cause a cycle and was last to iterate on
            }
        }
        return res;
    }
}


// Union Find with path compression optimization for faster look-ups
class UF{
    int[] parent;
    int[] rank;
    
    public UF(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int node){
        int curr = node;
        while(curr != parent[curr]){
            parent[curr] = parent[parent[curr]]; //rewires the tree
            curr = parent[curr];
        }
        return curr;
    }

    public boolean union(int n1, int n2){
        int pn1 = find(n1);
        int pn2 = find(n2);
        if(pn1 == pn2){ //already connected -- cycle detected
            return false;
        }
        // make sure we are adding to the bigger "root"
        if(rank[pn2] > rank[pn1]){
            int temp = pn2;
            pn2 = pn1;
            pn1 = temp;
        }
        // union to the bigger root
        parent[pn2] = pn1;
        rank[pn1] += rank[pn2];
        return true; 
    }
}