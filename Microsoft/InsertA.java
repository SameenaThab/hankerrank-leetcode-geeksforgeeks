/* 
https://algo.monster/problems/max_inserts_to_obtain_string_without_3_consecutive_a
Given a string S, returns the maximum number of letters a that can be inserted into S (including at the front and end of S) so that the resulting string doesn’t contain three consecutive letters a. If string S already contains the substring aaa, then your function should return -1.

Example 1:
Input: aabab
Output: 3
Explanation:
A string aabaabaa can be made

Example 2:
Input: dog
Output: 8
Explanation:
A string aadaaoaagaa can be made

Example 3:
Input: aa
Output: 0
Explanation:
No longer string can be made.

Example 4:
Input: baaaa
Output: -1
Explanation:
There is a substring aaa
*/
class InsertA {
    int insertA(String str) {
        int countA = 0;
        int countOthers = 0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i) == 'a') {
                countA++;
            } else {
                //count other characters other than a
                countOthers++;
                // reset count of A
                countA=0;
            }
            // we can insert 2 a btw other characters 
            // no of insertions = #(otherCharacter)+1  eg: dog -> 4 insertions possible including front and end
            // 2 A's for each insertion without being 3 consecutive
            // str.length()-countOthers gives no of A in string
            // need to exclude no of A to avoid 3 consecutive A's
            return 2*(countOthers+1)-(str.length()-countOthers);
        }
    }
}