import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* 
Same as binary search but if we encounter emty string, we move further until non empty string found
worst case o(n)
*/

public class  SparseSearch_Chap10Prob5 {
      
    public static void main(String[] args) {
        String[] arr = new String[]{"at","","","ball","","","","car","","","","dad"};
        SparseSearch_Chap10Prob5 sol = new SparseSearch_Chap10Prob5();        
        System.out.println("Solution array : "+sol.binarySearch(arr,0,arr.length-1,"dad"));
    }

    private int binarySearch(String[] arr,int low,int high,String k) {
        int mid;
        while(low<=high) {
            mid = (low+high)/2;
            System.out.println("low: "+low+" high: "+high+" mid: "+mid);
            if(arr[mid].isEmpty()) {
                int left = mid-1;
                int right = mid+1;
                while(true) {
                    if(left < low && right > high)
                        return -1;
                    if(left>=low && !arr[left].isEmpty()) {
                        mid = left;
                        break;
                    }
                    if(right<=high && !arr[right].isEmpty()) {
                        mid = right;
                        break;
                    }
                    left--;
                    right++;
                }
            }
            if(arr[mid].equals(k)) {
                return mid;
            }
            else if(k.compareTo(arr[mid]) > 0) {
                low=mid+1;
            }
            else {
                high = mid-1;
            }
        }

        return -1;
    }
}
