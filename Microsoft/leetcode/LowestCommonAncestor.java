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

    public TreeNode lowestCommonAncestorHashMap(TreeNode root, TreeNode p, TreeNode q) {
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

    /* 
    Approach
    Intuition
    In the previous approach, we come across the LCA during the backtracking process.
    We can get rid of the backtracking process itself.
    In this approach we always have a pointer to the probable LCA and the moment we find both the nodes we return the pointer as the answer.

    Algorithm
    1.Start with root node.
    2.Put the (root, root_state) on to the stack. root_state defines whether one of the children or both children of root are left for traversal.
    3.While the stack is not empty, peek into the top element of the stack represented as (parent_node, parent_state).
    4.Before traversing any of the child nodes of parent_node we check if the parent_node itself is one of p or q.
    5.First time we find either of p or q, set a boolean flag called one_node_found to True. Also start keeping track of the lowest common ancestors by keeping a note of the top index of the stack in the variable LCA_index. Since all the current elements of the stack are ancestors of the node we just found.
    6.The second time parent_node == p or parent_node == q it means we have found both the nodes and we can return the LCA node.
    7.Whenever we visit a child of a parent_node we push the (parent_node, updated_parent_state) onto the stack. We update the state of the parent since a child/branch has been visited/processed and accordingly the state changes.
    8.A node finally gets popped off from the stack when the state becomes BOTH_DONE implying both left and right subtrees have been pushed onto the stack and processed. If one_node_found is True then we need to check if the top node being popped could be one of the ancestors of the found node. In that case we need to reduce LCA_index by one. Since one of the ancestors was popped off.
    
    Time Complexity : O(N), where N is the number of nodes in the binary tree.
    In the worst case we might be visiting all the nodes of the binary tree.
    The advantage of this approach is that we can prune backtracking.
    We simply return once both the nodes are found.

    Space Complexity : O(N). 
    In the worst case the space utilized by stack would be N since the height of a skewed binary tree could be N.
    
    */
    // Three static flags to keep track of post-order traversal.
    
    // Both left and right traversal pending for a node.
    // Indicates the nodes children are yet to be traversed.
    private static int BOTH_PENDING = 2;

    // Left traversal done.
    private static int LEFT_DONE = 1;

    // Both left and right traversal done for a node.
    // Indicates the node can be popped off the stack.
    private static int BOTH_DONE = 0;
    
    public TreeNode lowestCommonAncestorStack(TreeNode root, TreeNode p, TreeNode q) {

        Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();

        // Initialize the stack with the root node.
        stack.push(new Pair<TreeNode, Integer>(root, Solution.BOTH_PENDING));

        // This flag is set when either one of p or q is found.
        boolean one_node_found = false;

        // This is used to keep track of the LCA.
        TreeNode LCA = null;

        // Child node
        TreeNode child_node = null;

        // We do a post order traversal of the binary tree using stack
        while (!stack.isEmpty()) {

            Pair<TreeNode, Integer> top = stack.peek();
            TreeNode parent_node = top.getKey();
            int parent_state = top.getValue();

            // If the parent_state is not equal to BOTH_DONE,
            // this means the parent_node can't be popped off yet.
            if (parent_state != Solution.BOTH_DONE) {

                // If both child traversals are pending
                if (parent_state == Solution.BOTH_PENDING) {

                    // Check if the current parent_node is either p or q.
                    if (parent_node == p || parent_node == q) {

                        // If one_node_found was set already, this means we have found
                        // both the nodes.
                        if (one_node_found) {
                            return LCA;
                        } else {
                            // Otherwise, set one_node_found to True,
                            // to mark one of p and q is found.
                            one_node_found = true;

                            // Save the current top element of stack as the LCA.
                            LCA = stack.peek().getKey();
                        }
                    }

                    // If both pending, traverse the left child first
                    child_node = parent_node.left;
                } else {
                    // traverse right child
                    child_node = parent_node.right;
                }

                // Update the node state at the top of the stack
                // Since we have visited one more child.
                stack.pop();
                stack.push(new Pair<TreeNode, Integer>(parent_node, parent_state - 1));

                // Add the child node to the stack for traversal.
                if (child_node != null) {
                    stack.push(new Pair<TreeNode, Integer>(child_node, Solution.BOTH_PENDING));
                }
            } else {

                // If the parent_state of the node is both done,
                // the top node could be popped off the stack.
                // Update the LCA node to be the next top node.
                //Since this top is ancestor but only one found then , 
                //there should be ancestor on evenmore upper level
                if (LCA == stack.pop().getKey() && one_node_found) {
                    LCA = stack.peek().getKey();
                }

            }
        }

        return null;
    }
}