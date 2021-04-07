import java.util.*;

class LetterCombinations {
    public List<String> letterCombinationsBetter(String digits) {
        Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
        map.put(2,Arrays.asList(new String[]{"a","b","c"}));
        map.put(3,Arrays.asList(new String[]{"d","e","f"}));
        map.put(4,Arrays.asList(new String[]{"g","h","i"}));
        map.put(5,Arrays.asList(new String[]{"j","k","l"}));
        map.put(6, Arrays.asList(new String[]{"m","n","o"}));
        map.put(7, Arrays.asList(new String[]{"p","q","r","s"}));
        map.put(8, Arrays.asList(new String[]{"t","u","v"}));
        map.put(9, Arrays.asList(new String[]{"w","x","y","z"}));
       return helper(digits,map);
    }
    
    public List<String> helper(String digits,Map<Integer,List<String>> map) {
        List<String> result = new ArrayList<String>();
        if(digits.length() == 0)
            return new ArrayList<String>();
        if(digits.length() == 1)
            return map.get(Integer.parseInt(digits));
        int n = digits.length();
        String left = digits.substring(0,1);
        String right = digits.substring(1,n);
        for(String s1:helper(left,map)) {
            for(String s2:helper(right,map)) {
                result.add(s1+s2);
            }
        }
        return result;
    }

    class Solution {
        public List<String> letterCombinationsBackTrack(String digits) {
            Map<Character,List<Character>> map = new HashMap<Character,List<Character>>();
                    map.put('0',new ArrayList<Character>(Arrays.asList('0')));
                    map.put('1',new ArrayList<Character>(Arrays.asList('1')));
                    map.put('2',new ArrayList<Character>(Arrays.asList('a','b','c')));
                    map.put('3',new ArrayList<Character>(Arrays.asList('d','e','f')));
                    map.put('4',new ArrayList<Character>(Arrays.asList('g','h','i')));
                    map.put('5',new ArrayList<Character>(Arrays.asList('j','k','l')));
                    map.put('6',new ArrayList<Character>(Arrays.asList('m','n','o')));
                    map.put('7',new ArrayList<Character>(Arrays.asList('p','q','r','s')));
                    map.put('8',new ArrayList<Character>(Arrays.asList('t','u','v')));
                    map.put('9',new ArrayList<Character>(Arrays.asList('w','x','y','z')));
                    List<String> result = new ArrayList<String>();
            if(digits.length() == 0)
                return result;
            backTrack(0,digits,map,"",result);
            return result;
        }
        
        void backTrack(int index, String digits, Map<Character,List<Character>> map,String subResult,List<String> result) {
            if(index == digits.length()) {
                result.add(subResult);
                return;
            }
            for(char ch : map.get(digits.charAt(index))) {
                subResult+=ch;
                backTrack(index+1,digits,map,subResult,result);
                subResult=subResult.substring(0,subResult.length()-1);
            }
        }
    }
}