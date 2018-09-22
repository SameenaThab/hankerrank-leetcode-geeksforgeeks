/*
Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool together money dollars for ice cream. On any given day, the parlor offers a line of n flavors. Each flavor,i , is numbered sequentially with a unique ID number from 1 to n and has a cost, costi, associated with it.

Given the value of money and the cost of each flavor for t trips to the Ice Cream Parlor, help Sunny and Johnny choose two distinct flavors such that they spend their entire pool of money during each visit. For each trip to the parlor, print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as two space-separated integers on a new line. You must print the smaller ID first and the larger ID second.*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class IceCream_Parlor {

   /* static void solve(int[] arr, int money) {
        ArrayList<Integer> al=new ArrayList<Integer>(arr.length);
        int n=arr.length;
        for(int i=0;i<n;i++)
            al.add(arr[i]);
        for(int i=0;i<n;i++)
            if(al.contains(money-al.get(i)))
            {
                System.out.println((i+1)+" "+(al.lastIndexOf(money-al.get(i))+1));
                break;
            }                      
    }
    */
    
    static void solve(int[] arr, int money)
    {
        int n=arr.length;
        int[] sorted=arr.clone();
        Arrays.sort(sorted);
        for(int i=0;i<n;i++)
        {
            int rem=money-sorted[i];
            int loc=Arrays.binarySearch(sorted,i+1,n,rem);
            if(loc>=0 && loc<n && sorted[loc]==rem)
            {
                int i1=indexof(arr,sorted[i],-1);
                int i2=indexof(arr,rem,i1);     
                System.out.println((Math.min(i1,i2)+1)+" "+(Math.max(i1,i2)+1));
                break;
            }

        }
    }

    static int indexof(int[] arr,int val,int excl)
    {
        for(int i=0;i<arr.length;i++)
            if(arr[i]==val && i!=excl)
                return i;
        return -1;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int money = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            solve(arr, money);
        }
        in.close();
    }
}
