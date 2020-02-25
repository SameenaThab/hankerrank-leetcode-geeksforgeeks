//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2973/
class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        Set<String> set = new HashSet<String>();
        for(String word:banned) {
            set.add(word);
        }
        
        StringBuilder sb = new StringBuilder();
        int max = 0;
        String maxWord = "";
        for(char ch:paragraph.toCharArray()) {
            if(Character.isLetter(ch)) {
                sb.append(Character.toLowerCase(ch));
            }              
            else if(sb.length() > 0){
                System.out.println("here");
                String finalWord = sb.toString();
                if(!set.contains(finalWord)) {
                    map.put(finalWord,map.getOrDefault(finalWord,0)+1);
                    int count = map.get(finalWord);
                    if(count>max) {
                        max = count;
                        maxWord = finalWord;
                    }
                }
                sb = new StringBuilder();
            }
        }
        
        return max>0? maxWord:sb.toString();
        
    }
}