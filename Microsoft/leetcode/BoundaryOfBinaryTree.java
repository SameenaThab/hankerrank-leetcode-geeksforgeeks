/* 
https://leetcode.com/problems/boundary-of-binary-tree/

Approach1:
One simple approach is to divide this problem into three subproblems- left boundary, leaves and right boundary.

Left Boundary: 
We keep on traversing the tree towards the left and keep on adding the nodes in the resres array, provided the current node isn't a leaf node.
If at any point, we can't find the left child of a node, but its right child exists, we put the right child in the resres and continue the process.

Leaf Nodes:
We make use of a recursive function addLeaves(res,root), in which we change the root node for every recursive call.
If the current root node happens to be a leaf node, it is added to the resres array.
Otherwise, we make the recursive call using the left child of the current node as the new root.
After this, we make the recursive call using the right child of the current node as the new root.

Right Boundary:
We perform the same process as the left boundary.
But, this time, we traverse towards the right.
If the right child doesn't exist, we move towards the left child.
Also, instead of putting the traversed nodes in the resres array, we push them over a stack during the traversal.
After the complete traversal is done, we pop the element from over the stack and append them to the resres array.

Time : O(n)
Space: O(n)
eg1 : root = [1,null,2,3,4]
            1
        2       3
    4         5     6  
output : 1,2,4,5,6,3,1 -> 1,2(left boundary),4,5,6(bottom boundary leaves),3,1(right boundary)
eg 2: root = [1,null,2,3,4]
                1
        null            2
                    3       4
output: 1,3,4,2 -> 1(left boundary),3,4(bottom/leaves),2(right boundary)

*/
import java.util.*;
class BoundaryOfBinaryTree {

     public class TreeNode {
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
    public List<Integer> boundaryOfBinaryTree1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        if (!isLeafNode(root)) {
            result.add(root.val);
        }
        //adding  left boundary
        TreeNode t = root.left;
        while(t != null) {
            if(!isLeafNode(t))
                result.add(t.val);
            if(t.left != null) {
                t=t.left;
            } else {
                t=t.right;
            }
        }
        //all leaf node
        bottomBoundary(root,result);
        // adding right boundary
        Stack<Integer> st = new Stack<Integer>();
        t = root.right;
        while(t != null) {
            if(!isLeafNode(t)) {
                st.push(t.val);
            }
            if(t.right != null) {
                t=t.right;
            } else {
                t=t.left;
            }
        }
        while(!st.isEmpty()) {
            result.add(st.pop());
        }
        return result;
    }
    
    public void bottomBoundary(TreeNode root,List<Integer> result) {
        if(root == null)
            return;
        if(isLeafNode(root)) { 
            result.add(root.val);
        }
        bottomBoundary(root.left,result);
        bottomBoundary(root.right,result);
    }
    
    public boolean isLeafNode(TreeNode root) {
        return root.left == null && root.right == null;
    }


    /* Time : O(N),Space:O(N) recursive stack
    Approach2:  Using PreOrder Traversal 
                        A
                B                           C
                    E               F              G
                J       K       L       M       N
    Before we dive into this approach, let's look at the preorder traversal of a simple Binary Tree as shown below:
    In order to distinguish between the various kinds of nodes, we make use of a flagflag as follows:
    Flag=0: Root Node.
    Flag=1: Left Boundary Node.
    Flag=2: Right Boundary Node.
    Flag=3: Others(Middle Node).
    We make use of three lists left_boundary, right_boundary, leaves to store the appropriate nodes and append the three lists at the end.
    We go for the normal preorder traversal, but while calling the recursive function for preorder traversal using the left child or the right child of the current node, we also pass the flag information indicating the type of node that the current child behaves like.
    For obtaining the flag information about the left child of the current node,
    we make use of the function leftChildFlag(node, flag). In the case of a left child, the following cases are possible:
        The current node is a left boundary node: In this case, the left child will always be a left boundary node. e.g. relationship between E & J in the above figure.
        The current node is a root node: In this case, the left child will always be a left boundary node. e.g. relationship between A & B in the above figure.
        The current node is a right boundary node: In this case, if the right child of the current node doesn't exist, the left child always acts as the right boundary node. e.g. G & N. But, if the right child exists, the left child always acts as the middle node. e.g. C & F.
    Similarly, for obtaining the flag information about the right child of the current node, we make use of the function rightChildFlag(node, flag). In the case of a right child, the following cases are possible, as can be verified by looking at the figure above:
        The current node is a right boundary node: In this case, the right child will always be a right boundary node. e.g. relationship between C & G in the above figure.
        The current node is a root node: In this case, the right child will always be a left boundary node. e.g. relationship between A & C in the above figure.
        The current node is a left boundary node: In this case, if the left child of the current node doesn't exist, the right child always acts as the left boundary node. e.g. B & E. But, if the left child exists, the left child always acts as the middle node.
    */

    /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> left_boundary = new LinkedList<> (), right_boundary = new LinkedList <> (), leaves = new LinkedList <> ();
        preorder(root, left_boundary, right_boundary, leaves, 0);
        left_boundary.addAll(leaves);
        left_boundary.addAll(right_boundary);
        return left_boundary;
    }

    public boolean isLeaf(TreeNode cur) {
        return (cur.left == null && cur.right == null);
    }

    public boolean isRightBoundary(int flag) {
        return (flag == 2);
    }

    public boolean isLeftBoundary(int flag) {
        return (flag == 1);
    }

    public boolean isRoot(int flag) {
        return (flag == 0);
    }

    public int leftChildFlag(TreeNode cur, int flag) {
        if (isLeftBoundary(flag) || isRoot(flag))
            return 1;
        else if (isRightBoundary(flag) && cur.right == null)
            return 2;
        else return 3;
    }

    public int rightChildFlag(TreeNode cur, int flag) {
        if (isRightBoundary(flag) || isRoot(flag))
            return 2;
        else if (isLeftBoundary(flag) && cur.left == null)
            return 1;
        else return 3;
    }

    public void preorder(TreeNode cur, List < Integer > left_boundary, List < Integer > right_boundary, List < Integer > leaves, int flag) {
        if (cur == null)
            return;
        if (isRightBoundary(flag))
            right_boundary.add(0, cur.val);
        else if (isLeftBoundary(flag) || isRoot(flag))
            left_boundary.add(cur.val);
        else if (isLeaf(cur))
            leaves.add(cur.val);
        preorder(cur.left, left_boundary, right_boundary, leaves, leftChildFlag(cur, flag));
        preorder(cur.right, left_boundary, right_boundary, leaves, rightChildFlag(cur, flag));
    }
}