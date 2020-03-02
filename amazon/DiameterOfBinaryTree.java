/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// same as MaxPath
//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2985/
class DiameterOfBinaryTree {
    
        int max_path;
    
    public int helper(TreeNode root) {
        if(root == null)
            return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        
        // int left = helper(root.left);
        // int right = helper(root.right);
        int full_tree = 1+left+right;
        max_path = Math.max(full_tree,max_path);
        int half_tree = 1+Math.max(left,right);
        return half_tree;
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        max_path = 1;
        helper(root);
        return max_path-1;
    }
}