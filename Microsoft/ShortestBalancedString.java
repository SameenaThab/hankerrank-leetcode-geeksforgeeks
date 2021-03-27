import java.util.*;
/* A string is considered balanced when every letter in the string appears both in uppercase and lowercase
For example, CATattac is balanced (a, c, t occur in both cases). Madam is not (a, d only appear in lowercase).
Write a function that given a string returns the shortest balanced substring of that string.
Can this be solved with a sliding window approach?
Update:
More examples
“azABaabza” returns “ABaab”
“TacoCat” returns -1 (not balanced)
“AcZCbaBz” returns the entire string
 */
 class ShortestBalancedString {


    int[] lower;
    int[] upper;
    String str;
    int minLength;
    int minStart;
    int[][] visited;
    

    /* 
    Time Complexity : O(2^n) after memorization we do not visit repeated nodes
                abcd
            abc             bcd
        ab       bc      bc       cd
     a      b   b   c   b   c   c    d  => no of nodes = 2^n
    Space complexity: Arrays lower and upper are constant, mem = n^2

    */
    String shortestBalancedString(String str) {
        this.str=str;
        this.lower = new int[26];
        this.upper = new int[26];
        this.minLength = Integer.MAX_VALUE;
        this.minStart = 0;
        this.visited = new int[str.length()][str.length()];
        for(char ch:str.toCharArray()) {
            if(ch>='a' && ch<='z') {
                lower[ch-'a']++;
            } else {
                upper[ch-'A']++;
            }
        }  
        // System.out.println(Arrays.toString(upper));
        // System.out.println(Arrays.toString(lower));
        recurse(0,str.length()-1);
        if(minLength == Integer.MAX_VALUE)
            return "Error: string cannot be balanced";
        return str.substring(minStart, minStart+minLength);
    }

    private void recurse(int start, int end) {
        if(start >= end || visited[start][end] == 1)
            return;
        visited[start][end]=1;
        if(balanced()) {
            // System.out.println(Arrays.toString(upper));
            // System.out.println(Arrays.toString(lower));
            // System.out.println(str.subSequence(start, end+1));

            if(minLength > end-start+1) {
                minLength = end-start+1;
                minStart = start;
            }
        }
        decrement(str.charAt(end));
        recurse(start,end-1);
        increment(str.charAt(end));

        decrement(str.charAt(start));
        recurse(start+1,end);
        increment(str.charAt(start));
    }

    private boolean balanced() {
        for(int i=0;i<26;i++){
            if((lower[i]==0 && upper[i]!=0) || (lower[i]!=0 && upper[i]==0))
                return false;
        }
        return true;
    }

    void decrement(char ch) {
        if(ch>='a' && ch<='z') {
            lower[ch-'a']--;
        } else {
            upper[ch-'A']--;
        }
    }

    void increment(char ch) {
        if(ch>='a' && ch<='z') {
            lower[ch-'a']++;
        } else {
            upper[ch-'A']++;
        }
    }

    String shortestBalancedStringSlidingWindow(String s)
    {
         
        // Store frequency of
        // lowercase characters
        int[] small = new int[26];
     
        // Stores frequency of
        // uppercase characters
        int[] caps = new int[26];
     
        // Count frequency of characters
        for(int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if(ch>='a' && ch<='z') {
                small[ch-'a']++;
            } else {
                caps[ch-'A']++;
            }
        }
     
        // Mark those characters which
        // are not present in both
        // lowercase and uppercase
        Map<Character,Integer> mp = new HashMap<Character,Integer>();
     
        for(int i = 0; i < 26; i++)
        {
            if (small[i] != 0 && caps[i] == 0)
                mp.put((char)(i + 'a'), 1);
            else if (caps[i] != 0 && small[i] == 0)
                mp.put((char)(i + 'A'), 1);
        }
     
        // Initialize the frequencies
        // back to 0
        Arrays.fill(small, 0);
        Arrays.fill(caps, 0);
     
        // Marks the start and
        // end of current substring
        int r = 0, l = 0;
     
        // Marks the start and end
        // of required substring
        int start = -1, end = -1;
     
        // Stores the length of
        // smallest balanced substring
        int minm = Integer.MAX_VALUE;
     
        while (r < s.length())
        {
            if (mp.get(s.charAt(r)) != null)
            {
                 
                // Remove all characters
                // obtained so far
                while (l < r)
                {
                    if(s.charAt(l) >='a' && s.charAt(l) <='z') {
                        small[s.charAt(l) -'a']--;
                    } else {
                        caps[s.charAt(l) -'A']--;
                    }
     
                    l++;
                }
                r++;
                l = r;
            }
            else
            {
                if(s.charAt(r)>='a' && s.charAt(r)<='z')
                    small[s.charAt(r) - 'a']++;
                else
                    caps[s.charAt(r) - 'A']++;
     
                // Remove extra characters from
                // front of the current substring
                while (true)
                {
                    if (s.charAt(l)>='A' && s.charAt(l)<='Z' &&
                        caps[s.charAt(l) - 'A'] > 1)
                    {
                        caps[s.charAt(l) - 'A']--;
                        l++;
                    }
                    else if (s.charAt(l)>='a' && s.charAt(l)<='z' &&
                             small[s.charAt(l) - 'a'] > 1)
                    {
                        small[s.charAt(l) - 'a']--;
                        l++;
                    }
                    else
                        break;
                }
     
                // If substring (st, i) is balanced
                if (balanced(small, caps))
                {
                    if (minm > (r - l + 1))
                    {
                        minm = r - l + 1;
                        start = l;
                        end = r;
                    }
                }

                r ++;
            }
        }
     
        // No balanced substring
        if (start == -1 || end == -1)
            return "Error: string cannot be balanced";
     
        // Store answer string
        else
        {
            return s.substring(start, end+1);
        }
    }

    private boolean balanced(int[] lower, int[] upper) {
        for(int i=0;i<26;i++){
            if((lower[i]==0 && upper[i]!=0) || (lower[i]!=0 && upper[i]==0))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ShortestBalancedString sol = new ShortestBalancedString();
        System.out.println("Solution: "+sol.shortestBalancedString("azABaabza"));
        System.out.println("Solution: "+sol.shortestBalancedString("TacoCat"));
        System.out.println("Solution: "+sol.shortestBalancedString("AcZCbaBz"));


        // System.out.println("Solution: "+sol.shortestBalancedStringSlidingWindow("azABaabza"));
        // System.out.println("Solution: "+sol.shortestBalancedStringSlidingWindow("TacoCat"));
        // System.out.println("Solution: "+sol.shortestBalancedStringSlidingWindow("AcZCbaBz"));
    }
 }