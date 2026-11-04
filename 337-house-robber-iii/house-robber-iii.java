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

 // Post-Order DFS with memory optimization
class Solution {
    public int rob(TreeNode root) {
        int[] rootOptions = dfs(root);
        return Math.max(rootOptions[0], rootOptions[1]);
    }

    private int[] dfs(TreeNode root){
        if(root == null){
            return new int[2]; // 0's by default
        }
        int[] leftOptions = dfs(root.left);
        int[] rightOptions = dfs(root.right);
        int withRoot = root.val + leftOptions[1] + rightOptions[1];
        int withoutRoot = Math.max(leftOptions[0], leftOptions[1]) + Math.max(rightOptions[0], rightOptions[1]);

        // Reuse left array to avoid extra allocation
        leftOptions[0] = withRoot;
        leftOptions[1] = withoutRoot;
        return leftOptions;  // leftOptions now contains values for root 
    }
}