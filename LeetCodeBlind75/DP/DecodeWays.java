import java.util.*;

class DecodeWays {
    // https://leetcode.com/problems/decode-ways/

    /* 
    11106 => A + decode(1106) , K + decode(106) => A+AJK,K+JK
        decode(1106) => A+decode(106) => A+JK, K+decode(06) => invalid
            decode(106) => A + decode(06)=>invalid,  J + decode(6) => J+F 
    If char is '0' at beginning of string, then no decodes available
    eg: "06","0"
    For every character '1-9' at any index decode is available
    Keeping that in mind, we start at index=0
    if charAt(index)='0' return 0
    if index == st.length(), return 1, since end of string
    if index == st.length()-1, return 1, since end of string
    else return decode(index+1) + decode(index+2)(if and only if substr(index,index+1)<=26  
    
    Use memomization same same index will repeat
    eg: decode(1106,0) => A+decode(106),K+decode(06)
    decode(106) => A+decode(06)->repeated,J+decode(6)
    */
    public int numDecodings(String s) {
        Map<Integer,Integer> mem = new HashMap<Integer,Integer>();
        return numDecodings(s,0,mem);
    }

    /* 
    Time Complexity: O(N), where N is length of the string. Memoization helps in pruning the recursion tree and hence decoding for an index only once. Thus this solution is linear time complexity.
    Space Complexity: O(N). The dictionary used for memoization would take the space equal to the length of the string. There would be an entry for each index value. The recursion stack would also be equal to the length of the string.
    */
    private int numDecodings(String s, int index, Map<Integer, Integer> mem) {
        if(index == s.length())
            return 1;
        if(s.charAt(index) == '0')
            return 0;
        if(index == s.length()-1) //index == s.length-1, substr(indx,indx+2) throws exception
            return 1;
        if(mem.containsKey(index))
            return mem.get(index);
        int sub1 = numDecodings(s,index+1,mem);
        int sub2 = Integer.parseInt(s.substring(index, index+2))<=26? numDecodings(s,index+2,mem) : 0;
        mem.put(index,sub1+sub2);
        return sub1+sub2;
    }

    /* 
    Iterative Approach
    We use an array for DP to store the results for subproblems. 
    A cell with index i of the dp array is used to store the number of decode ways for substring of s from index 0 to index i-1.
    initialize dp array int[s.length+1], initialize first two index with 1
    eg: 3 2 6 every element in dp represent , no of decodeways till that index
    dp  1 1       => 1 1 1      =>  1 1 1 2
          3 2 6        3 2 6          3 2 6  ( since 26<=26 decodeWays(6) = all decodeways(till 3)+decodeWays(till 2))
    if substr(i-2,i)<=26
        dp[i] = dp[i-1]
    else
        dp[i] = dp[i-1]+dp[i-2]
    Algorithm:
    If the string s is empty or null we return the result as 0.
    Initialize dp array. dp[0] = 1 to provide the baton to be passed.
    If the first character of the string is zero then no decode is possible hence initialize dp[1] to 0, otherwise the first character is valid to pass on the baton, dp[1] = 1.
    Iterate the dp array starting at index 2. The index i of dp is the i-th character of the string s, that is character at index i-1 of s.
    We check if valid single digit decode is possible. This just means the character at index s[i-1] is non-zero. Since we do not have a decoding for zero. If the valid single digit decoding is possible then we add dp[i-1] to dp[i]. Since all the ways up to (i-1)-th character now lead up to i-th character too.
    We check if valid two digit decode is possible. This means the substring s[i-2]s[i-1] is between 10 to 26. If the valid two digit decoding is possible then we add dp[i-2] to dp[i].
    Once we reach the end of the dp array we would have the number of ways of decoding string s.

    Time Complexity: O(N), where NN is length of the string. We iterate the length of dp array which is N+1.
    Space Complexity: O(N). The length of the DP array.


    */

    public int numDecodingsIterative(String s) {
        int[] dp = new int[s.length()+1];
        dp[0]=1;
        // s = "026" => result will be 0 as dp[1]=0 
        dp[1] = s.charAt(0)=='0'? 0:1; 
        for(int i=2;i<dp.length;i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];  
             }
             
             // Check if successful two digit decode is possible.
             int twoDigit = Integer.valueOf(s.substring(i - 2, i));
             if (twoDigit >= 10 && twoDigit <= 26) { // 06 <=26 but not more than 10
                 dp[i] += dp[i - 2];
             }
        }
        System.out.println(Arrays.toString(dp));
        return dp[s.length()];
    }

    /* 
    In Iterative Approach,
    we are using an array dp to save the results for future.
     As we move ahead character by character of the given string, we look back only two steps. 
     For calculating dp[i] we need to know dp[i-1] and dp[i-2] only. 
     Thus, we can easily cut down our 
     O(N)  space requirement to O(1) by using only two variables to store the last two results.
    */

    public int numDecodingsIterativeSpaceOptimal(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int twoback = 1;
        // s = "026" => result will be 0 as dp[1]=0 
        int oneback = s.charAt(0)=='0'? 0:1;
        for(int i=2;i<=s.length();i++) {
            int current = 0;
            if(s.charAt(i - 1) != '0')
                current = oneback;         
            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) { // 06 <=26 but not more than 10
                current+=twoback;
            }
            twoback=oneback;
            oneback=current;
        }
        return oneback;
    }


    public static void main(String[] args) {
        DecodeWays sol = new DecodeWays();

        System.out.println("Solution: "+sol.numDecodings("11106"));
        System.out.println("Solution: "+sol.numDecodingsIterative("11106"));
        System.out.println("Solution: "+sol.numDecodingsIterativeSpaceOptimal("11106"));
    }
}