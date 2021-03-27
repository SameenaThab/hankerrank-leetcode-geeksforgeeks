class TwoContiguousOccurrences {
    /* 
    https://algo.monster/problems/longest_substring_without_3_contiguous_occurrences_letter

    Given a string str containing only a and b, find the longest substring of str such that str does not contain more than two contiguous occurrences of a and b.

    Example 1:
    Input: aabbaaaaabb
    Output: aabbaa
    Example 2:
    Input: aabbaabbaabbaaa
    Output: aabbaabbaabbaa

    Solution: sliding window solution
    initialize start to 0
    traverse string str with index i, increment count for continuous char
    if different character from previous encounteres reset count
    if any character continuous count is > 2
    increment start to i-1 (so that only current and prev char are counted which is equal to 2)
    update start_ml and max_length, if(maxlength < current len , i-start+1)
    return result = str.substring(start_ml,max_length)
    */
    String twoContiguousOccurrences(String str) {
        int n = str.length();
        if(n<=1)
            return str;
        int count = 1;
        int start_ml = 0;
        int max_length = 0;
        int start = 0;
        for(int i=1;i<n;i++) {
            if(str.charAt(i) != str.charAt(i-1)) {
                count=1;
            } else {
                count++;
            }
            if (count <= 2) {
                if (i - start + 1 > max_length) {
                    max_length = i - start + 1;
                    start_ml = start;
                }
            } else {
                start = i-1;
                count = 2;
            }
        }
        return str.substring(start,max_length); 
    }

    public static void main(String[] args) {
        TwoContiguousOccurrences sol = new TwoContiguousOccurrences();
        System.out.println("Solution for aabbaaaaabb: "+sol.twoContiguousOccurrences("aabbaaaaabb"));
        System.out.println("Solution for aabbaabbaabbaaa: "+sol.twoContiguousOccurrences("aabbaabbaabbaaa"));
    }
}