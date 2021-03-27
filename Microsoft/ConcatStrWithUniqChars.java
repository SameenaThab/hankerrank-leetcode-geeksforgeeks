/* 
https://algo.monster/problems/concatenated_string_length_with_unique_characters
Given an Array A consisting of N Strings, calculate the length of the longest string S such that:

S is a concatenation of some of the Strings from A.
every letter in S is different.
N is [1..8]
A consists of lowercase English letters
Sum of length of strings in A does not exceed 100.
Example 1:
Input: ["co","dil","ity"]
Output: 5
Explanation:
String S could be codil, dilco, coity, ityco

Example 2:
Input: ["abc","kkk","def","csv"]
Output: 6
Explanation:
Strings S could be abcdef , defabc, defcsv , csvdef

Example 3:
Input: ["abc","ade","akl"]
Output: 0
Explanation:
impossible to concatenate as letters wont be unique.

*/

import java.util.*;
class ConcatStrWithUniqChars {

    int concatString(String[] arr) {
        //exclude string that are not unique
        arr = Arrays.stream(arr).filter(str->isUnique(str)).toArray(String[]::new);
        Set<String> words = new HashSet<String>();
        for(String word:arr){
            words.add(word);
        }
        Map<String,Integer> mem = new HashMap<String,Integer>();
        return recursive("",0,arr,mem,0,words);
    }


    private int recursive(String currStr, int idx, String[] arr, Map<String, Integer> mem,int maxLength, Set<String> words) {
        if(mem.containsKey(currStr))
            return mem.get(currStr);
        boolean isUnique = isUnique(currStr);
        if(!isUnique)
            return maxLength;
        
        // Since unique update the maxLength, we check if the word exists in set to take care of example 3
        // that is we only want concatenated words not individual word
        if (isUnique && !words.contains(currStr)) {
            maxLength = Math.max(currStr.length(), maxLength);
        }
        if(idx == arr.length) {
            mem.put(currStr,maxLength);
            return maxLength;
        }
        for(int i=idx;i<arr.length;i++) {
           maxLength = Math.max(maxLength,recursive(currStr+arr[i], i+1, arr, mem, maxLength,words));
        }
        mem.put(currStr,maxLength);
        return maxLength;
    }


    private boolean isUnique(String word) {
        int counter = 0; // 32 bit
        for(char ch:word.toCharArray()){
            int index = ch-'a';
            int bitShift = 1<<index;    
            if((counter & bitShift) > 0) // character already exists
                return false;
            counter|=bitShift;
        }
        return true;
    }

    public static void main(String[] args) {
        ConcatStrWithUniqChars sol = new ConcatStrWithUniqChars();
        System.out.println("Should be 5: "+sol.concatString(new String[] {"co","dil","ity"}));
        System.out.println("Should be 6: "+sol.concatString(new String[] {"abc","kkk","def","csv"}));
        System.out.println("Should be 3: "+sol.concatString(new String[] {"abc","ade","akl"}));

    }

}