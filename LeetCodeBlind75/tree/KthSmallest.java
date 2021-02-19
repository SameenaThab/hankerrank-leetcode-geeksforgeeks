import java.util.*;

class KthSmallest {
    /* 
    Approach 1:
    We know inOreder traversal is sorted list of BST
    Make inOrder list and return list.get(k-1) element
    Time complexity : O(N) to build a traversal.
    Space complexity :  O(N) to keep an inorder traversal.
    */
    
    public int kthSmallestApp1(TreeNode root, int k) {
        List<Integer> list = new ArrayList<Integer>();
        inOrder(root,list);
        return list.get(k-1);        
    }

    private void inOrder(TreeNode root,List<Integer> list) {
        if(root == null)
            return;
        inOrder(root.left,list);
        list.add(root.val);
        inOrder(root.right,list);
    }

    /* 
    Approach 2:
    We dont have to make the whole inOrder list , we can just stop by kth element  using stack
    Time complexity: O(H+k), where H is a tree height. This complexity is defined by the stack, which contains at least H+k elements, since before starting to pop out one has to go down to a leaf. This results in O(logN+k) for the balanced tree and O(N+k) for completely unbalanced tree with all the nodes in the left subtree.
    Space complexity: O(H) to keep the stack, where H is a tree height. That makes O(N) in the worst case of the skewed tree, and  O(logN) in the average case of the balanced tree.
    */
    public int kthSmallestApp2(TreeNode root, int k) {
        Stack<TreeNode> stack  = new Stack<TreeNode>();
        // put all left nodes in stack . leftmost is the smallest node
        while(true) {
            while(root != null) {
                stack.push(root);
                root=root.left;
            }   
            root = stack.pop(); // pop current leftmost node 
            k--; // since k is 1 based index
            if(k==0)
                return root.val;
             //because next smallest is right sibling of leftmost node
            // if right sibling is null parent of left most is next smallest, which is already pushed in stack
            root = root.right;
        }
    }  
    
}