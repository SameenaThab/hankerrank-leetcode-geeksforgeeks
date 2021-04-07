class InorderSuccessor_BinaryTree {
/* 
                6
        4           10
    2           8       12
        3(p)

Algorithm

We define two class variables for this algorithm: previous and inorderSuccessorNode. 
The previous variable will only be used when handling the second case as previously explained and the inorderSuccessorNode will ultimately contain the result to be returned.
Inside the function inorderSuccessor, we first check which of the two cases we need to handle. 
For that, we simply check for the presence of a right child.

The right child exists
In this case, we assign the right child to a node called leftmost and we iterate until we reach a node (leftmost) which doesn't have a left child. We iteratively assign leftmost = leftmost.left and that's how we will get the leftmost node in the subtree.

The right child does not exist
As mentioned before, this case is trickier to handle. For this, we define another function called inorderCase2 and we will pass it a node and the node p.
We perform simple inorder traversal and hence, we first recurse on the left child of the node.
Then, when the recursion returns, we check if the class variable previous is equal to the node p. If that is the case, then it means p is the inorder predecessor of node or in other words, the node is the inorder successor of the node p and we return from that point onwards. We assign inorderSuccessorNode to node and return from this function.
Finally, we return the inorderSuccessorNode as our result.

*/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    TreeNode predessor = null;
    TreeNode inOrderSuccessor = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right!=null) {
            inOrderSuccessor = leftMost(p.right);
        } else {
            inOrderTraverse(root,p);
        }
        return inOrderSuccessor;
    }
    private void inOrderTraverse(TreeNode node, TreeNode p) {
        if(node == null)
            return;
        inOrderTraverse(node.left, p);
        if(predessor == p && inOrderSuccessor != null){
            inOrderSuccessor = node;
            return;
        }
        predessor = node;
        inOrderTraverse(node.right,p);
    }
    private TreeNode leftMost(TreeNode node) {
        TreeNode t = node;
        while(t.left!=null) {
            t=t.left;
        }
        return t;
    }
}