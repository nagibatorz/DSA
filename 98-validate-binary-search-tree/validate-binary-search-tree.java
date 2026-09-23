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
 // DFS approach + Use long due to root.val constraints
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode curr, long min, long max){
        if(curr == null) return true;

        if(curr.val <= min || curr.val >= max){
            return false;
        }

        //curr node because the upper bound for left subtree and lower bound for left subtree
        return dfs(curr.left, min, curr.val) && dfs(curr.right, curr.val, max);
    }
}