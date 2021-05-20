//https://leetcode.com/problems/remove-invalid-parentheses/solution/
class RemoveInvalidParentheses {

/* 
Approach BackTracking
Algorithm
1. Initialize an array that will store all of our valid expressions finally.
2. Start with the leftmost bracket in the given sequence and proceed right in the recursion.
3. The state of recursion is defined by the index which we are currently processing in the original expression. Let this index be represented by the character i. Also, we have two different variables left_count and right_count that represent the number of left and right parentheses we have added to our expression till now. These are the parentheses that were considered.
3. If the current character i.e. S[i] (considering S is the expression string) is neither a closing or an opening parenthesis, then we simply add this character to our final solution string for the current recursion.
4. However, if the current character is either of the two brackets i.e. S[i] == '(' or S[i] == ')', then we have two options. We can either discard this character by marking it an invalid character or we can consider this bracket to be a part of the final expression.
5. When all of the parentheses in the original expression have been processed, we simply check if the expression represented by expr i.e. the expression formed till now is valid one or not. The way we check if the final expression is valid or not is by looking at the values in left_count and right_count. For an expression to be valid left_count == right_count. If it is indeed valid, then it could be one of our possible solutions.
6. Even though we have a valid expression, we also need to keep track of the number of removals we did to get this expression. This is done by another variable passed in recursion called rem_count.
7. Once recursion finishes we check if the current value of rem_count is < the least number of steps we took to form a valid expression till now i.e. the global minima. If this is not the case, we don't record the new expression, else we record it.
Optimization:
We have to wait till the very end to decide if the expression formed in recursion is a valid expression or not. 
Is there a way for us to cutoff from some of the recursion paths early on because they wouldn't lead to a solution? The answer to this is Yes! The optimization is based on the following idea.
For a left bracket encountered during recursion, if we decide to consider it, then it may or may not lead to an invalid final expression.
It may lead to an invalid expression eventually if there are no matching closing bracket available afterwards. But, we don't know for sure if this will happen or not.
However, for a closing bracket, if we decide to keep it as a part of our final expression (remember for every bracket we have two options, either to keep it or to remove it and recurse further) and there is no corresponding opening bracket to match it in the expression till now, then it will definitely lead to an invalid expression no matter what we do afterwards.
Time Complexity : O(2^N) 
since in the worst case we will have only left parentheses in the expression and for every bracket we will have two options i.e. whether to remove it or consider it. Considering that the expression has N parentheses, the time complexity will be O(2^N)
Space Complexity : O(N) - recursion stack
*/

private Set<String> validExpressions = new HashSet<String>();
private int minimumRemoved;

private void reset() {
  this.validExpressions.clear();
  this.minimumRemoved = Integer.MAX_VALUE;
}

private void recurseBractack(
    String s,
    int index,
    int leftCount,
    int rightCount,
    StringBuilder expression,
    int removedCount) {

  // If we have reached the end of string.
  if (index == s.length()) {

    // If the current expression is valid.
    if (leftCount == rightCount) {

      // If the current count of removed parentheses is <= the current minimum count
      if (removedCount <= this.minimumRemoved) {

        // Convert StringBuilder to a String. This is an expensive operation.
        // So we only perform this when needed.
        String possibleAnswer = expression.toString();

        // If the current count beats the overall minimum we have till now
        if (removedCount < this.minimumRemoved) {
          this.validExpressions.clear();
          this.minimumRemoved = removedCount;
        }
        this.validExpressions.add(possibleAnswer);
      }
    }
  } else {

    char currentCharacter = s.charAt(index);
    int length = expression.length();

    // If the current character is neither an opening bracket nor a closing one,
    // simply recurse further by adding it to the expression StringBuilder
    if (currentCharacter != '(' && currentCharacter != ')') {
      expression.append(currentCharacter);
      this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
      expression.deleteCharAt(length);
    } else {

      // Recursion where we delete the current character and move forward
      this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
      expression.append(currentCharacter);

      // If it's an opening parenthesis, consider it and recurse
      if (currentCharacter == '(') {
        this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
      } else if (rightCount < leftCount) {
        // For a closing parenthesis, only recurse if right < left
        this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
      }

      // Undoing the append operation for other recursions.
      expression.deleteCharAt(length);
    }
  }
}


