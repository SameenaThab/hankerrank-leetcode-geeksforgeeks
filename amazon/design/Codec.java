/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// https://leetcode.com/explore/interview/card/amazon/81/design/2999/
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serHelper(root,"");
    }
    
    public String serHelper(TreeNode root,String st) {
        if(root == null) {
            st +="null,";
        }
        else {
          st += st.valueOf(root.val) + ",";
          st = serHelper(root.left, st);
          st = serHelper(root.right, st);
        }
        return st;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        String[] arr = data.split(",");
        //System.out.println(arr[0]);
        List<String> list = new LinkedList<String>(Arrays.asList(arr));
        return deserHelper(list);
    }
    
    public TreeNode deserHelper(List<String> list) {
        //System.out.println(list.get(0));
        if(list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = deserHelper(list);
        root.right = deserHelper(list);
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));