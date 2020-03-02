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
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
        queue.add(root);
        queue.add(null);
        LinkedList<Integer> deque = new LinkedList<Integer>();
        boolean leftToRight = true;
        while(queue.size()>0) {
            TreeNode t = queue.poll();
            if(t != null) {
                if(leftToRight) {
                    deque.addLast(t.val);
                }
                else {
                    deque.addFirst(t.val);
                }
                if(t.left != null)
                    queue.add(t.left);
                if(t.right != null)
                    queue.add(t.right);
            } else {
                result.add(deque);
                deque = new LinkedList<Integer>();
                if(queue.size() > 0)
                    queue.add(null);
                leftToRight = !leftToRight;
            }
        }
        
        return result;
        
    }
}