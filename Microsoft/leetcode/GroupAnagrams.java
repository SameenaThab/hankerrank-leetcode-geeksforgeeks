import java.util.*;
class GroupAnagrams {
    /*Time Complexity: O(NKlogK)
    where N is the length of strs, and K is the maximum length of a string in strs. The outer loop has complexity O(N) as we iterate through each string. Then, we sort each string in O(KlogK) time.
    Space: O(NK),the total information content stored in ans.
    Best Approach 
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        if(strs.length == 0)
            return new ArrayList();
        for(String s: strs){
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            List<String> list = map.getOrDefault(key,new ArrayList<String>());
            list.add(s);
            map.put(key,list);
        }
        return new ArrayList(map.values());
    }
    
    /*
    Time Complexity: O(NK), where N is the length of strs, and K is the maximum length of a string in strs. Counting each string is linear in the size of the string, and we count every string.

    Space Complexity: O(NK), the total information content stored in ans.
    */
    public List<List<String>> groupAnagrams2(String[] strs) {
        int[] count = new int[26];
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        if(strs.length == 0)
            return new ArrayList();
        for(String s: strs){
            
            Arrays.fill(count, 0);
            for(char c:s.toCharArray()) {
                count[c-'a']++;
            }
            String key = Arrays.toString(count);
            List<String> list = map.getOrDefault(key,new ArrayList<String>());
            list.add(s);
            map.put(key,list);
        }
        return new ArrayList(map.values());
    }
}