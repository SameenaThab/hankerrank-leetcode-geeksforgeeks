import java.util.*;
import java.math.*;
import java.io.*;

/* 
Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
*/

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("hello world");
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode leftLeft = new TreeNode(1);
        TreeNode leftRight = new TreeNode(3);
        left.left = leftLeft;
        left.right = leftRight;
        root.left = left;
        TreeNode right = new TreeNode(6);
        TreeNode rightLeft = new TreeNode(5);
        TreeNode rightRight = new TreeNode(7);
        right.left = rightLeft;
        right.right = rightRight;
        root.right = right;     
        List<List<TreeNode>> result = sol.depthlists(root);
        for(List<TreeNode> list:result) {
            for(TreeNode node: list){
                System.out.print(node.data+" ");
            }
            System.out.println();
        }
    }

    List<List<TreeNode>> depthlists(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<TreeNode>> result = new LinkedList<List<TreeNode>>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            LinkedList<TreeNode> subresult = new LinkedList<TreeNode>();
            for(int i=0;i<len;i++) {
                TreeNode curr = queue.poll();
                subresult.add(curr);
                if(curr.left != null)
                    queue.add(curr.left);
                if(curr.right != null)
                    queue.add(curr.right);
            }
            result.add(subresult);
        }
        return result;
    }

}
