import java.util.*;
class LowestCommonAncestor {
/* 
create HashMap<Child,Parent>
Find parents for all nodes until you find parents for both p and q nodes
Now find all ancestors of p by updating p to parent of p in a loop
Now that you have ancestors of p , until u have q in ancestors of p , keep updating q to parent of q
once you have q's ancestor in ancestors of p, we have the answer.

Time Complexity : O(N), where N is the number of nodes in the binary tree. In the worst case we might be visiting all the nodes of the binary tree.

Space Complexity : O(N). In the worst case space utilized by the stack, the parent pointer dictionary and the ancestor set, would be NN each, since the height of a skewed binary tree could be N.

*/
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode,TreeNode> parentMap = new HashMap<TreeNode,TreeNode>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        parentMap.put(root,null);

        // Find parents for all nodes until you find parents for both p and q nodes
        while(!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode curr = queue.poll();
            if(curr.left!=null){
                parentMap.put(curr.left,curr);
                queue.add(curr.left);
            }
            if(curr.right!=null){
                parentMap.put(curr.right,curr);
                queue.add(curr.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<TreeNode>();
        while(p!=null) {
            ancestors.add(p);
            p=parentMap.get(p);
        }
        while(!ancestors.contains(q)){
            q=parentMap.get(q);
        }
        return q;
    }
}