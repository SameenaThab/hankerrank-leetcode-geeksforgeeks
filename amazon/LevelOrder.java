/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/506/
class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
        int curr = 0;
        q.add(root);
        while(!q.isEmpty()) {
            result.add(new ArrayList<Integer>());
            int size = q.size();
            for(int i=0;i<size;i++) {
                TreeNode t = q.poll();                 
                result.get(curr).add(t.val);           
                if(t.left != null) q.add(t.left);
                if(t.right != null) q.add(t.right);
            }
            curr++;
        }
        return result;
    }

    public List<List<Integer>> levelOrder_OtherSolution(TreeNode root) {
        Queue<Pair<TreeNode,Integer>> q = new LinkedList<Pair<TreeNode,Integer>>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
        q.add(new Pair(root,0));
        while(!q.isEmpty()) {
            Pair<TreeNode,Integer> pair = q.poll();
            TreeNode t = pair.getKey();
            int index = pair.getValue();
            //the size of queue will be equal to current running level+1
            List<Integer> list = result.size() == index+1?result.get(index):new ArrayList<Integer>();
            if(t != null) {   
                list.add(t.val);           
                if(t.left != null) q.add(new Pair(t.left,index+1));
                if(t.right != null) q.add(new Pair(t.right,index+1));
            }
            if(result.size() == index+1)
                result.set(index,list);
            else
                result.add(list);
        }
        return result;
    }
}