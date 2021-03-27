/* 
https://algo.monster/problems/longest_semi_alternating_substring
Given a string S containing only characters a and b. A substring (contiguous fragment) of S is called a semi-alternating substring if it does not contain three identical consecutive characters. In other words, it does not contain either 'aaa' or 'bbb' substrings. Note that the whole S is its own substring.

Example 1:
Input: baaabbabbb
Output: 7
Explanation:
the longest semi-alternating substring is aabbabb

Example 2:
Input: babba
Output: 5
Explanation:
Whole S is semi-alternating.

Example 3:
Input: abaaaa
Output: 4
Explanation:
The first four letters of S create a semi-alternating substring.

*/
class LongestSemiAlternating {
    int longestSemiAlternating(String word) {
        int maxLength = 0;
        int start = 0;
        int count = 1;
        for(int i=1;i<word.length();i++) {
            if(word.charAt(i) == word.charAt(i-1)){
                count++;
                if(count == 3) {
                    maxLength = Math.max(i-start,maxLength);
                    start = i-1;
                }
            } else {
                count = 1;
            }

        }
        if(maxLength == 0)
            maxLength = word.length()-start;
        return maxLength;
    }

    int internetSolution(String word) {
        int maxLength = 0;
        int start = 0;
        int count = 1;
        for(int i=1;i<word.length();i++) {
            if(word.charAt(i) == word.charAt(i-1)){
                count++;
                if(count == 3) {
                    maxLength = Math.max(i-start,maxLength);
                    start = i-1;
                }
            } else {
                count = 1;
            }

        }
        if(maxLength == 0)
            maxLength = word.length()-start;
        return maxLength;
    }

    public static void main(String[] args) {
        LongestSemiAlternating sol = new LongestSemiAlternating();
        System.out.println("Solution for baaabbabbb: "+sol.longestSemiAlternating("baaabbabbb"));
        System.out.println("Solution for babba: "+sol.longestSemiAlternating("babba"));
        System.out.println("Solution for abaaaa: "+sol.longestSemiAlternating("abaaaa"));


        System.out.println("Solution for baaabbabbb: "+sol.internetSolution("baaabbabbb"));
        System.out.println("Solution for babba: "+sol.internetSolution("babba"));
        System.out.println("Solution for abaaaa: "+sol.internetSolution("abaaaa"));
    }
}