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

class LevelOrder {

    /* 
    Time complexity : O(N) since each node is processed exactly once.
    Space complexity : O(N) to keep the output structure which contains N node values.
    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> subList;
        if(root != null)
            queue.add(root);
        while(!queue.isEmpty()) {
            int n = queue.size();
            subList = new ArrayList<Integer>();
            for(int i=0;i<n;i++) {
                TreeNode curr = queue.poll();
                subList.add(curr.val);
                if(curr.left != null)
                    queue.add(curr.left);
                if(curr.right != null)
                    queue.add(curr.right);
            }
            result.add(subList);
        }
        return result;
    }

    /* 
    Time complexity : O(N) since each node is processed exactly once.
    Space complexity : O(N) to keep the output structure which contains N node values.
    */
    public void levelOrderDFS(TreeNode root,int level,List<List<Integer>> result) {
        if(root == null)
            return;
        if(result.size() == level) {
            result.add(new ArrayList<Integer>());
        }
        result.get(level).add(root.val);
        levelOrderDFS(root.left,level+1,result);
        levelOrderDFS(root.right,level+1,result);
    }


    public List<List<Integer>>  levelOrderDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        levelOrderDFS(root,0,result);
        return result;
    }

    public static void main(String[] args) {
        LevelOrder sol = new LevelOrder();
        TreeNode right = new TreeNode(9,new TreeNode(8),new TreeNode(10));
        TreeNode left = new TreeNode(5,new TreeNode(4),new TreeNode(6));
        TreeNode root = new TreeNode(20,left,right);
        List<List<Integer>> result = sol.levelOrder(root);
        for(List<Integer> list : result) {
            System.out.println(list);
        }
        List<List<Integer>> result2 = sol.levelOrderDFS(root);
        for(List<Integer> list : result2) {
            System.out.println(list);
        }
    } 
}