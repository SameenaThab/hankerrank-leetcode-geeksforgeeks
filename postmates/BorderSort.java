import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BorderSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int m = 3;
        int n = 4;
        int[][] a = new int[][]{{4,2,8,0},{2,6,9,8},{0,3,1,7}};
        System.out.println("Matrix before border sort");  
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        borderSort(a,m,n);
        System.out.println("Matrix after border sort");  
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
   }

    public static void borderSort(int[][] a,int m,int n) {
        int size = 2*(m+n-2);
        System.out.println("size:"+size);
        int[] arr = new int[size];
        System.out.println("Matrix before border sort");
        int x=0;  
        int i=0;
        int j=0;
        while(x<size) {        
            while(j<n) {
                arr[x++]=a[i][j++];
            } 
            j--;
            i++;
            System.out.println("i:"+i+" j:"+j);
            while(i<m) {
                arr[x++]=a[i++][j];
            }  
            i--;
            j--;
            System.out.println("i:"+i+" j:"+j);
            while(j>=0) {
                arr[x++]=a[i][j--];
            }
            j++;
            i--;
            System.out.println("i:"+i+" j:"+j);
            while(i>0) {
                arr[x++]=a[i--][j];
            }
        }
        Arrays.sort(arr);
        x=0;  
        i=0;
        j=0;
        while(x<size) {        
            while(j<n) {
                a[i][j++]=arr[x++];
            } 
            j--;
            i++;
            System.out.println("i:"+i+" j:"+j);
            while(i<m) {
                a[i++][j]=arr[x++];
            }  
            i--;
            j--;
            System.out.println("i:"+i+" j:"+j);
            while(j>=0) {
                a[i][j--]=arr[x++];
            }
            j++;
            i--;
            System.out.println("i:"+i+" j:"+j);
            while(i>0) {
                a[i--][j]=arr[x++];
            }
        }        
    }

}