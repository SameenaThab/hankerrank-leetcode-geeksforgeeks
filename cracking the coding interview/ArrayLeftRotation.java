/*
A left rotation operation on an array shifts each of the array's elements 1  unit to the left. For example, if 2 left rotations are performed on array [1,2,3,4,5], then the array would become [3,4,5,1,2].

Given an array a of n integers and a number, d, perform d left rotations on the array. Return the updated array to be printed as a single line of space-separated integers.
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ArrayLeftRotation {

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        
        for(int j=0;j<k;j++)
        {
            int temp=a[0];
            for(int i=1;i<n;i++)
            {
                a[i-1]=a[i];
            }  
            a[n-1]=temp;
        } 
        
        return a;
    }
    
    // with extra space
    static int[] rotLeft(int[] a, int d) {

        int n = a.length;
        d = d%n;
        int[] b = new int[n];
        if(d == 0)
            return a;
        System.out.println("d: "+d);
        if(d!=0) {
            for(int i=0;i<n;i++) {
                int pos = i-d<0?i-d+n:i-d;
                System.out.println("i: "+i+" "+"pos: "+pos);
                System.out.println("before b[pos]: "+b[pos]);
                b[pos] = a[i];
                System.out.println("after b[pos]: "+b[pos]);
            }
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
      
        int[] output = new int[n];
        output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");
      
        System.out.println();
      
    }
}
