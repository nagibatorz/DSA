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
        // single node case
        res = root.val;

        dfs(root);
        // in the end result contains the maximum path sum
        // it was either updated within dfs method or returned upstream and computed from there
        return res;
    }

    private int dfs(TreeNode root){
        if(root == null) return 0;

        // explore left and right
        int left = dfs(root.left);
        int right = dfs(root.right);

        // ignore negative values
        left = (left > 0) ? left : 0;
        right = (right > 0) ? right : 0;

        // path sum with splitting 
        int sum = left + right + root.val;
        if(sum > res){
            res = sum;
        }

        //path sum witthout splitting
        return root.val +  ((left > right) ? left : right);
    }
}