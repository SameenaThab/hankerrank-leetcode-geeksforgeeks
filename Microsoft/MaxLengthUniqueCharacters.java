import java.util.*;

/* 
https://algo.monster/problems/max_length_of_a_concatenated_string_with_unique_characters
Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters. Return the maximum possible length of s.

Constraints:
1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
Example 1:
Input: ["un","iq","ue"]
Output: 4
Explanation:
All possible concatenations are "","un","iq","ue","uniq" and "ique".

Example 2:
Input: ["cha","r","act","ers"]
Output: 6
Explanation:
Possible solutions are "chaers" and "acters".

Example 3:
Input: ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
*/
class MaxLengthUniqueCharacters {
    int maxLengthUniqueChars(String[] words) {
        words = Arrays.stream(words).filter(word -> isUnique(word)).toArray(String[] :: new);
        Map<String,Integer> mem = new HashMap<String,Integer>();
        return recurse("",0,words,mem,0);
    }

    private int recurse(String formed, int idx, String[] words, Map<String, Integer> mem,int maxLength) {
        if(idx == words.length)
            return maxLength;
        if(mem.containsKey(formed)) {
            return mem.get(formed);
        }
        if(!isUnique(formed)) {
            mem.put(formed,maxLength);
            return maxLength;
        }
        maxLength = Math.max(maxLength,formed.length());

        for(int i=idx;i<words.length;i++) {
            maxLength = Math.max(maxLength,recurse(formed+words[i],i,words,mem,maxLength));
        }
        mem.put(formed,maxLength);
        return maxLength;
    }

    private boolean isUnique(String word) {
        int bitvector = 0;
        for(char ch : word.toCharArray()){
            int index = ch-'a';
            if((bitvector & (1<<index)) > 0) {
                return false;
            }
            bitvector |= (1<<index);
        }
        return true;
    }

    public int myLeetCodeSolution(List<String> arr) {
        List<String> combinations = new ArrayList<String>();
        int max = 0;
        combinations.add("");
        for(int i=0;i<arr.size();i++) {
            String curr = arr.get(i);
            if(isUnique(curr)) { // if word itself hasnt unique characters, then it will not make unique concatenation
                int n = combinations.size();
                for(int j=0;j<n;j++){
                    String newComb = curr+combinations.get(j);
                    if(isUnique(newComb)) {
                        combinations.add(newComb);
                        max = Math.max(max,newComb.length());
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxLengthUniqueCharacters sol = new MaxLengthUniqueCharacters();
        System.out.println(sol.maxLengthUniqueChars(new String[]{"un","iq","ue"}));
        System.out.println(sol.maxLengthUniqueChars(new String[]{"cha","r","act","ers"}));
        System.out.println(sol.maxLengthUniqueChars(new String[]{"abcdefghijklmnopqrstuvwxyz"}));
    }
}