/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * https://leetcode.com/problems/binary-tree-right-side-view/solution/
 */
class RightSideView {

    /* 
    BFS approach, last element in queue for every level is right side view of that level
    Time: O(n)
    Space: O(d) , where d is diameter of tree, worst case is N for complete tree
    */

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightView = new ArrayList<>();
        if(root == null)
            return rightView;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                TreeNode curr = queue.poll();
                if(i==size-1) {
                    rightView.add(curr.val);
                }
                if(curr.left != null)
                    queue.add(curr.left);
                if(curr.right != null)
                    queue.add(curr.right);
            }
        }
        return rightView;
    }

    /* 
    If we use DFS we supply level as argument.
    Unlike traditional treeTraversal we call DFS with right node first before left node, since we are collecting rightView
    if rightView List size = level, then we enetered new level and we will add the node(which is rightmost as we traverse right node first)
    */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        
        List<Integer> rightView = new ArrayList<>();
        if(root == null)
            return rightView;
        dfs(root,rightView,0);
        return rightView;
    }
    
    void dfs(TreeNode node,List<Integer> rightView,int level) {
        if(rightView.size() == level)
            rightView.add(node.val);
        if(node.right != null)
            dfs(node.right,rightView,level+1);
        if(node.left != null) {
            dfs(node.left,rightView,level+1);
        }
    }
}