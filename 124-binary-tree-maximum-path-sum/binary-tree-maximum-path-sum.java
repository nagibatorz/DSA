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
//DFS + compute path as 2 options: split/ no split
class Solution {

    private int res;
    public int maxPathSum(TreeNode root) {
        res = root.val;
        dfs(root);
        return res; // in the end result contains the maximum path sum
        // it was either updated within dfs method or returned upstream and computed from there
    }

    private int dfs(TreeNode root){
        if(root == null) return 0;

        // explore right and left subtrees
        int leftMax = dfs(root.left);
        int rightMax = dfs(root.right);

        // ignore paths with negative nodes
        leftMax = Math.max(0, leftMax);
        rightMax = Math.max(0, rightMax);

        //Compute max path sum for this root, if we split
        res = Math.max(res, root.val + leftMax + rightMax);

        // return path sum for this path if we do not split, extend upstream path
        return root.val + Math.max(leftMax, rightMax);
    }
}