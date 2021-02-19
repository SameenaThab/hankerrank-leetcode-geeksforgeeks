// import org.junit.Test;
// import org.junit.runner.JUnitCore;
// import org.junit.runner.Result;
// import org.junit.runner.notification.Failure;

// import static org.junit.Assert.*;

//https://www.interviewcake.com/question/java/second-largest-item-in-bst?course=fc1&section=trees-graphs

public class FindSecondLargestBST {

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

    /* 
     Two cases of finding a second largest element
     case 1: When largest element is a leafnode, then its parent is the second largest element
        eg:     5
            3           8
                    6       9
        9 is the largest element and leaf node , therefore its parent 8 is second largest
     case 2: When largest element has a left subtree (that means no right subtree since its largest)
            Then largest of left sub tree becomes the second largest elemet
        eg:               5
                    4           9
                            7  
                        6       8    
            9 is largest element and 8 is largest element of left subtree 6<-7->8, 
            therefore 8 is second largest element     
    */

    public static int findSecondLargest(BinaryTreeNode rootNode) throws Exception { 
        if(rootNode == null || (rootNode.left == null && rootNode.right == null) )
            throw new Exception("number of nodes must be atleast 2");
        while(rootNode != null) {
            // when left subtree exists for largest node
            if(rootNode.right == null && rootNode.left != null) {
                return findLargest(rootNode.left);
            }
            // current node is parent of largest node
            if(rootNode.right != null && rootNode.right.left == null && rootNode.right.right == null) {
                break;
            }
            rootNode = rootNode.right;
        }
        return rootNode.value;
    }


    private static int findLargest(BinaryTreeNode root) {
        while(root.right != null) {
            root = root.right;
        }
        return root.value;
	}


	public static void main(String[] args) {

    }
}















    // tests

    // @Test
    // public void findSecondLargestTest() throws Exception  {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     final BinaryTreeNode a = root.insertLeft(30);
    //     a.insertLeft(10);
    //     a.insertRight(40);
    //     final BinaryTreeNode b = root.insertRight(70);
    //     b.insertLeft(60);
    //     b.insertRight(80);
    //     final int actual = findSecondLargest(root);
    //     final int expected = 70;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void largestHasLeftChildTest() throws Exception  {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     final BinaryTreeNode a = root.insertLeft(30);
    //     a.insertLeft(10);
    //     a.insertRight(40);
    //     root.insertRight(70).insertLeft(60);
    //     final int actual = findSecondLargest(root);
    //     final int expected = 60;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void largestHasLeftSubtreeTest() throws Exception  {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     final BinaryTreeNode a = root.insertLeft(30);
    //     a.insertLeft(10);
    //     a.insertRight(40);
    //     final BinaryTreeNode b = root.insertRight(70).insertLeft(60);
    //     b.insertLeft(55).insertRight(58);
    //     b.insertRight(65);
    //     final int actual = findSecondLargest(root);
    //     final int expected = 65;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void secondLargestIsRootNodeTest() throws Exception  {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     final BinaryTreeNode a = root.insertLeft(30);
    //     a.insertLeft(10);
    //     a.insertRight(40);
    //     root.insertRight(70);
    //     final int actual = findSecondLargest(root);
    //     final int expected = 50;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void descendingLinkedListTest()  throws Exception {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     root.insertLeft(40).insertLeft(30).insertLeft(20);
    //     final int actual = findSecondLargest(root);
    //     final int expected = 40;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void ascendingLinkedListTest()  throws Exception {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     root.insertRight(60).insertRight(70).insertRight(80);
    //     final int actual = findSecondLargest(root);
    //     final int expected = 70;
    //     assertEquals(expected, actual);
    // }

    // @Test(expected = Exception.class)
    // public void exceptionWithTreeThatHasOneNodeTest()  throws Exception {
    //     final BinaryTreeNode root = new BinaryTreeNode(50);
    //     findSecondLargest(root);
    // }

    // @Test(expected = Exception.class)
    // public void exceptionWithEmptyTreeTest()  throws Exception {
    //     findSecondLargest(null);
    // }


}