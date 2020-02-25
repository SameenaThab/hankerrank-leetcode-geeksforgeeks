//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/507/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
       return helper(root,root); 
    }
    
    boolean helper(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null)
            return true;
        if(t1 == null || t2 == null)
            return false;
        return t1.val == t2.val && helper(t1.right,t2.left) && helper(t1.left,t2.right);
    }
}