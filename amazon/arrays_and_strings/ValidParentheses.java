//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2972/
class ValidParentheses
 {
    public boolean isValid(String s) {
        if(s.length()%2 != 0)
            return false;
       Stack<Character> st = new Stack<Character>();
        for(int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{') {
                st.push(ch);
            }
            else{
                if(!st.isEmpty() && !helper(st.pop(),ch))
                    return false;
            }
        }
        return st.isEmpty();
    }
    
    public boolean helper(char c1,char c2) {
        System.out.println(c1+" "+c2);
        if(c1=='('&&c2==')' || c1=='['&&c2==']' || c1=='{'&&c2=='}') {
            return true;
        }
        else {
            return false; 
        } 
    }
}