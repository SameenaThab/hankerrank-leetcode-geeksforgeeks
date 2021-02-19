
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
// https://leetcode.com/problems/same-tree/
class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        else if(p == null || q == null) {
            return false;
        }
        else 
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        SameTree sol = new SameTree();
        TreeNode p = new TreeNode(1,new TreeNode(2),new TreeNode(3));
        TreeNode q = new TreeNode(1,new TreeNode(2),new TreeNode(3));
        System.out.println("Solution: "+sol.isSameTree(p,q));

        p = new TreeNode(1,new TreeNode(2),null);
        q = new TreeNode(1,null,new TreeNode(2));
        System.out.println("Solution: "+sol.isSameTree(p,q));
    }

}