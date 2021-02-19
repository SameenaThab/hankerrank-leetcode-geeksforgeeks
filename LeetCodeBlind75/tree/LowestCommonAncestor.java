import javax.lang.model.util.ElementScanner6;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/


/*  

Since its a BST we can compare the val of p ad q with root and got left or right accordingly
root == p || root == q
    return root;
p and q < root -> go left
p and q > root -> go right
else return root
*/
class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p == root || q == root)
            return root;
        if(p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        else if( p.val > root.val && q.val > root.val )
            return lowestCommonAncestor(root.right, p, q);
        else
            return root; // one in left , other in right
    }
}