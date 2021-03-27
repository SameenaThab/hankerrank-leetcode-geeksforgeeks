/* 
Given a string str, the task is to find the lexicographically smallest string that can be formed by removing at most one character from the given string.

Example 1:
Input: abczd
Output: abcd
Example 2:
Input: abcda
Output: abca
Explanation:
One can remove d to get abca which is the lexicographically smallest string possible.
https://algo.monster/problems/lexicographically_smallest_string
*/
class LexiSmallerString {
    String lexiSmallerString(String str) {
        int i=0;
        int n=str.length();
        for(;i<n-1;i++) {
            if(str.charAt(i)>str.charAt(i+1)) {
                break;
            }
        }
        return str.substring(0, i)+str.substring(i+1);
    }

    public static void main(String[] args) {
        LexiSmallerString sol = new LexiSmallerString();
        System.out.println("Lexicographically smaller string: "+sol.lexiSmallerString("abczd"));
        System.out.println("Lexicographically smaller string: "+sol.lexiSmallerString("abcda"));
    }
}