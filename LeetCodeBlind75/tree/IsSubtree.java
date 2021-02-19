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

class IsSubtree {
    /* 
    Time complexity : O(m*n). In worst case(skewed tree) traverse function takes O(m*n) time.
    Space complexity : O(n). The depth of the recursion tree can go upto n. n refers to the number of nodes in s.
    */
    public boolean isEqual(TreeNode s, TreeNode t) {
        if(s==null && t==null)
            return true;
        if(s==null || t==null)
            return false;
        return s.val == t.val && isEqual(s.left, t.left) && isEqual(s.right, t.right);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s,t);
    }

    private boolean traverse(TreeNode s, TreeNode t) {
        return isEqual(s, t) || (s != null && (traverse(s.left, t) || traverse(s.right, t)) );
    }

    /* Another Approach:
    check if preOrder traveral of t is substring of preOrder traversal of s 
    */
    HashSet < String > trees = new HashSet < > ();
    public boolean isSubtreeApp2(TreeNode s, TreeNode t) {
        String tree1 = preorder(s, true);
        String tree2 = preorder(t, true);
        return tree1.indexOf(tree2) >= 0;
    }
    public String preorder(TreeNode t, boolean left) {
        if (t == null) {
            if (left)
                return "lnull";
            else
                return "rnull";
        }
        return "#"+t.val + " " +preorder(t.left, true)+" " +preorder(t.right, false);
    }

    public static void main(String[] args) {
        IsSubtree sol = new IsSubtree();
        TreeNode sRight = new TreeNode(7,new TreeNode(8),new TreeNode(9));
        TreeNode sleft = new TreeNode(5,new TreeNode(6),new TreeNode(4));
        TreeNode s = new TreeNode(10,sleft,sRight);
        TreeNode t = new TreeNode(5,new TreeNode(6),new TreeNode(4));
        System.out.println("Solution: "+sol.isSubtree(s, t));

        TreeNode s1 = new TreeNode(1,new TreeNode(1),null);
        TreeNode t1 = new TreeNode(1);
        System.out.println("Solution: "+sol.isSubtree(s1, t1));


        System.out.println("Solution: "+sol.isSubtree(null, null));
    }

}