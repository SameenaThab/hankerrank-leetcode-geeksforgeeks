//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2968/
class StrStr {
    public int strStr(String haystack, String needle) {
        int l = 0;
        int r = needle.length();
        while(r<=haystack.length()) {
            if(haystack.substring(l,r).equals(needle)) {
                return l;
            }
            l++;
            r++;
        }
        return -1;
    }
}