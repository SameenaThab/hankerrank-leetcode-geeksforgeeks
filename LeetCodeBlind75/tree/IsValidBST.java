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

class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }

    private boolean isValidBST(TreeNode root, Integer minValue, Integer maxValue) {
        if(root == null)
            return true;
        if( (minValue != null && root.val <= minValue) || (maxValue != null && root.val >= maxValue) )
            return false;
        return isValidBST(root.left,minValue,root.val) && isValidBST(root.right,root.val,maxValue);
    }

    public static void main(String[] args) {
        IsValidBST sol = new IsValidBST();
        TreeNode right = new TreeNode(9,new TreeNode(8),new TreeNode(10));
        TreeNode left = new TreeNode(5,new TreeNode(4),new TreeNode(6));
        TreeNode root = new TreeNode(7,left,right);

        System.out.println("Solution: "+sol.isValidBST(root));

        System.out.println("Solution: "+sol.isValidBST(new TreeNode(9,new TreeNode(10),new TreeNode(5))));
    }
}