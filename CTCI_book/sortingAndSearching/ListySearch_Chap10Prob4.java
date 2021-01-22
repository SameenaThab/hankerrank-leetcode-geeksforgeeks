import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* 
Listy doesnot have length/size method by has elementAt method - return -1 when index out of bounds
Listy elements are sorted
So we can determine the length using elementAt.
we start with index = 1 and do multiples of 2 -> 2,4,8. This way we can determine length o(logn) times (n = 2^k => k(#times called) = logn)
then we do binary search when index return -1 or value > k (why search further)
*/
class Listy {
    int[] arr;
    
    Listy(int[] arr) {
        this.arr=arr;
    }

    int getElementAt(int index) {
        return index>=arr.length? -1:arr[index];
    }
}
public class  ListySearch_Chap10Prob4 {
      
    public static void main(String[] args) {
        int[] arr = new int[]{5,8,10,14,22,34,35};
        Listy listy = new Listy(arr);
        ListySearch_Chap10Prob4 sol = new ListySearch_Chap10Prob4();        
        System.out.println("Solution array : "+sol.search(listy,5));    
        System.out.println("Solution array : "+sol.search(listy,35));
    }

    private int search(Listy listy,int k) {
        int index = 1;
        while(listy.getElementAt(index)!=-1 && listy.getElementAt(index)<=k) {
            index*=2;
        }
        // return binarySearch(listy,0,index/2,k); // cannot do this because when index = 8, low =0,high =4, what if k at index 6
        return binarySearch(listy,index/2,index,k); // even though low starts from index/2, we will traverse to index less then low by doing high=mid-1;
    }

    private int  binarySearch(Listy listy, int low, int high, int k) { // recursive binary search
        int mid;
        System.out.println("low: "+low+" high: "+high);
        while(low <= high) {
            mid = (low+high)/2;
            if(listy.getElementAt(mid) == k)
                return mid;
            else if(listy.getElementAt(mid) < k)  {
                low = mid+1;
            } else { // case when listy.getElementAt(mid) == -1 || listy.getElementAt(mid) > k
                high = mid-1;
            }
        }
        return -1;
    }

}
