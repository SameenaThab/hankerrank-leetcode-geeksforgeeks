//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/480/
class UniqueCharacter {
    public int firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        Set<Character> set = new HashSet<Character>();
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (int i = 0; i < s.length(); i++) {
            //System.out.println("i: "+i+"value: "+s.charAt(i)+"-"+map.get(s.charAt(i)));
            if (map.get(s.charAt(i)) == 1) 
                return i;
        }
        return -1;
    }
}