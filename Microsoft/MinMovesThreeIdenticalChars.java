import java.util.Arrays;

/* 
https://algo.monster/problems/min_moves_no_three_consecutive_chars
Given a string S consisting of N letters a and b. In one move you can replace one letter by the other (a by b or b by a).
Write a function solution that given such a string S, returns the minimum number of moves required to obtain a string containing no instances of three identical consecutive letters.
Example 1
Input: baaaaa
Output: 1
Explanation: The string without three identical consecutive letters which can be obtained is one move is "baabaa".

Example 2
Input: baaabbaabbba
Output: 5
[b, a, a, a, b, b, a, a, b, b, b, a]
[b, a, a, b, b, b, a, a, b, b, a, a]

Example 3
Input: baabab
Output: 0
*/
class MinMovesThreeIdenticalChars {
/* 
https://molchevskyi.medium.com/microsoft-interview-tasks-min-moves-to-make-string-without-3-identical-consecutive-letters-abe61ed51a10

To solve this task we need to find sequences of the same letters and if the sequence is longer than 3 divide length of this sequence to 3 and add result of the division to counters of needed moves.
Example of work:
    3 consecutive: baaab, replace the middle a (3 / 3 == 1)
    4 consecutive: baaaab, replace the third a (4 / 3 == 1)
    5 consecutive: baaaaab, replace the third a (5 / 3 == 1)
    6 consecutive: baaaaaab -> baabaaab -> baaab -> bab with 2 replacements (6 / 3 == 2)
    10 consecutive: baaaaaaaaaab -> baabaaaaaaab -> baaaaaaab -> baaaab -> baab with 3 replacements (10 / 3 == 3)

*/
    int solution(String s) {
        int moves = 0, n = s.length();
        char[] characters = s.toCharArray();
        for(int i = 0; i < n;) {
            int next = i + 1;
            // if we meet sequence of the same letters 
            // scan the string to find length of this sequence 
            while( (next < n) && (characters[i] == characters[next]) ) { next++; }
            // Here "next - i" is length of the sequence
            // Each third letter should be changed to remove 
            // too long sequences
            moves += (next - i) / 3; 
            i = next; // skip processed letters 
        }
        return moves;
    }

    public static void main(String[] args) {
        MinMovesThreeIdenticalChars sol = new MinMovesThreeIdenticalChars();
        System.out.println("Solution for baaaaa: "+sol.minMovesThreeIdenticalChars("baaaaa"));
        System.out.println("Solution for baaabbaabbba: "+sol.minMovesThreeIdenticalChars("baaabbaabbba"));
        System.out.println("Solution for baabab: "+sol.minMovesThreeIdenticalChars("baabab"));


        System.out.println("Solution for baaaaa: "+sol.solution("baaaaa"));
        System.out.println("Solution for baaabbaabbba: "+sol.solution("baaabbaabbba"));
        System.out.println("Solution for baabab: "+sol.solution("baabab"));
    }
}