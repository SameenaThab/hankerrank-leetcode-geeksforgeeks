class LengthOfLongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
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