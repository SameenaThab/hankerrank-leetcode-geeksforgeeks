/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.  

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/
    boolean checkBST(Node root) {
        return check(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    
    boolean check(Node root,int min,int max)
    {
        if(root==null)
            return true;
        
        if (root.data < min || root.data > max) {
			return false;
		}
        
        return check(root.left,min,root.data-1) && check(root.right,root.data+1,max);

    }
