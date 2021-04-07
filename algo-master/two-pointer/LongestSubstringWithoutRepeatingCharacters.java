import java.util.HashSet;
import java.util.Set;
/* 
https://algo.monster/problems/longest_substring_without_repeating_characters
Find the length of the longest substring of a given string without repeating characters.

Input: abccabcabcc
Output: 3
Explanation: longest substrings are abc, cab, both of length 3

Input: aaaabaaa
Output: 2
Explanation: ab is the longest substring, length 2

Brute force way is to check every single substring and count the ones with non-repeating characters. A substring is defined by a start index and an end index.

n = len(s)
for start in range(n):
    for end in range(n):
        substring = s[start: end + 1]
        # check if substring has repeating characters
To improve on brute force, we have to skip unnecessary operations. For a substring starting with start that already contains one duplicate character we want to stop checking more substrings with start index. When this happens we want to increment start and look at next set of substrings.
This makes it a classic sliding window problem. A sliding window is defined by two pointers. We move the window (incrementing pointers) whiles maintaining a certain variant. For this particular problem, the variant is the characters inside the window being unique. We use a set to record what's in the window. And when we encounter a character that's already in the window, we want to move the left pointer until it goes past the last occurrence of that character.
*/
class LongestSubstringWithoutRepeatingCharacters {
    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        int maxLength = 1;
        int start = 0;
        int end = 0;
        Set<Character> set = new HashSet<Character>();
        while(end<s.length()) {
            if(!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
            } else {
                set.remove(s.charAt(start));
                start++;
            }
            maxLength = Math.max(maxLength, end-start);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("str: abccabcabcc "+longestSubstringWithoutRepeatingCharacters("abccabcabcc"));
        System.out.println("str: aaaabaaa "+longestSubstringWithoutRepeatingCharacters("aaaabaaa"));
    }
}
