import java.util.*;

public class LevelOrder {
    /*
  https://www.interviewbit.com/problems/level-order/    */
    class Pair {
        TreeNode node;
        int level;
        
        Pair(TreeNode node,int level) {
            this.node = node;
            this.level = level;
        }
    }
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(A,0));
        while(!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int level = pair.level;
            if(level>=result.size()) {
                result.add(new ArrayList<Integer>());
            }
            result.get(level).add(node.val);
            if(node.left != null)
                queue.add(new Pair(node.left,level+1));
            if(node.right != null)
                queue.add(new Pair(node.right,level+1));
        }
        
        return result;
    }
}