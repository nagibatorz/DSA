/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// DFS + Preorder traversal (preorder helps identufy where the root is)
public class Codec {

    private List<String> serialized;
    private int idx;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        serialized = new ArrayList<>();
        dfs(root);
        // add comma delimeter
        return String.join(",", serialized);
    }

    
    private void dfs(TreeNode root){
        if(root == null){
            serialized.add("N");
            return;
        }
        // Serialize with dfs in preorder
        serialized.add(""+root.val);
        dfs(root.left);
        dfs(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        idx = 0;
        // remove the delimeter and build valid string array from preorder traversal
        String[] nodes = data.split(",");
        return build(nodes);
    }

    private TreeNode build(String[] nodes){
        if(nodes[idx].equals("N")){
            idx += 1;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodes[idx]));
        // idx is incremented recursively and points to the next position within preoder
        idx += 1;
        root.left = build(nodes);
        root.right = build(nodes);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));