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
//DFS
class Solution {

    public int diameterOfBinaryTree(TreeNode root) {
        //global variable
        int[] res = new int[1];

        //track diameter using global variable
        dfs(res, root);
        return res[0];
    }

    private int dfs(int[] res, TreeNode root){
        if(root == null) return 0;

        //get diameter of left and right 
        int left = dfs(res, root.left);
        int right = dfs(res, root.right);

        // get biggest diameter so far
        res[0] = Math.max(res[0], left + right);

        //add 1 as one more node in the diameter path
        return 1 + Math.max(left, right);
    }
}
