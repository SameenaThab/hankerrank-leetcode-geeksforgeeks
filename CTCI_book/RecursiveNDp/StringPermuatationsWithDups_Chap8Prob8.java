import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;
import java.lang.reflect.Array;

public class  StringPermuatationsWithDups_Chap8Prob8 {

    public static void main(String[] args) {
        StringPermuatationsWithDups_Chap8Prob8 sol = new StringPermuatationsWithDups_Chap8Prob8();
        String st = "aabb";
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(char ch:st.toCharArray()) {
            int count = map.getOrDefault(ch, 0);
            map.put(ch,count+1);
        }
        ArrayList<String> result3 = new ArrayList<String>();
        sol.permutations("",st.length(),result3,map);
        System.out.println(result3);
    }

    // string = aabb
    // {a=2,b=2}
    // iterate prefix thru keySet of string, that way no duplicate character and remainder string 
    // combination come across
    // prefix=a  => a+perm{a=1,b=2}
    //     perm({a=1,b=2}) => a+perm({a=0,b=2})
    //                     => b+perm({a=1,b=1})
    //         perm({a=0,b=2}) => b+perm({a=0,b=1})
    void permutations(String prefix,int remainder,ArrayList<String> result,Map<Character,Integer> map) {        
        // System.out.println(prefix);
        if(remainder == 0) {
            System.out.println("Base case: "+prefix);
            result.add(prefix);
        }
        else {
            for(char ch:map.keySet()) {
                int count = map.get(ch);
                if(count>0) {
                    map.put(ch,count-1);
                    permutations(prefix+ch,remainder-1,result,map);
                    map.put(ch,count);
                }
            }
        }        
    }    
}
