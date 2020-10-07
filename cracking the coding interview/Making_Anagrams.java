https://www.hackerrank.com/challenges/making-anagrams/problem?h_r=internal-search
/*
Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string. In other words, both strings must contain the same exact letters in the same exact frequency For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.

Alice decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number of character deletions required to make the two strings anagrams. Can you help her find this number?

Given two strings,  and , that may or may not be of the same length, determine the minimum number of character deletions required to make  and  anagrams. Any characters can be deleted from either of the strings.
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Making_Anagrams {

    // using single hash Map
    static int makingAnagrams(String s1, String s2) {
        Map<Character,Integer> table = new HashMap<Character,Integer>();
        //making hashMap for first String
        for(char c:s1.toCharArray()) {
            if(table.containsKey(c)) {
                table.put(c,table.get(c)+1);
            } else {
                table.put(c,1);
            }
        }
        int del = 0;
        for(char c:s2.toCharArray()) {
            if(table.containsKey(c) && table.get(c)!=0) {
                // decrementing deletions when char present in hash Map
                table.put(c,table.get(c)-1);                
            } else {
                // incrementing deletions when char not in hash Map
                del++;
            }
        }

        //add remaining mismatched characters of first String to deletions counter
        for (Character key : table.keySet()) { 
            del=del+table.get(key);
        }
        return del;

    }

    public static int numberNeeded(String first, String second) {
        int count=0;
        int[] firstcharcount=new int[26];
        int[] secondcharcount=new int[26];
        for(char c:first.toCharArray())
        {
            int offset=c-'a';
            firstcharcount[offset]++;
        }
        
         for(char c:second.toCharArray())
        {
            int offset=c-'a';
            secondcharcount[offset]++;
        }
        
        return diff(firstcharcount,secondcharcount);
    }
    
    public static int diff(int[] first,int[] second)
    {
        int count=0;
        for(int i=0;i<26;i++)
        {
            count+=Math.abs(second[i]-first[i]);
        }
        
        return count;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
