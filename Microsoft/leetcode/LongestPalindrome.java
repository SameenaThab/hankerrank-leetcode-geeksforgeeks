class LongestPalindrome {
/* 
Expand Around Center

We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are only 2n−1 such centers.
You might be asking why there are 2n−1 but not n centers? The reason is the center of a palindrome can be in between two letters. Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.

Time complexity : O(n^2)
Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2)
Space complexity : O(1).
*/
    public String longestPalindrome(String s) {
        int start = 0;
        int maxLength = 0;
        if(s.length() <= 1)
            return s;
        int low = 0;
        int high = 0;
        for(int i=1;i<s.length();i++) {
            low = i-1;
            high = i;
            // even length palindrome
            while(low>=0 && high<s.length() && s.charAt(low)==s.charAt(high)) {
                if(high-low+1 > maxLength){
                    start = low;
                    maxLength = high-low+1;
                }
                low--;
                high++;
            }
            // odd length palindrome
            low = i;
            high = i;
            while(low>=0 && high<s.length() && s.charAt(low)==s.charAt(high)) {
                if(high-low+1 > maxLength){
                    start = low;
                    maxLength = high-low+1;
                }
                low--;
                high++;
            }            
        }
        return s.substring(start,start+maxLength);
    }
}