    public List<String> removeInvalidParentheses(String s) {
        //BackTrack
        // this.reset();
        // this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
        // return new ArrayList(this.validExpressions);
        //Optimal
        int[] invalidBraces = findInvalidBraces(s);
        int invalidOpen = invalidBraces[0];
        int invalidClose = invalidBraces[1];
        Set<String> result = new HashSet<>();
        recurseOptimal(s,0,invalidOpen,invalidClose,0,0,result,new StringBuilder());
        return new ArrayList<String>(result);
    }
    
/* 
The one thing all these valid expressions have in common is that they will all be of the same length i.e. as compared to the original expression, all of these expressions will have the same number of characters removed.
So if we can find the no of invalid (,) to be removed, we dont have to trial and error with each brace we encounter
Depending on no of invalid braces remaining we can stop recursion

Algorithm
The overall algorithm remains exactly the same as before. The changes that we will incorporate are listed below:
1.The state of the recursion is now defined by five different variables:
2.index which represents the current character that we have to process in the original string.
3.left_count which represents the number of left parentheses that have been added to the expression we are building.
4.right_count which represents the number of right parentheses that have been added to the expression we are building.
5.left_rem is the number of left parentheses that remain to be removed.
6.right_rem represents the number of right parentheses that remain to be removed. Overall, for the final expression to be valid, left_rem == 0 and right_rem == 0.
7.When we decide to not consider a parenthesis i.e. delete a parenthesis, be it a left or a right parentheses, we have to consider their corresponding remaining counts as well. This means that we can only discard a left parentheses if left_rem > 0 and similarly for the right one we will check for right_rem > 0.
8.There are no changes to checks for considering a parenthesis. Only the conditions change for discarding a parenthesis.
9.Condition for an expression being valid in the base case would now become left_rem == 0 and right_rem == 0. Note that we don't have to check if left_count == right_count anymore because in the case of a valid expression, we would have removed all the misplaced or invalid parenthesis by the time the recursion ends. So, the only check we need if left_rem == 0 and right_rem == 0.

Time Complexity : The optimization that we have performed is simply a better form of pruning. Pruning here is something that will vary from one test case to another. In the worst case, we can have something like ((((((((( and the left_rem = len(S) and in such a case we can discard all of the characters because all are misplaced. So, in the worst case we still have 2 options per parenthesis and that gives us a complexity of O(2^N)
Space Complexity : The space complexity remains the same i.e. O(N) as previous solution. We have to go to a maximum recursion depth of NN before hitting the base case. Note that we are not considering the space required to store the valid expressions. We only count the intermediate space here.
*/
    void recurseOptimal(String s,int index,int openRem,int closeRem,int leftCount,int rightCount,Set<String> result,StringBuilder sb) {
        if(index == s.length()) {
            if(openRem == 0 && closeRem == 0) {
                result.add(sb.toString());
            }                     
            return;
        }
        char ch = s.charAt(index);
        if((ch == '(' && openRem>0) || (ch == ')' && closeRem>0)) {  
            // removing the character which is a brace
            recurse(
                s,
                index+1,
                openRem-(ch=='('?1:0),
                closeRem-(ch==')'?1:0),
                leftCount,
                rightCount,
                result,
                sb);
        }
        sb.append(ch);
        if(ch != '(' && ch != ')')   
            recurse(s,index+1,openRem,closeRem,leftCount,rightCount,result,sb);
        else if(ch == '(')
            recurse(s,index+1,openRem,closeRem,leftCount+1,rightCount,result,sb);
        // when ch is ')'
        else if(rightCount<leftCount)
            recurse(s,index+1,openRem,closeRem,leftCount,rightCount+1,result,sb);
        sb.deleteCharAt(sb.length()-1);
    }
    
    private int[] findInvalidBraces(String s) {
        int left = 0;
        int right = 0;
        for(char ch:s.toCharArray()) {
            if(ch == '(') {
                left++;
            }
            if(ch == ')') {
                if(left == 0)
                    right++;
                else
                    left--;
            }
         }
        return new int[]{left,right};
    }
    
    
}