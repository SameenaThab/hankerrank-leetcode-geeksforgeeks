import java.util.*;

/**
 * 
Round 2 Question
Given a String str, find the length of longest substring with almost 2 unique characters
eg: aacccdddddefg output:8 cccddddd is the longest substring with almost 2 unique characters
eg: aacacdddddefg output:6 cddddd is the longest substring with almost 2 unique characters

solution : Start with pointer  l=0 and r=0, max = -1, Intitailize Map HashMap<Character, Integer> map 
1- Traverse the string str until r<str.length()
2- For each character in str.charAt(r) insert /update the character and its index in map
3- when map.size > 2 -> breach atmost unique character
    3.1- find the character with the largest index in the map
    3.2- reset  l = map.get(character_with_largest_index)+1;
    3.3- remove the character from the map
4- update max = Math.max(r-l+1,max);

time = O(n) = length of string
space = O(1) as hashmap only grows till 3 characters since almost character allowed is 2
*/
class CamblyLongestSubstrWithUniqueCharacters {

    public static void main(String[] args) {
        System.out.println(findLongestSubStrLen("aacccdddddefg"));
        System.out.println(findLongestSubStrLen("aacacdddddefg"));
    }

    public static int findLongestSubStrLen(String str) {
        int max = -1;
        int l=0,r=0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        while(r<str.length()) {
            char ch = str.charAt(r);
            map.put(ch,r);
            if(map.size() > 2) {
                int minIndexToRemove = r;
                char charToRemove = '$';
                for(Character key:map.keySet()) {
                    if(map.get(key)<minIndexToRemove) {
                        minIndexToRemove = map.get(key);
                        charToRemove = key;
                    }
                }
                l = minIndexToRemove+1;
                map.remove(charToRemove);
            }
            max = Math.max(max,r-l+1);
            r++;
        }
        return max;
    }
}