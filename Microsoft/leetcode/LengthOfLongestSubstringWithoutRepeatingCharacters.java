class LengthOfLongestSubstringWithoutRepeatingCharacters {
    /* 
    Time complexity : O(n). Index jj will iterate nn times.

    Space complexity (HashMap) : O(min(m,n)). Same as the previous approach.

    Space complexity (Table): O(m). M is the size of the charset.
    */
    public int lengthOfLongestSubstring(String s) {
        //ASCII has 128 alphanumeric characters.
        // including uppercase and lowercase letters, numbers, and punctuation, 32 control codes are also incorporated in these combinations.
        Integer[] chars = new Integer[128];
        int left = 0;
        int right = 0;
        int res = 0;
        while(right < s.length()) {
            //get index of char, if already existing
            Integer index = chars[s.charAt(right)];
            if(index != null && index >=left && index < right) {
                // we start from its next character to avoid duplicates
                left = index+1;
            }
            res = Math.max(res,right-left+1);
            chars[s.charAt(right)] = right;
            right++;
        }
        
        return res;
    }

}