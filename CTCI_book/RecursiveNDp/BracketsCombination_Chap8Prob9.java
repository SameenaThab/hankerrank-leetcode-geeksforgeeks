import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;
import java.lang.reflect.Array;

public class  BracketsCombination_Chap8Prob9 {

    public static void main(String[] args) {
        BracketsCombination_Chap8Prob9 sol = new BracketsCombination_Chap8Prob9();
        int n = 4;
        Set<String> set = sol.bracketCombinations(n);
        System.out.println(set);
        // No duplicates method more efficient
        List<String> list = new ArrayList<String>();
        char[] str = new char[n*2];
        int leftParens = n;
        int rightParens = n;
        sol.bracketCombinations(list,leftParens,rightParens,str,0);
        System.out.println(list);
    }

    //Efficient and no duplicate methods
    // 1. as long as "(" are not used up, we can always insert a left paren
    // 2. we can add right paren, as long as string is valid .
    //     String is valid when right parens are more than left parens in the String
    //     therefore rightparens remaining are less than left parens give a invalid string
    // Bcoz we insert left and right paren at each index and never
    //  repeat an index each string is guaranteed to be unique
    void bracketCombinations(List<String> list,int leftRem,int rightRem,char[] str,int index) {
        // rightRem and leftRem represent remaining parens  to be added to the string
        // for n = 2 , str = "())" -> leftRem = 1, rightRem = 0 => illegal string
        if(leftRem<0 || rightRem<leftRem) 
            return;
        // System.out.println("leftRem: "+leftRem+" rightRem: "+rightRem);
        if(leftRem == 0 && rightRem == 0)  {// No parens left to add means string is done
            list.add(String.copyValueOf(str));
            return;
        }
        str[index]='(';
        bracketCombinations(list,leftRem-1,rightRem,str,index+1);
        str[index]=')';
        bracketCombinations(list,leftRem,rightRem-1,str,index+1);
    }

    // case 1 -> ()
    // case 2 -> (()) ()() 
    // case 3 -> ()(()),(()()),((())),(())(),()()(),(())()
    // insert () in each '(' occurance of set(case 2)
    //  prefix () at string of set(case 2)
    // (()) -> insert () at index 0 -> (()())
    //      -> insert () at index 1 -> ((()))
    //      -> prefix () -> ()(())
    // ()() -> insert () at index 0 -> (())()
    //      -> insert () at index 2 -> ()(())
    //      -> prefix () -> ()()()

    Set<String> bracketCombinations(int n) {
        Set<String> set = new HashSet<String>();
        if(n==0){
            set.add("");
        } else {
            Set<String> subset = bracketCombinations(n-1);
            for(String st:subset) {
                for(int i=0;i<st.length();i++) {
                    if(st.charAt(i) == '('){
                        set.add(insert(st,i));
                    }
                }
               set.add("()"+st); 
            }
        }
        return set;
    }

    String insert(String st,int i) {
        String left = st.substring(0,i+1);
        String right = st.substring(i+1);
        return left+"()"+right;
    }


}
