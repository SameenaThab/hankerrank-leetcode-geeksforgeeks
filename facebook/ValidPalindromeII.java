class ValidPalindromeII {
//https://leetcode.com/problems/valid-palindrome-ii/submissions/
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while(left<right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {               
                // remove left and check
                return isPalindrome(s,left+1,right)
                    //remove right and check
                    || isPalindrome(s,left,right-1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String st,int left,int right) {
        while(left<right) {
            if(st.charAt(left) != st.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
    
}