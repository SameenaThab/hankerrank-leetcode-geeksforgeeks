class InorderSuccessor_BST {
    /* 
                    6
            4           10
        2           8       12
            3(p)
    
    Algorithm
    1. We start our traversal with the root node and continue the traversal until our current node reaches a null value i.e. there are no more nodes left to process.

    2. At each step we compare the value of node p with that of node.

        1.If p.val >= node.val that implies we can safely discard the left subtree since all the nodes there including the current node have values less than p.

        Skipping the left subtree

        2.However, if p.val < node.val, that implies that the successor must lie in the left subtree and that the current node is a potential candidate for inorder successor. Thus, we update our local variable for keeping track of the successor, successor, to node.

        Skipping the right subtree

    3. Return successor.
    
    */
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }
    
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode successor = null;
        
            while (root != null) {
                
                if (p.val >= root.val) {
                    // even when we reach p we traverse right, in next iteration we update right node to be successor 
                } else {
                    successor = root;
                    root = root.left;
                }
            }
            
            return successor;
        }
    }