//https://leetcode.com/explore/interview/card/amazon/84/recursion/521/
class PhoneLetters {
    public List<String> letterCombinations(String digits) {
        Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
        map.put(2,Arrays.asList(new String[]{"a","b","c"}));
        map.put(3,Arrays.asList(new String[]{"d","e","f"}));
        map.put(4,Arrays.asList(new String[]{"g","h","i"}));
        map.put(5,Arrays.asList(new String[]{"j","k","l"}));
        map.put(6//, Arrays.asList(new String[]{"m","n","o"}));
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
}