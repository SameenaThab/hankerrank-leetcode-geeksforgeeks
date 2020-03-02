/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2981/
class MaxPathSum {
    int max_path = Integer.MIN_VALUE;
    
    public int helper(TreeNode root) {
        if(root == null)
            return 0;
        int left = Math.max(helper(root.left),0);
        int right = Math.max(helper(root.right),0);
        
        // int left = helper(root.left);
        // int right = helper(root.right);
        int full_tree = left+right+root.val;
        max_path = Math.max(full_tree,max_path);
        int half_tree = root.val+Math.max(left,right);
        return half_tree;
    }
    
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max_path;
    }
}