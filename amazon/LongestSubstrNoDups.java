//https://leetcode.com/problems/longest-substring-without-repeating-characters/
class LongestSubstrNoDups {
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        int max=0;
        // int i=0,j=0;
        // Set<Character> set = new HashSet<Character>();
        // while(i<n && j<n) {
        // if(!set.contains(s.charAt(j))) {
        //     set.add(s.charAt(j++));
        //     max=Math.max(max,j-i);
        // }
        //     else {
        //         set.remove(s.charAt(i++));
        //     }
        // }
        
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i=0,j=0;j<n;j++) {
            if(map.containsKey(s.charAt(j)))
                i = Math.max(map.get(s.charAt(j)),i); // because we dont want go back lesser index where we already found a duplicate char
            max=Math.max(max,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return max;
    }
}