class LongestRepeatingCharacterReplacement {
    //https://leetcode.com/problems/longest-repeating-character-replacement/submissions/
    /* 
    https://aaronice.gitbook.io/lintcode/two_pointers/longest-repeating-character-replacement
    end - start + 1 = size of the current window
    maxCount = largest count of a single, unique character in the current window
    The main equation is:end - start + 1 - maxCount gives # of diferent characters
    When end-start+1-maxCount== 0, then then the window is filled with only one character
    When end-start+1-maxCount> 0, then we have characters in the window that are NOT the character that occurs the most.
    end-start+1-maxCount == # of characters that are NOT the character that occurs the most in that window.
    We are allowed to have at most k replacements in the window, so when end - start + 1 - maxCount > k, then there are more characters in the window than we can replace, and we need to shrink the window.
    maxCount may be invalid at some points, but this doesn't matter,
    because it was valid earlier in the string, and all that matters is finding the max window that occurred anywhere in the string. 
    Additionally, it will expand _if and only if _enough repeating characters appear in the window to make it expand. So whenever it expands, it's a valid expansion.
    */
    //O(n) time, O(k) space
    public int characterReplacement(String s, int k) {

        int[] count = new int[26];
        int maxCount = 0; 
        int maxLength = 0;

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A'] += 1;
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);
            while (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A'] -= 1;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public int characterReplacement2(String s, int k) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int max = 0;
        int[] ch = new int[26];
        char[] str = s.toCharArray();
        for(int i=0, j=0; i<s.length(); i++){
            while(j < s.length()){
                ch[str[j] - 'A']++;
                if(count(ch) > k){  //If exceed k, break
                    ch[str[j] - 'A']--;
                    break;
                }
                j++;
            }
            max = Math.max(max, j-i);
            ch[str[i] - 'A']--;
        }
        return max;
    }
    //Count the number of character that is different to the longest character
    public int count(int[] ch){
        int max = 0;
        int sum = 0;
        for(int val:ch){
            sum += val;
            max = Math.max(max, val);
        }
        return sum - max;
    }

}