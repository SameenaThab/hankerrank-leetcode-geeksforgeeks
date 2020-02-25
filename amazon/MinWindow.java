//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/902/
class MinWindow {
    public String minWindow(String s, String t) {
       if (s.length() == 0 || t.length() == 0) {
          return "";
        }
        Map<Character,Integer> tmap = new HashMap<Character,Integer>();
        Map<Character,Integer> winMap = new HashMap<Character,Integer>();
        for(char ch:t.toCharArray()){
            tmap.put(ch,tmap.getOrDefault(ch,0)+1);
        }
        
        //required characters to match with same freq
        int req = tmap.size();
        int l=0,r=0;
        int[] ans = new int[3];
        ans[0] = -1;
        ans[1] = 0;
        ans[2] = 0;
        int formed=0;
        while(r<s.length()) {
            char ch = s.charAt(r);
            winMap.put(ch,winMap.getOrDefault(ch,0)+1);
            //int count = winMap.get(ch);
            if(tmap.containsKey(ch) && tmap.get(ch).intValue() == winMap.get(ch).intValue())
                formed++; // because the freq of character matches btw s and t
            //contracting left pointer
            while(l<=r && formed == req) {
              if (ans[0] == -1 || r - l + 1 < ans[0]) {
                  ans[0] = r - l + 1;
                  ans[1] = l;
                  ans[2] = r;
              }
                char c2 = s.charAt(l);
                winMap.put(c2,winMap.get(c2)-1);
                if(tmap.containsKey(c2) && tmap.get(c2).intValue() > winMap.get(c2).intValue())
                    formed--;
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}