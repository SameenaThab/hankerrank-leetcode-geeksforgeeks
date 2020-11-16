// https://leetcode.com/discuss/interview-question/895766/Postmates-or-OA-or-Number-Subarrays

import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LargestLeftPalindrome {
    public static void main(String[] args) {
        String st = "abaccc";
        while(!st.isEmpty()) {
            int palin = largestLeftPalindrome(st);
            if(palin == -1) {
                System.out.println("Remaining String: "+st);
                break;
            } 
            System.out.println("Found Palindrome: "+st.substring(0,palin));
            st=st.substring(palin);
            System.out.println("Remaining String: "+st);
        }
   }

    private static int largestLeftPalindrome(String st) {
        int left=0;
        int n=st.length();
        int right = n-1;
        while(left != right) {
            int i=left;
            int j=right;
            while(i <= j && st.charAt(i) == st.charAt(j)) {
                i++;
                j--;
            }
            if(i>j) {
                System.out.println("left: "+left+" right: "+right);
                return right+1;
            }                
            right--;
        }
        return -1;
    }

}