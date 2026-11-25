class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int res = n;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isConnected[i][j] == 1 && uf.union(i, j)) res--;
            }
        }
        return res;
    }

    class UnionFind{
        int[] parent;
        int[] rank;

        public UnionFind(int n){
            this.parent = new int[n+1];
            this.rank = new int[n+1];
            for(int i = 0; i <= n; i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int node){
            int curr = node;
            // compress path and find parent
            while(curr != parent[curr]){
                parent[curr] = parent[parent[curr]];
                curr = parent[curr];
            }
            return curr;
        }

        public boolean union(int n1, int n2){
            // find parents
            int pn1 = find(n1);
            int pn2 = find(n2);
            
            // if parents are the same -> already unioned
            if(pn1 == pn2) return false;

            // always connect higher rank to lower rank
            if(rank[pn1] < rank[pn2]){
                int temp = pn1;
                pn1 = pn2;
                pn2 = temp;
            }
            // union and add up rank
            // rank of pn2 stays idle, ignored since parent[pn2] is now pn1 and we look up its rank we will be looking at rank[pn1]
            parent[pn2] = pn1;
            rank[pn1] += rank[pn2];
            return true;
        }
    }
}