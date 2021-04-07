class CanPermutePalindrome {
    public boolean canPermutePalindrome(String s) {
        int[] alphabets = new int[26];
        for(char ch:s.toCharArray()) {
            alphabets[ch-'a']+=1;
        }
        int odd=0;
        for(int i=0;i<26;i++) {
            if(alphabets[i]%2 != 0)
                odd++;
        }
        return odd<=1;     
    }

    public boolean canPermuteSinglePassAndIncludesAllCases(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }
}