import java.util.*;

// import org.junit.Test;
// import org.junit.runner.JUnitCore;
// import org.junit.runner.Result;
// import org.junit.runner.notification.Failure;

// import static org.junit.Assert.*;

public class BinarySearchTreeCheck {

    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }

    public static boolean isBinarySearchTree(BinaryTreeNode root) {                
        return isBinarySearchTree(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private static boolean isBinarySearchTree(BinaryTreeNode root, int minValue, int maxValue) {
        if(root == null)
            return true;
        return (root.value > minValue && root.value < maxValue) && isBinarySearchTree(root.left,minValue,root.value) && isBinarySearchTree(root.right,root.value,maxValue);    
    }

    private static class NodeBounds {

        BinaryTreeNode node;
        int lowerBound;
        int upperBound;
    
        NodeBounds(BinaryTreeNode node, int lowerBound, int upperBound) {
            this.node = node;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }
    
    public static boolean isBinarySearchTreeBFS(BinaryTreeNode root) {
    
        // start at the root, with an arbitrarily low lower bound
        // and an arbitrarily high upper bound
        Deque<NodeBounds> nodeAndBoundsStack = new ArrayDeque<>();
        nodeAndBoundsStack.push(new NodeBounds(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    
        // depth-first traversal
        while (!nodeAndBoundsStack.isEmpty()) {
            NodeBounds nb = nodeAndBoundsStack.pop();
            BinaryTreeNode node = nb.node;
            int lowerBound = nb.lowerBound;
            int upperBound = nb.upperBound;
    
            // if this node is invalid, we return false right away
            if (node.value <= lowerBound || node.value >= upperBound) {
                return false;
            }
    
            if (node.left != null) {
                // this node must be less than the current node
                nodeAndBoundsStack.push(new NodeBounds(node.left, lowerBound, node.value));
            }
            if (node.right != null) {
                // this node must be greater than the current node
                nodeAndBoundsStack.push(new NodeBounds(node.right, node.value, upperBound));
            }
        }
    
        // if none of the nodes were invalid, return true
        // (at this point we have checked all nodes)
        return true;
    }

    public static void main(String[] args) {
        BinarySearchTreeCheck sol = new BinarySearchTreeCheck();
    }
















    // tests

    // @Test
    // public void validFullTreeTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     final BinaryTreeNode a = root.insertLeft(30);
    //     a.insertLeft(10);
    //     a.insertRight(40);
    //     final BinaryTreeNode b = root.insertRight(70);
    //     b.insertLeft(60);
    //     b.insertRight(80);
    //     final boolean result = isBinarySearchTree(root);
    //     assertTrue(result);
    // }

    // @Test
    // public void bothSubtreesValidTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     final BinaryTreeNode a = root.insertLeft(30);
    //     a.insertLeft(20);
    //     a.insertRight(60);
    //     final BinaryTreeNode b = root.insertRight(80);
    //     b.insertLeft(70);
    //     b.insertRight(90);
    //     final boolean result = isBinarySearchTree(root);
    //     assertFalse(result);
    // }

    // @Test
    // public void descendingLinkedListTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     root.insertLeft(40).insertLeft(30).insertLeft(20).insertLeft(10);
    //     final boolean result = isBinarySearchTree(root);
    //     assertTrue(result);
    // }

    // @Test
    // public void outOfOrderLinkedListTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     root.insertRight(70).insertRight(60).insertRight(80);
    //     final boolean result = isBinarySearchTree(root);
    //     assertFalse(result);
    // }

    // @Test
    // public void oneNodeTreeTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     final boolean result = isBinarySearchTree(root);
    //     assertTrue(result);
    // }


}