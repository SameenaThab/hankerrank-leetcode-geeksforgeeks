import java.util.*;

class TreeNode {
    TreeNode left;
    TreeNode right;
    int data;
    int rank;

    TreeNode(int data) {
        this.data=data;
        this.left=null;
        this.right=null;
        this.rank=0;
    }

    void insert(int x) {
        if(x==this.data) {
            this.rank++;
            return;
        }
        if(x<this.data) {
            this.rank++;
            if(this.left!=null)
                this.left.insert(x);
            else
                this.left = new TreeNode(x);
        } else {
            if(this.right!=null)
                this.right.insert(x);
            else
                this.right = new TreeNode(x);           
        }
    }

    int getRank(int x) {
        // System.out.println("data: "+data+" rank: "+rank);
        if(x==this.data) {
            return this.rank;
        }
        if(x<this.data && this.left!=null) {
            return this.left.getRank(x);
        } 
        if(x>this.data && this.right!=null) {
            int xRank = this.right.getRank(x);
            return rank == -1?rank:1+this.rank+xRank;
        }
        return -1;
    }
}

public class Practise {

    public static void main(String[] args) {
        Practise p = new Practise();
        String[] arr = new String[] {"","at","","","","ball","","car","","dad","",""};
        assert find(arr,"ball")==5:"ball at 5";
        assert find(arr,"dad")==9:"dad at 9";
        assert find(arr,"at")==1:"at at 1";
    }

    public static int find(String[] arr,String word) {
        return find(arr,word,0,arr.length);
    }

    public static int find(String[] arr,String word,int first,int last) {
        int mid = (first+last)/2;
        if(arr[mid]=="") {
            int left=mid-1;
            int right=mid+1;
            while(true) {
                if(left<first && right>last)
                    return -1;
                if(left>=first && !arr[left].isEmpty()) {
                    mid = left;
                    break;
                }
                if(right<=last && !arr[right].isEmpty()) {
                    mid=right;
                    break;
                }
                right++;
                left--;
            }
        }
        if(word.equals(arr[mid])) {
            return mid;
        } else if(word.compareTo(arr[mid])<0) {
            return find(arr,word,first,mid-1);
        } else {
            return find(arr,word,mid+1,last);
        }
    }
}
