import java.util.*;


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
// https://leetcode.com/problems/invert-binary-tree/
class InvertTree {
    /* 
    Time Complexity : O(n)
    Space Complexity : O(h) recursion stack grows till height of tree
    */
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode inverted = new TreeNode(root.val);
        inverted.left = invertTree(root.right);
        inverted.right = invertTree(root.left);
        return inverted;
    }

    /* 
    Since each node in the tree is visited/added to the queue only once,
    the time complexity is O(n), where n is the number of nodes in the tree.

    Space complexity is O(n), since in the worst case, the queue will contain all nodes in one level of the binary tree.
    For a full binary tree, the leaf level has n/2 leaves, therefore O(n) 
2
n
​	
 ⌉=O(n) leaves.
    */
    public TreeNode invertTreeIterative(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null)
            queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp; 
            if(curr.left != null)
                queue.add(curr.left);
            if(curr.right != null)
                queue.add(curr.right);
        }
        return root;
    }

    public void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null)
            queue.add(root);
        while(!queue.isEmpty()) {
            int n = queue.size();
            for(int i=0;i<n;i++) {
                TreeNode curr = queue.poll();
                System.out.print(curr.val+" ");
                if(curr.left != null)
                    queue.add(curr.left);
                if(curr.right != null)
                    queue.add(curr.right);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        InvertTree sol = new InvertTree();
        TreeNode right = new TreeNode(2,new TreeNode(1),new TreeNode(3));
        TreeNode left = new TreeNode(7,new TreeNode(6),new TreeNode(9));
        TreeNode root = new TreeNode(4,left,right);
        sol.levelOrder(root);
        System.out.println("Invertion ");
        TreeNode inverted = sol.invertTree(root);
        TreeNode inverted2 = sol.invertTreeIterative(root);
        sol.levelOrder(inverted);
        sol.levelOrder(inverted2);
    }
}