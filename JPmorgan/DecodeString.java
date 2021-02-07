import java.util.*;

class DecodeString {
    
    public static void main(String[] args) {
        DecodeString sol = new DecodeString();
        //not better
        System.out.println("Solution: "+sol.decodeStringOneStack("3[a]2[bc]"));
        System.out.println("Solution: "+sol.decodeStringOneStack("3[a2[c]]"));
        System.out.println("Solution: "+sol.decodeStringOneStack("2[abc]3[cd]ef"));
        System.out.println("Solution: "+sol.decodeStringOneStack("abc3[cd]xyz"));
        //better
        System.out.println("Solution: "+sol.decodeStringTwoStack("3[a]2[bc]"));
        System.out.println("Solution: "+sol.decodeStringTwoStack("3[a2[c]]"));
        System.out.println("Solution: "+sol.decodeStringTwoStack("2[abc]3[cd]ef"));
        System.out.println("Solution: "+sol.decodeStringTwoStack("abc3[cd]xyz"));
        //optimal
    }

    /* 
    Algorithm
    The input can contain an alphabet (a-z), digit (0-9), opening braces [ or closing braces ]. 
    Start traversing string s and process each character based on the following rules:

    Case 1) Current character is not a closing bracket ].
        Push the current character to stack.
    Case 2) Current character is a closing bracket ].
        Start decoding the last traversed string by popping the string decodedString and number k from the top of the stack.
        Pop from the stack while the next character is not an opening bracket [ and append each character (a-z) to the decodedString.
        Pop opening bracket [ from the stack.
        Pop from the stack while the next character is a digit (0-9) and build the number k.
    Now that we have k and decodedString , decode the pattern k[decodedString] by pushing the decodedString to stack k times.
    Once the entire string is traversed, pop the result from stack and return.


    space and time complexity
    Time Complexity: O((maxK^(countK))*n)
    maxK is the maximum value of k, countK is the count of nested k values and n is the maximum length of encoded string.
    Example, for s = 20[a10[bc]], maxK is 20, countK is 2 as there are 2 nested k values (20 and 10) . Also, there are 2 encoded strings a and bc with maximum length of encoded string ,n as 2
    The worst case scenario would be when there are multiple nested patterns. Let's assume that all the k values (maxK) are 10 and all encoded string(n) are of size 2.

    For, s = 10[ab10[cd]]10[ef], 
    time complexity would be roughly equivalent to 10*cd∗10∗ab+10∗2=10^2*2

    Hence, for an encoded pattern of form maxK[nmaxK[n]], 
    the time complexity to decode the pattern can be given as, O((maxK^(countK))*n)

    Space O(sum(maxK^(countK))*n)), where maxK is the maximum value of k, countK is the count of nested kk values and nn is the maximum length of encoded string.
    The maximum stack size would be equivalent to the sum of all the decoded strings in the form maxK[n*maxK[n]]
    */

    public String decodeStringOneStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }
            // push the current character to stack
            else {
                stack.push(s.charAt(i));
            }
        }      
        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }

    /*
    In the previous approach, we used a single character stack to store the digits(0-9) as well as letters (a-z).
    We could instead maintain 2 separate stacks.
    countStack: The stack would store all the integer k.
    stringStack: The stack would store all the decoded strings.
    Also, instead of pushing the decoded string to the stack character by character, we could improve our algorithm by appending all the characters into the string first and then push the entire string into the stringStack. Let's look at the algorithm in detail.

    Algorithm

    Iterate over the string s and process each character as follows:
    Case 1) If the current character is a digit (0-9), append it to the number k.
    Case 2) If the current character is a letter (a-z), append it to the currentString.
    Case 3) If current character is a opening bracket [, push k and currentString intocountStack and stringStack respectively.
    Case 4) Closing bracket ]: We must begin the decoding process,
    We must decode the currentString. Pop currentK from the countStack and decode the pattern currentK[currentString]
    As the stringStack contains the previously decoded string, pop the decodedString from the stringStack. Update the decodedString = decodedString + currentK[currentString]

    Time Complexity: O(maxK⋅n), where maxK is the maximum value of k and n is the length of a given string s.
    We traverse a string of size n and iterate k times to decode each pattern of form k[string]. This gives us worst case time complexity O(maxK⋅n).
    Space Complexity: O(m+n), where m is the number of letters(a-z) and nn is the number of digits(0-9) in string s.
     In worst case, the maximum size of stringStack and countStack could be m and n respectively.
    */
    public String decodeStringTwoStack(String word) {
        System.out.println("word:"+word);
        Stack<StringBuilder> strStack = new Stack<StringBuilder>();
        Stack<Integer> intStack = new Stack<Integer>();
        StringBuilder result = new StringBuilder("");
        StringBuilder currString = new StringBuilder("");
        int currMultiple = 0;
        for(char ch:word.toCharArray()) {
            if(Character.isDigit(ch)) {
                currMultiple = currMultiple*10+(ch-'0');
            }
            else if(ch == '[') {
                //push currmultiple
                intStack.push(currMultiple);
                //push curr String
                strStack.push(currString);
                //reset currMultiple and currString
                currString = new StringBuilder();
                currMultiple = 0;
            }
            else if(ch == ']') {
                int k = intStack.pop();
                StringBuilder poppedStr = strStack.pop();
                while(k != 0) {
                    poppedStr.append(currString);
                    k--;
                }
                currString = poppedStr;
            }
            else {
                currString.append(ch);
            }
        }
        return currString.toString();
    } 
/* 

In the previous approach, we implemented an external stack to keep the track of each character traversed. Ideally, a stack is required when we have nested encoded string in the form k[string k[string]].

Using this intuition, we could start by building k and string and recursively decode for each nested substring. The recursion uses an internal call stack to store the previous state. Let's understand the algorithm in detail.

Algorithm:
Build result while next character is letter (a-z) and build the number k while next character is a digit (0-9) by iterating over string s.
Ignore the next [ character and recursively find the nested decodedString.
Decode the current pattern k[decodedString] and append it to the result.
Return the current result.
The above steps are repeated recursively for each pattern until the entire string s is traversed.

Base Condition: We must define a base condition that must be satisfied to backtrack from the recursive call.
In this case, 
we would backtrack and return the result when we have traversed the string s 
or the next character is ] and there is no nested substring.

decodeString(str,0)
3[a2[b]]
i=0 => k=3
i=1 i++ => ignoring [ 
recurse decodeString(str,2)
    i=2 => result = a
    i=3 => k = 2
    i=4 i++ //ignore [
    recurse decodeString(str,5)
        i=5 result = b
    i=6 i++ //ignore ]
    result = abb
result = abbabbabb
Complexity Analysis

Assume, nn is the length of the string ss.

Time Complexity: O(maxK⋅n) as in Approach 2

Space Complexity: O(n). This is the space used to store the internal call stack used for recursion.
 As we are recursively decoding each nested pattern, the maximum depth of recursive call stack would not be more than n
*/
    int index = 0; //global variable
    String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index)))
                result.append(s.charAt(index++));
            else {
                int k = 0;
                // build k while next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index)))
                    k = k * 10 + s.charAt(index++) - '0';
                // ignore the opening bracket '['    
                index++;
                String decodedString = decodeString(s);
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                while (k-- > 0)
                    result.append(decodedString);
            }
        }
        return new String(result);
    }
}