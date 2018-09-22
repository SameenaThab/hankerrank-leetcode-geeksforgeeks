import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Count_Inversions {

    static long countInversions(int[] arr) {
        int n=arr.length;
        if(n<=1)
            return 0;
        int mid=n/2;
        int[] leftarr=Arrays.copyOfRange(arr,0,mid);
        int[] rightarr=Arrays.copyOfRange(arr,mid,n);
        long inversions=countInversions(leftarr)+countInversions(rightarr);
        
        int l=0;
        int r=0;
        int rlimit=n-mid;
        for(int i=0;i<n;i++)
        {
            if((l<mid) && (r>=rlimit||leftarr[l]<=rightarr[r]))
            {
                arr[i]=leftarr[l++];
                inversions+=r;
            }
            else
            {
                if(r<rlimit)
                    arr[i]=rightarr[r++];
            }
        }
        
        return inversions;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            long result = countInversions(arr);
            System.out.println(result);
        }
        in.close();
    }
}
