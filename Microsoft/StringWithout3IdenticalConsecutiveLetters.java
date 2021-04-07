//https://algo.monster/problems/string_without_3_identical_consecutive_letters
/* 
Given a string S having lowercase English letters, returns a string with no instances of three identical consecutive letters, obtained from S by deleting the minimum possible number of letters.

Example 1:
Input: eedaaad
Output: eedaad
Explanation:
One occurrence of letter a is deleted.

Example 2:
Input: xxxtxxx
Output: xxtxx
Explanation:
Note that letter x can occur more than three times in the returned string if the occurrences are not consecutive.

Example 3:
Input: uuuuxaaaaxum
Output: uuxaaxum
*/
class StringWithout3IdenticalConsecutiveLetters {
    public static String filterString(String s) {
        int s_len = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        sb.append(s.charAt(1));
        for (int i = 2; i < s_len; ++i) {
            // if (s.charAt(i) == s.charAt(i - 1) && s.charAt(i) = s.charAt(i - 2)) {
            //     continue;
            // } else {
            //     sb.append(s.charAt(i));
            // }
            // above commented code can be writtena s below
            if (s.charAt(i) != s.charAt(i - 1) || s.charAt(i) != s.charAt(i - 2)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String filterStringMySolution(String s) {
        int s_len = s.length();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(s.charAt(0));
        for (int i = 1; i < s_len; ++i) {
            if(s.charAt(i)==s.charAt(i-1)){
                count++;
            } else {
                count = 1;
            }
            if(count<3) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringWithout3IdenticalConsecutiveLetters sol = new StringWithout3IdenticalConsecutiveLetters();
        System.out.println("Solution for eedaaad: "+sol.filterString("eedaaad"));
        System.out.println("Solution for eedaaad: "+sol.filterString("xxxtxxx"));
        System.out.println("Solution for eedaaad: "+sol.filterString("uuuuxaaaaxum"));
        System.out.println("Solution for eedaaad: "+sol.filterStringMySolution("eedaaad"));
        System.out.println("Solution for eedaaad: "+sol.filterStringMySolution("xxxtxxx"));
        System.out.println("Solution for eedaaad: "+sol.filterStringMySolution("uuuuxaaaaxum"));
    }
}
