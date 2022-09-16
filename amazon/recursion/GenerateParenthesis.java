import java.util.*;

//https://leetcode.com/explore/interview/card/amazon/84/recursion/2988/
class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }

    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<String>();
        gen(n,n,new StringBuilder(""),result);
        return result;
    }
    
    public void gen(int left, int right, StringBuilder tillNow, List<String> result) {
        if(left == 0 && right == 0) 
            result.add(tillNow.toString());
        if(left > right) {
            return;
        }
        if(left != 0) {        
            tillNow.append('(');
            gen(left-1,right,tillNow,result);
            tillNow.deleteCharAt(tillNow.length()-1);
        }
        if(right != 0) {        
            tillNow.append(')');
            gen(left,right-1,tillNow,result);
            tillNow.deleteCharAt(tillNow.length()-1);
        }
    }
}