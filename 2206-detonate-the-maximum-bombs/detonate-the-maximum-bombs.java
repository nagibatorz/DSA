class Solution {
    public int maximumDetonation(int[][] bombs) {
        List<Bomb> adj = new ArrayList<>();
        for(int[] bomb : bombs){
            adj.add(new Bomb(bomb));
        }

        for(int i = 0; i < adj.size(); i++){
            Bomb bomb1 = adj.get(i);
            for(int j = i + 1; j < adj.size(); j++){
                Bomb bomb2 = adj.get(j);

                long dx = bomb1.x - bomb2.x;
                long dy = bomb1.y - bomb2.y;
                long distSq = dx * dx + dy * dy;

                // Directed check 1: Can b1 detonate b2?
                if ((long) bomb1.r * bomb1.r >= distSq) {
                    bomb1.neighbors.add(bomb2);
                }
                // Directed check 2: Can b2 detonate b1?
                if ((long) bomb2.r * bomb2.r >= distSq) {
                    bomb2.neighbors.add(bomb1);
                }
            }
        }
        int max = 0;
        for(Bomb bomb : adj){
            int newCandidate = dfs(bomb, new HashSet<>());
            if(newCandidate > max){
                max = newCandidate;
            }
        }
        return max;
    }


    private int dfs(Bomb bomb, Set<Bomb> vis){
        if(vis.contains(bomb)){
            return 0;
        }
        vis.add(bomb);
        for(Bomb nei : bomb.neighbors){
            dfs(nei, vis);
        }
        return vis.size();
    }

    class Bomb{
        List<Bomb> neighbors;
        int x;
        int y;
        int r;
        public Bomb(int[] bomb){
            this.x = bomb[0];
            this.y = bomb[1];
            this.r = bomb[2];
            this.neighbors = new ArrayList<>();
        }
    }
}