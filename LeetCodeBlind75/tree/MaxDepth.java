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


class MaxDepth {
    
/* 
    Time comp: O(n)
    Space Comp = O(n) 
*/
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null)
            queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            depth++;
            int n = queue.size();
            for(int i=0;i<n;i++) {
                TreeNode curr = queue.poll();
                if(curr.left != null)
                    queue.add(curr.left);
                if(curr.right != null)
                    queue.add(curr.right);
            }
        }
        return depth;
    }
/* 
Optimal:
    Time comp: O(n)
    Space Comp = O(maxDepth) stack grows till maxDepth
*/
    public int maxDepthRecursive(TreeNode root) {
        if(root == null)
            return 0;
        return 1+Math.max(maxDepthRecursive(root.left),maxDepthRecursive(root.right));
    }

    public static void main(String[] args) {
        MaxDepth sol = new MaxDepth();
        TreeNode right = new TreeNode(20,new TreeNode(15),new TreeNode(7));
        TreeNode root = new TreeNode(3,new TreeNode(9),right);
        System.out.println("Solution: "+sol.maxDepth(root));
        System.out.println("Optimal Solution: "+sol.maxDepthRecursive(root));
    }
}