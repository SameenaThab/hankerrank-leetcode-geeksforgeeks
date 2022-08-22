import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;


class Chap1Prob2 {

    public static void main(String[] args) {
        System.out.println(isPermutationOfOther("Apple","ppale"));
        System.out.println(isPermutationOfOther("banana","ppale"));
        System.out.println(isPermutationOfOther("Globe","belOg"));
        System.out.println(isPermutationOfOther("dog","god"));
        System.out.println(isPermutationOfOther("north","nerth"));
        System.out.println("----------------");
        System.out.println(isPermutationOfOther2NotOptimal("apple","ppale"));
        System.out.println(isPermutationOfOther2NotOptimal("banana","ppale"));
        System.out.println(isPermutationOfOther2NotOptimal("globe","belog"));
        System.out.println(isPermutationOfOther2NotOptimal("dog","god"));
        System.out.println(isPermutationOfOther2NotOptimal("north","nerth"));


    }

    // time = O(n) where n is length of string, space = O(1) as 26 is constant
    private static boolean isPermutationOfOther(String word1,String word2) {
        int[] arr = new int[26];
        if(word1.length() != word2.length())
                return false;
        for(char ch:word1.toCharArray()) {
            if(ch >='a' && ch <='z') {
                arr[ch-'a']++; 
            }
            else { // (ch >='A' && ch <='Z') 
                arr[ch-'A']++;
            }
        }
        for(char ch:word2.toCharArray()) {
            int index;
            if(ch >='a' && ch <='z') {
                index = ch-'a';
            }
            else { // (ch >='A' && ch <='Z') 
                index = ch-'A';
            }
            arr[index]--;
            if(arr[index]<0)
                return false;
        }       
        return true;
    }

// Space = O(nlogn) due to sorting, space = O(n) depending on the length of longest string, Works only when everthing is lower case
    private static boolean isPermutationOfOther2NotOptimal(String word1,String word2) {
        if(word1.length() != word2.length())
                return false;
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        // System.out.println("arr1: "+Arrays.toString(arr1));
        // System.out.println("arr2: "+Arrays.toString(arr2));
        return Arrays.equals(arr1,arr2);
    }

}
