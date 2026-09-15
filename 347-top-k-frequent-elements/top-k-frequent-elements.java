class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for(int n : map.keySet()){
            int[] entry = new int[2];
            entry[0] = n;
            entry[1] = map.get(n);
            maxHeap.offer(entry);
        }
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = maxHeap.poll()[0];
        }
        return res;
    }
}