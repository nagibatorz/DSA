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

// Iterative DFS using a Stack
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        Stack<TreeNode> st = new Stack<>();

        //scan the tree
        while(curr != null || !st.isEmpty()){

            //go all the way left
            while(curr != null){
                st.push(curr);
                curr = curr.left;
            }

            //start popping and counting 
            curr = st.pop();
            k--;
            if(k == 0){
                return curr.val;
            }

            //check right subtree
            curr = curr.right;
        }

        return -1;
    }
}
