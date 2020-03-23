class GroupAnagrams {
    //https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2970/
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for(String s:strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            //System.out.println(key);
            List<String> lt = map.getOrDefault(key,new ArrayList<String>());
            lt.add(s);
            map.put(key,lt);
        }
        List<List<String>> result = new ArrayList<List<String>>();
        for(String key:map.keySet()) {
            //System.out.println(key);
            result.add(map.get(key));
        }
        return result;
    }
}