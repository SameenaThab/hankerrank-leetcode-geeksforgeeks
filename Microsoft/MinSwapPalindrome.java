class MinSwapPalindrome {

    /* 
    Check if the word can be a palindorme or not eg: "abcd" cannont be palindorme
    intialize left and right pointers;
    if charAt(left) == charAt(right), left++,right--, shrink the window
    else
        find the char that matches to start btw start+1 to right
        if found keep swap character until start and end are same . keep incrementing swaps
            eg: mamad left=0(m), right=4(d) found m at index to 2 swap every char btw 2 to 4
                => maamd -> maadm
        else swap char start and start+1. keep incrementing swaps
            eg: damam left=0(d), right=4(m) d isnt found
                => admam
        left++,right--, shrink the window
    */
    int minSwapPalindrome(String word) {
        if(word.length()==0 || cannotBePalidrome(word))
            return -1;
        int swaps = 0;
        char[] arr = word.toCharArray(); // arr makes it easy to swap
        int left=0,right=word.length()-1;
        while(left<right) {
            if(arr[left] != arr[right]) {
                int i = right;
                // find a character which is equal to char at 'left'
                while(i>left && arr[i] != arr[left])
                    i--;
                if(i == left) {
                    /* 
                    happens in case of "damam" (left = d, right=m => swap to "admam")
                    */
                    swap(arr,left,left+1);
                    swaps++;
                } else {
                    while(i<right) {
                        /* 
                        swap until the end , so that left and right match
                        mamad (left=m,right=d) becomes maadm after all swaps
                        */
                        swap(arr,i,i+1); 
                        i++;
                        swaps++;
                    }
                }
            }
            left++;
            right--;
        }
        return swaps;
    }

    private void swap(char[] arr, int x, int y) {
        char temp = arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }

    private boolean cannotBePalidrome(String word) {
        int[] characters = new int[26];
        for(char ch:word.toCharArray()) {
            characters[ch-'a']++;
        }
        int odd=0;
        for(int i=0;i<26;i++){
            if(characters[i]%2 != 0)
                odd++;
        }
        return odd>1; // # of odd characters >1 then cannot be a palindrome
    }

    public static void main(String[] args) {
        MinSwapPalindrome sol = new MinSwapPalindrome();
        System.out.println("Ans for mamad: "+sol.minSwapPalindrome("mamad"));
        System.out.println("Ans for damam: "+sol.minSwapPalindrome("damam"));
        System.out.println("Ans for abde: "+sol.minSwapPalindrome("abde"));
    }
}