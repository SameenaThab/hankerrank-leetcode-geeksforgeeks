// import org.junit.Test;
// import org.junit.runner.JUnitCore;
// import org.junit.runner.Result;
// import org.junit.runner.notification.Failure;

import java.util.*;
// import static org.junit.Assert.*;


/* 
            1
        2           3
    4       5   6       7

      , 4, 5
1 
2
4

*/
public class SuperBalanced {

    public static class Pair {
        BinaryTreeNode node;
        int depth;

        Pair(BinaryTreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

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

    public static boolean isBalanced(BinaryTreeNode treeRoot) {
        
        List<Integer>  depths = new ArrayList<Integer>();

        return isBalancedDFS(treeRoot,0,depths);
    }

    // DFS is better than BFS because we reach the root faster in DFS than BFS.Also no extra space for queue
    private static boolean isBalancedDFS(BinaryTreeNode root, int currDepth, List<Integer> depths) {
        if(root == null)
            return true;
        // root is a leaf node
        if(root.left == null && root.right == null) {
            if(!depths.contains(currDepth)) {
                depths.add(currDepth);
                // more than 2 different depths bcoz not balanced atleast two depths will be more than 2
                // depth[0]-dept[1]>0 => not balanced
                if(depths.size() > 2 || (depths.size() == 2 && Math.abs(depths.get(0)-depths.get(1)) > 1) ) {
                    return false;
                } 
            } 
            return true;           
        }
        else
            return isBalancedDFS(root.left, currDepth+1, depths) && isBalancedDFS(root.right, currDepth+1, depths);
    }

    // DFS is better than BFS because we reach the root faster in DFS than BFS.Also no extra space for queue
    private static boolean isBalancedBFS(BinaryTreeNode root) {
        if(root == null)
            return true;
        Queue<Pair> queue = new LinkedList<Pair>();
        List<Integer>  depths = new ArrayList<Integer>();
        queue.add(new Pair(root,0));
        while(!queue.isEmpty()) {
            Pair currPair = queue.poll();
            BinaryTreeNode currNode = currPair.node;
            int currDepth = currPair.depth;
            if(currNode.left == null && currNode.right == null) {
                if(!depths.contains(currDepth)) {
                    depths.add(currDepth);
                    // more than 2 different depths bcoz not balanced atleast two depths will be more than 2
                    // depth[0]-dept[1]>0 => not balanced
                    if(depths.size() > 2 || (depths.size() == 2 && Math.abs(depths.get(0)-depths.get(1)) > 1) ) {
                        return false;
                    } 
                }           
            }   
            else {
                if(currNode.left != null)
                    queue.add(new Pair(currNode.left,currDepth+1));
                if(currNode.right != null)
                    queue.add(new Pair(currNode.right,currDepth+1));
            }         
        }
        return true;
    }

    public static void main(String[] args) {
        SuperBalanced sol = new SuperBalanced();
        final BinaryTreeNode root = new BinaryTreeNode(5);
        final BinaryTreeNode a = root.insertLeft(8);
        final BinaryTreeNode b = root.insertRight(6);
        a.insertLeft(1);
        a.insertRight(2);
        b.insertLeft(3);
        b.insertRight(4);
        System.out.println("Must be true:"+isBalanced(root));
        System.out.println("Must be true:"+isBalancedBFS(root));

        final BinaryTreeNode root2 = new BinaryTreeNode(3);
        root2.insertLeft(4).insertLeft(1);
        root2.insertRight(2).insertRight(9);
        System.out.println("Must be true:"+isBalanced(root2));
        System.out.println("Must be true:"+isBalancedBFS(root2));

        final BinaryTreeNode root3 = new BinaryTreeNode(6);
        root3.insertLeft(1);
        root3.insertRight(0).insertRight(7);
        final boolean result = isBalanced(root3);
        System.out.println("Must be true:"+isBalanced(root3));
        System.out.println("Must be true:"+isBalancedBFS(root3));

        final BinaryTreeNode root4 = new BinaryTreeNode(6);
        root4.insertLeft(1);
        root4.insertRight(0).insertRight(7).insertRight(8);
        System.out.println("Must be false: "+isBalanced(root4));
        System.out.println("Must be false: "+isBalancedBFS(root4));

        final BinaryTreeNode root5 = new BinaryTreeNode(1);
        root5.insertLeft(5);
        final BinaryTreeNode x = root5.insertRight(9);
        x.insertLeft(8).insertLeft(7);
        x.insertRight(5);
        System.out.println("Must be false: "+isBalanced(root5));
        System.out.println("Must be false: "+isBalancedBFS(root5));

        final BinaryTreeNode root6 = new BinaryTreeNode(1);
        final BinaryTreeNode y = root6.insertLeft(2);
        y.insertLeft(3);
        y.insertRight(7).insertRight(8);
        root6.insertRight(4).insertRight(5).insertRight(6).insertRight(9);
        System.out.println("Must be false: "+isBalanced(root6));
        System.out.println("Must be false: "+isBalancedBFS(root6));

        final BinaryTreeNode root7 = new BinaryTreeNode(1);
        final BinaryTreeNode c = root7.insertLeft(2);
        final BinaryTreeNode d = c.insertLeft(3);
        c.insertRight(4);
        d.insertLeft(5);
        d.insertRight(6);
        root7.insertRight(7).insertRight(8).insertRight(9).insertRight(10);
        System.out.println("Must be false:"+isBalanced(root7));
        System.out.println("Must be false:"+isBalancedBFS(root7));

        final BinaryTreeNode root8 = new BinaryTreeNode(1);
        System.out.println("Must be true:"+isBalanced(root8));
        System.out.println("Must be true:"+isBalancedBFS(root8));

        final BinaryTreeNode root9 = new BinaryTreeNode(1);
        root9.insertRight(2).insertRight(3).insertRight(4);
        System.out.println("Must be true:"+isBalanced(root9));
        System.out.println("Must be true:"+isBalancedBFS(root9));

    }
















    // tests

    // @Test
    // public void fullTreeTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(5);
    //     final BinaryTreeNode a = root.insertLeft(8);
    //     final BinaryTreeNode b = root.insertRight(6);
    //     a.insertLeft(1);
    //     a.insertRight(2);
    //     b.insertLeft(3);
    //     b.insertRight(4);
    //     final boolean result = isBalanced(root);
    //     assertTrue(result);
    // }

    // @Test
    // public void bothLeavesAtTheSameDepthTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(3);
    //     root.insertLeft(4).insertLeft(1);
    //     root.insertRight(2).insertRight(9);
    //     final boolean result = isBalanced(root);
    //     assertTrue(result);
    // }

    // @Test
    // public void leafHeightsDifferByOneTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(6);
    //     root.insertLeft(1);
    //     root.insertRight(0).insertRight(7);
    //     final boolean result = isBalanced(root);
    //     assertTrue(result);
    // }

    // @Test
    // public void leafHeightsDifferByTwoTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(6);
    //     root.insertLeft(1);
    //     root.insertRight(0).insertRight(7).insertRight(8);
    //     final boolean result = isBalanced(root);
    //     assertFalse(result);
    // }

    // @Test
    // public void bothSubTreesSuperbalancedTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(1);
    //     root.insertLeft(5);
    //     final BinaryTreeNode b = root.insertRight(9);
    //     b.insertLeft(8).insertLeft(7);
    //     b.insertRight(5);
    //     final boolean result = isBalanced(root);
    //     assertFalse(result);
    // }

    // @Test
    // public void bothSubTreesSuperbalancedTwoTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(1);
    //     final BinaryTreeNode a = root.insertLeft(2);
    //     a.insertLeft(3);
    //     a.insertRight(7).insertRight(8);
    //     root.insertRight(4).insertRight(5).insertRight(6).insertRight(9);
    //     final boolean result = isBalanced(root);
    //     assertFalse(result);
    // }

    // @Test
    // public void threeLeavesAtDifferentLevelsTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(1);
    //     final BinaryTreeNode a = root.insertLeft(2);
    //     final BinaryTreeNode b = a.insertLeft(3);
    //     a.insertRight(4);
    //     b.insertLeft(5);
    //     b.insertRight(6);
    //     root.insertRight(7).insertRight(8).insertRight(9).insertRight(10);
    //     final boolean result = isBalanced(root);
    //     assertFalse(result);          
    // }

    // @Test
    // public void onlyOneNodeTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(1);
    //     final boolean result = isBalanced(root);
    //     assertTrue(result);
    // }

    // @Test
    // public void treeIsLinkedListTest() {
    //     final BinaryTreeNode root = new BinaryTreeNode(1);
    //     root.insertRight(2).insertRight(3).insertRight(4);
    //     final boolean result = isBalanced(root);
    //     assertTrue(result);
    // }




}