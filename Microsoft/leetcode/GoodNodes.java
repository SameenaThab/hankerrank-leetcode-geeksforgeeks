class GoodNodes {

    public class TreeNode {
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

    public int goodNodes(TreeNode root) {
        if(root == null)
            return 0;
        return 1+goodNodes(root.left,root.val)+goodNodes(root.right, root.val);
    }

    private int goodNodes(TreeNode node, int maxTillNow) {
        if(node == null)
            return 0;
        if(node.val<maxTillNow)
            return goodNodes(node.left,maxTillNow)+goodNodes(node.right, maxTillNow);
        else  
            return 1+goodNodes(node.left,node.val)+goodNodes(node.right, node.val);
    }
}