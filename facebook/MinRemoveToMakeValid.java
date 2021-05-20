// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/solution/
class MinRemoveToMakeValid {
    /* 
    Approach 1: Using a Stack and String Builder
    We can use a stack. Each time we see a "(", we should add its index to the stack. Each time we see a ")", we should remove an index from the stack because the ")" will match with whatever "(" was at the top of the stack.
    The length of the stack is equivalent to the balance from above. We will need to do the:
       1. Remove a ")" if it is encountered when stack was already empty (prevent negative balance).
       2. Remove a "(" if it is left on stack at end (prevent non-zero final balance).

    Space : O(n),Time: O(n)
    */
    public String minRemoveToMakeValidApp1(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

/* 
Approach 2: Two Pass String Builder

A key observation you might have made from the previous algorithm is that for all invalid ")",
we know immediately that they are invalid (they are the ones we were putting in the set).
It is the "(" that we don't know about until the end (as they are what was left on the stack at the end).
We could be building up a string builder in that first loop that has all of the invalid ")" removed. This would be half the problem solved in the first loop, in O(n)time.

Now, another important observation is that we can use the same algorithm to remove the invalid "(". We just need to look at the string in reverse.
We do this by swapping the "(" and ")" for each other, and reversing the order of all characters in the string.

In code, it's best to pull out the common functionality of both passes, 
otherwise you will have almost the same code repeated twice. 
A good way to do this is to have a function that takes a string, a symbol to treat as the "open" parenthesis, and a symbol to treat as the "close" parenthesis.
The function then returns a string that has all invalid instances of the "closing" symbol removed. Then for the second pass, pass in the reversed string (that was returned from the first pass) and with the "open" and "close" symbols swapped.

Space : O(n),Time: O(n)

*/
    private StringBuilder removeInvalidClosing(CharSequence string, char keep, char remove) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == keep) {
                balance++;
            }
            if (c == remove) {
                // by continuing , we are not appending, therefore removing
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }  
        return sb;
    }

    public String minRemoveToMakeValidApp2(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }

/* 
Approach 3: Shortened Two Pass String Builder

This approach is a simplification of the previous one, and only needs to keep track of the balance. It does not need a stack. Instead of doing the full procedure twice, we can do the first pass and then look at the balance to see how many "(" we need to remove. It turns out that if we remove the rightmost '(', we are guaranteed to have a balanced string. So for the second pass, we only need to remove balance "(", starting from the right.

Consider a string s that contains no invalid ")" (it has had all the invalid ")" removed by the first pass of the algorithm). It's important to understand that we therefore know there is a way of removing balance "(" that will make it valid.
*/

    public String minRemoveToMakeValid(String s) {
    
        // Pass 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            } if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

        // Pass 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                // by continuing we are not adding any extra braces
                if (openToKeep < 0) continue;
            }
            result.append(c);
        }

        return result.toString();
    }

}