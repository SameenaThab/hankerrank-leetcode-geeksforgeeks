import java.util.*;
class LowestCommonAncestor {

/* 
Interview Question
The coding half of this interview was a slightly different take on a popular question. 
But, instead of a normal binary tree, it was inverted so children only have 1 pointer back to their parent instead of pointers to their children. I actually thought this was pretty straight-forward. My first solution was just to use a HashSet of seen nodes and have the pointers increment upwards back and forth until one node hits a seen node and then you know that's the LCA. Obvioulsy though that's O(N) space. When asked how to do it in O(1) space I gave 2 solutions.
*/
class TreeNodeInverse {
    int val;
    TreeNodeInverse parent;
    TreeNodeInverse(int val) {
       this.val = val;
    }
}

public TreeNodeInverse LCAInvertedNode(TreeNodeInverse root,TreeNodeInverse p,TreeNodeInverse q) {
    // root node has null parent, check if any give node is root, then retunr root
    if(p.parent == null || q.parent == null)
        return root;
    return helper(root,p,q);
}

public TreeNodeInverse helper(TreeNodeInverse root,TreeNodeInverse p,TreeNodeInverse q) {
    // root node has null parent, check if any give node is root, then retunr root
    if(p == null || q == null)
        return null;
    TreeNodeInverse temp = p;
    int pDepth = 0;
    //this while loop is only possible if both p and q are on same level.
    // To bring them to same level we can calculate depths and move the lowest node to the diff in depth no of times
    while(p == q) {
        p=p.parent;
        q=q.parent;
    }
}

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