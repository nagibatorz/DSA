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

 // If we see not a null node after we saw a null node ->
 // -> nodes are not skewed to the left -> tree is incomplete
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> q = new ArrayDeque<>();
        boolean nullSeen = false;
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.remove();

            if (curr.left != null) {
                if (nullSeen) return false;
                q.add(curr.left);
            } else {
                nullSeen = true;
            }

            if (curr.right != null) {
                if (nullSeen) return false;
                q.add(curr.right);
            } else {
                nullSeen = true;
            }
        }
        return true;
    }
}