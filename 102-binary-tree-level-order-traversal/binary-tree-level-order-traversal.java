/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


// BFS 
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while(!q.isEmpty()){
            int n = q.size();

            // pre alocating size of the ArrayList speeds up benchmarking
            List<Integer> level = new ArrayList<>(n);

            for(int i = 0; i < n; i++){
                TreeNode node = q.poll();
                level.add(node.val);

                //null checks before adding to q
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }

            //no need to check if level is empty because if we entered the while then we are guaranteed to have nodes at that level
            res.add(level);
        }
        return res;
    }
}