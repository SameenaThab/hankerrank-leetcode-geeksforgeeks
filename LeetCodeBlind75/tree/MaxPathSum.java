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

class MaxPathSum {
    /* 
    https://leetcode.com/problems/binary-tree-maximum-path-sum/
    
    path has edge between nodes

    eg1:         2
            1       3    => 1+2+3 = 6

    eg2:          -10
            -2          9
                    8       1   =>  longest path is 8+9+1
    
    eg3:           10
            2               9
                        8       1     => longest path = 2+10+9+8 = 29

    root with both its left and right children has the possibility of becoming max path,
    only when both left and right nodes are leaves like in eg1 and 2
    for all others it is a branch root+MAX(left_gain,right_gain)
    To calculate gain we use another method max_gain
    max_gain = root.val + max(max_gain(left),max_gain(right)) 
    max_gain = 0 , if root == null
    As we calculate max_gain, we update max_path, which is a global variable

    */
    int maxPath = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxPath;
    }

    /* 
    Time complexity: O(N), where N is number of nodes, since we visit each node not more than 2 times.

    Space complexity:O(H), where H is a tree height, to keep the recursion stack. In the average case of balanced tree, the tree height H =logN, in the worst case of skewed tree, H = N.
    */
    private int maxGain(TreeNode root) {
        if(root == null)
            return 0;
        int leftPath = Math.max(maxGain(root.left),0); //negative paths will be eliminated
        int rightPath = Math.max(maxGain(root.right),0);

        // max path is sum of root and both left and right, 
        // for subtree with left and right leaf nodes, it is root+leftLeaf+rightLeaf;
        maxPath = Math.max(maxPath,root.val+leftPath+rightPath); 
        
        // for recursion :
        // return the max gain if continue the same path
        return root.val+Math.max(leftPath,rightPath);
    }

    public static void main(String[] args) {
        MaxPathSum sol = new MaxPathSum();
        TreeNode right = new TreeNode(9,new TreeNode(8),new TreeNode(1));
        TreeNode left = new TreeNode(-2);
        TreeNode root = new TreeNode(-10,left,right);
        System.out.println("Solution: "+sol.maxPathSum(root));

        MaxPathSum sol2 = new MaxPathSum();
        TreeNode root2 = new TreeNode(2,new TreeNode(1),new TreeNode(3));
        System.out.println("Solution2: "+sol2.maxPathSum(root2));
    }   
}