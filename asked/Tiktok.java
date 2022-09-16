/*
 * given number n  and array of integers arr
 * find the largest number x less than n such that all  digits in x belong in arr
 * n = 23121 arr = {2,4,9}
 * output : 22999
 * n = 10000 arr = {2,4,9}
 * output : 9999
 *  n = 99 arr = {2,4,9}
 * output : 99
*/

import java.util.*;
class Tiktok {
    public static void main(String[] args) {
        System.out.println(findLargestNumber(23121,new int[]{2,4,9}));
        System.out.println(findLargestNumber(10000,new int[]{2,4,9}));
        System.out.println(findLargestNumber(99,new int[]{2,4,9}));
    }

    private static int findLargestNumber(int n,int[] arr) {
        Set<Integer> set = new HashSet<Integer>();
        for(int num:arr) {
            set.add(num);
        }
        int temp = n;
        List<Integer> list = new ArrayList<Integer>();
        while(temp != 0) {
            // System.out.println("temp: "+temp);
            if(set.contains(temp%10)) {
                // System.out.println("reminder: "+temp%10);
                list.add(temp%10);
                temp/=10;
            } else {
                temp--;
            }
        }
        // System.out.println("list: "+list.toString());
        int result = 0;
        for(int i=list.size()-1;i>=0;i--) {
            result=result*10+list.get(i);
        }
        return result;
    }
}
