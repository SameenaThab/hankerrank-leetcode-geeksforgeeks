class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
/* 
Time complexity : in both serialization and deserialization functions, we visit each node exactly once, thus the time complexity is O(N), where N is the number of nodes, i.e. the size of tree.

Space complexity : in both serialization and deserialization functions, we keep the entire tree, either at the beginning or at the end, therefore, the space complexity is O(N).
*/
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String result = "";
        if(root == null)
            return null;
        result+=root.val+",";
        result+=serialize(root.left)+",";
        result+=serialize(root.right);
        return result;
    }

    int index = 0;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty())
            return null;
        String[] nodes = data.split(",");
        return deserialize(nodes);
    }

    private TreeNode deserialize(String[] nodes) {
        if(index >= nodes.length)
            return null;
        if(nodes[index].equals("null")) {
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodes[index]));
        index++;
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }

    public static void main(String[] args) {
        Codec sol = new Codec();
        TreeNode right = new TreeNode(7,new TreeNode(8),new TreeNode(9));
        TreeNode left = new TreeNode(5,new TreeNode(6),new TreeNode(4));
        TreeNode root = new TreeNode(10,left,right);
        String serialized = sol.serialize(root);
        System.out.println("Solution: "+serialized);
        TreeNode newRoot = sol.deserialize(serialized);
        preOrder(newRoot);
    }

    private static void preOrder(TreeNode root) {
        if(root == null)
            return;
        System.out.print(root.val+",");
        preOrder(root.left);
        preOrder(root.right);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));