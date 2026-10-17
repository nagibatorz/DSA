import java.util.*;

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Build the adjacency list
        List<List<Node>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            adj.get(a).add(new Node(b, succProb[i]));
            adj.get(b).add(new Node(a, succProb[i]));
        }
        
        // Max-heap comparing the double probabilities
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        
        // Track the max probability to reach each node instead of a boolean visited set
        double[] maxProb = new double[n];
        maxProb[start_node] = 1.0;
        
        // Start Dijkstra
        pq.offer(new Node(start_node, 1.0));
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            int currNode = curr.node;
            double currProb = curr.prob;
            
            // If we reached the target, return the probability
            if(currNode == end_node) {
                return currProb;
            }
            
            // Optimization: If a better path to this node was already found, skip processing
            if(currProb < maxProb[currNode]) {
                continue;
            }
            
            for(Node nei : adj.get(currNode)){
                int nextNode = nei.node;
                double nextProb = currProb * nei.prob;
                
                // Only push to the queue if this new path offers a higher probability
                if(nextProb > maxProb[nextNode]){
                    maxProb[nextNode] = nextProb;
                    pq.offer(new Node(nextNode, nextProb));
                }
            }
        }
        
        return 0.0;
    }

    // Custom class acts as our "Tuple"
    class Node {
        int node;
        double prob;
        public Node(int node, double prob){
            this.node = node;
            this.prob = prob;
        }
    }
}