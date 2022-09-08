/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2980/
class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
       Queue<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<Integer> levelList = new LinkedList<Integer>();
        queue.add(root);
        boolean left = true;
        while(!queue.isEmpty()) {
            int n = queue.size();
            for(int i=0;i<n;i++) {
                TreeNode curr = queue.poll();
                if(left) {
                   levelList.addLast(curr.val); 
                } else {
                    levelList.addFirst(curr.val);
                }
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
            result.add(levelList);
            levelList = new LinkedList<Integer>();
            left = !left;
        }
        return result;
    }
}