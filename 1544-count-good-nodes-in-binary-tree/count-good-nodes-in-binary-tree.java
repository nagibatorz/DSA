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
    public int goodNodes(TreeNode root) {
        if(root == null) return 0;
        return dfs(root, root.val);
    }

    private int dfs(TreeNode root, int max){
        if(root == null) return 0;
        int curr = 0;

        //update max, current node is a good node
        if(root.val >= max){
            max = root.val;
            curr += 1;
        }

        //explore deeper
        return curr + dfs(root.left, max) + dfs(root.right, max);
    }
}