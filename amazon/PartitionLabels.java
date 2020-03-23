class PartitionLabels {
    //https://leetcode.com/explore/interview/card/amazon/82/others/3004/
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<Integer>();
        int[] last = new int[26];
        for(int i=0;i<S.length();i++) {
            last[S.charAt(i)-'a'] = i;
        }
        int anchor = 0;
        int j = 0;
        for(int i=0;i<S.length();i++) {
            j = Math.max(j,last[S.charAt(i)-'a']);
            if(i == j) {
                result.add(j-anchor+1);
                anchor = i+1;
            }
        }
        return result;
    }
}