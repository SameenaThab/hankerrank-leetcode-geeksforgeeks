import java.util.*;

public class GenerateParenthesis {

    public static void main(String[] args) {
        GenerateParenthesis sol = new GenerateParenthesis();
        System.out.println("Solution: "+sol.generateParenthesis(3));
    }

    public ArrayList<String> generateParenthesis(int n) {
        // Set<String> visited = new HashSet<String>();
        // return helper(n,visited);
        //backtracking,efficient and no visited set needed . For explantation check chap8Prob9 in CTCI
        int leftRem = n;
        int rightRem = n;
        char[] comb = new char[2*n];
        ArrayList<String> result = new ArrayList<String>();
        generateParenthesisBT(leftRem,rightRem,comb,result,0);
        return result;
    }

    private void generateParenthesisBT(int leftRem, int rightRem, char[] comb, ArrayList<String> result,int index) {
        if(leftRem<0 || rightRem < leftRem)
            return;
        if(leftRem == 0 && rightRem == 0) {
            result.add(String.copyValueOf(comb));
            return;
        }
        comb[index]='(';
        generateParenthesisBT(leftRem-1,rightRem,comb,result,index+1);
        comb[index]=')';
        generateParenthesisBT(leftRem, rightRem-1, comb, result, index+1);
    }

    private ArrayList<String> helper(int n, Set<String> visited) {
        ArrayList<String> result = new ArrayList<String>();
        if(n == 1) {
            result.add("()");
            return result;
        }
        ArrayList<String> subResult = helper(n-1,visited);
        for(String comb : subResult) {
            for(int i=0;i<comb.length();i++) {
                String newComb = comb.substring(0,i)+"()"+comb.substring(i);
                if(!visited.contains(newComb)) {
                    visited.add(newComb);
                    result.add(newComb);
                }
            }
        }

        return result;
    }


}
