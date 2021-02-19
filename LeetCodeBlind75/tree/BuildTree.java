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

/*
The preorder traversal follows Root -> Left -> Right order, that makes it very convenient to construct the tree from its root.
Let's do it. The first element in the preorder list is a root. This root splits inorder list into left and right subtrees.
Now one have to pop up the root from preorder list since it's already used as a tree node and then repeat the step above for the left and right subtrees.

Time complexity : O(N). Let's compute the solution with the help of master theorem
 T(N) = aT(b/N) + Θ(N^d)
The equation represents dividing the problem up into a subproblems of size N/b in Θ(N^d) time.
Here one divides the problem in two subproblemes a = 2, the size of each subproblem (to compute left and right subtree) is a half of initial problem b = 2, and all this happens in a constant time d = 0.
That means that log(base b)(a) > d and hence we're dealing with case 1 that means O(N^(log(b)a)) = O(n) time complexity.
Space complexity : O(N), since we store the entire tree.
*/
class BuildTree {
    int preIndex = 0;
    int[] preorder;
    int[] inorder;
    Map<Integer,Integer> inOrderValueIndexes = new HashMap<Integer,Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0;i<inorder.length;i++) {
            inOrderValueIndexes.put(inorder[i],i);
        }
        this.preorder = preorder;
        this.inorder = inorder;
        return buildTree(0,preorder.length);
    }

    private TreeNode buildTree(int left, int right) {
        if(left == right)
            return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        // find root in inorder
        int index = inOrderValueIndexes.get(preorder[preIndex]);
        preIndex++;
        root.left = buildTree(left, index);
        root.right = buildTree(index+1, right);
        return root;
    }

    public void inOrder(TreeNode t) {
        if(t == null)
            return;
        inOrder(t.left);
        System.out.print(t.val+" ");
        inOrder(t.right);
    }

    public static void main(String[] args) {
        BuildTree sol = new BuildTree();
        TreeNode root = sol.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        sol.inOrder(root);
    }  

}