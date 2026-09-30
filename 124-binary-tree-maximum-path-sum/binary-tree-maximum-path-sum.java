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
class Solution {
    private int res;
    public int maxPathSum(TreeNode root) {
        res = root.val;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root){
        if(root == null) return 0;

        int left = dfs(root.left);
        left = (left > 0) ? left : 0;
        int right = dfs(root.right);
        right = (right > 0) ? right : 0;

        int sum = left + right + root.val;
        if(sum > res){
            res = sum;
        }

        return root.val +  ((left > right) ? left : right);
    }
}