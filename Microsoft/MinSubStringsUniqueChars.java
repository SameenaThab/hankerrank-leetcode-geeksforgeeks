import java.util.*;

/* 
Split the given string into minimum number of substrings with unique characters such that 
the concatenation of all the substrings will result in the given string.

"dddd" --> ["d", "d", "d", "d"] --> answer should be 4.
"abab" --> ["ab", "ab"] --> answer should be 2.
 */

 public class MinSubStringsUniqueChars {
    List<String> minSubStringsWithUniqChars(String word) {
        Set<Character> set = new HashSet<Character>();
        List<String> result = new ArrayList<String>();
        StringBuilder temp = new StringBuilder();
        for(int i=0;i<word.length();i++) {
            // System.out.println("char: "+word.charAt(i));
            if(set.contains(word.charAt(i)) ) {
                // System.out.println("clear and add "+temp.toString());
                result.add(temp.toString());
                set.clear();
                temp = new StringBuilder();
            }

            set.add(word.charAt(i));
            temp.append(word.charAt(i));
        }
        if(temp.length() != 0)
            result.add(temp.toString());
        return result;
    }

    public static void main(String[] args) {
        MinSubStringsUniqueChars sol = new MinSubStringsUniqueChars();
        System.out.println(sol.minSubStringsWithUniqChars("dddd"));
        System.out.println(sol.minSubStringsWithUniqChars("abab"));

        System.out.println(sol.minSubStringsWithUniqChars("abzjklmnm"));
        System.out.println(sol.minSubStringsWithUniqChars("zxjwyabd"));
    }
 }
