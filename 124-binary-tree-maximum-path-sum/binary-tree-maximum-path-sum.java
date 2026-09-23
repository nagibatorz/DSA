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
// Eliminated Math.max calls to speed up benchmarking
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
        leftMax = (leftMax > 0) ? leftMax : 0;
        rightMax = (rightMax > 0) ? rightMax : 0;

        //Compute max path sum for this root, if we split
        int currPathSum = root.val + leftMax + rightMax;
        if(currPathSum > res){
            res = currPathSum;
        }

        // return path sum for this path if we do not split, extend upstream path
        return root.val + ((leftMax > rightMax) ? leftMax : rightMax);
    }
}