/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/514/
class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return helper(root,null,null);
    }
    
    public boolean helper(TreeNode root,Integer min,Integer max) {
        if(root == null)
            return true;
        int val = root.val;
        if(min != null && min >= val) return false;
        if(max != null && max <= val) return false;
        if(!helper(root.right,val,max)) return false;
        if(!helper(root.left,min,val)) return false;
        return true;
    }
}