import java.util.*;

class LongestSubstringNonRepeatingChars {
    /**
     * Time
     *  O(2n) = O(n). In the worst case each character will be visited twice by i and j.
     * Space
     * O(min(m,n)). Same as the previous approach.
     *  We need O(k) space for the sliding window, where k is the size of the Set.
     *  The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.
    */
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        HashSet<Character> set = new HashSet<Character>();
        for(char ch:s.toCharArray()) {
            while(set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }
            max = Math.max(max,right-left+1);
            set.add(ch);
            right++;
        }
        return max;
    }

    /**
     * Time complexity : O(n). Index j will iterate n times.
     * Space complexity : O(min(m,n)). Same as the previous approach. But no. of character in alphabet is const for space can be O(1)
    */
    public int lengthOfLongestSubstringOptmized(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        Map<Character,Integer> indexMap = new HashMap<Character,Integer>();
        for(right=0;right<s.length();right++) {
            char curr = s.charAt(right);
            if(indexMap.containsKey(curr)) {
                left  = Math.max(left,indexMap.get(curr));
            }
            indexMap.put(curr,right+1);
            max = Math.max(max,right-left+1);
        }
        
        return max;
    }
}