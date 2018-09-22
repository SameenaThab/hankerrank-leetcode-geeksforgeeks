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
