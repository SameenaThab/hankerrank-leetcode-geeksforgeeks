// https://leetcode.com/discuss/interview-question/895766/Postmates-or-OA-or-Number-Subarrays


import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class NumberSubarray {
    public static void main(String[] args) {
        int[] arr = {1,2,1,2,1,1,3,1,3};
        // int[] arr = {1,1,1};

        // i=0
        // 1,2,1,2
        // 1,2,1,2,1
        // 1,2,1,2,1,1
        // 1,2,1,2,1,1,3,1,3

        // i=1
        // 2,1,2,1
        // 2,1,2,1,2
        // 2,1,2,1,2,3,1,3

        // i=2
        // 0

        // i = 3
        // 0

        // i=4
        // 1,1
        // 1,1,3,1,3

        // i=5
        // 1,3,1,3

        // i=6
        // 0

        // i=7
        // 0

        // i=8
        // 0

        System.out.println(count(arr));
   }

    private static int count(int[] arr) {
        int count = 0;
        int n = arr.length;
        Map<Integer,Integer> freq = new HashMap<Integer,Integer>();
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i<n;i++) {
            System.out.println("i "+i);
            for(int j=i;j<n;j++) {
                freq.put(arr[j],freq.getOrDefault(arr[j], 0)+1);
                if(freq.get(arr[j]) == 1) {
                    set.add(arr[j]);
                    System.out.println("Came to add "+j);
                } else {
                    set.remove(arr[j]);
                    System.out.println("Came to remove "+j);
                }
    
                if(set.isEmpty()) {
                    count++;
                }                
            }
            freq.clear();
            set.clear();
            System.out.println("count "+count);
        }
        return count;
    }

}