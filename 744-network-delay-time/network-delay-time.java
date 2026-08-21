class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        if(times.length == 0) return 0;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int[] edge : times){
            map.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, k});
        Set<Integer> vis = new HashSet<>();
        int t = 0;
        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int w1 = curr[0], n1 = curr[1];
            if(vis.contains(n1)){
                continue;
            }
            vis.add(n1);
            t = w1;
            if(map.containsKey(n1)){
                for(int[] nei : map.get(n1)){
                    int n2 = nei[0], w2 = nei[1];
                    if(!vis.contains(n2)){
                        minHeap.offer(new int[]{w1+w2, n2});
                    }
                }
            }
        }
        return vis.size() == n ? t : -1;

    }
}