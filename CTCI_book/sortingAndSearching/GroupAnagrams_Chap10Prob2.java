import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* 
anagrams acre and race. when sorted give same word "acer"
create hashMap woth sortedword as key, to group anagrams
*/

public class  GroupAnagrams_Chap10Prob2 {
      
    public static void main(String[] args) {
        String[] arr = new String[]{"acre","happy","yapph","race","paphy","care"};
        GroupAnagrams_Chap10Prob2 solution = new GroupAnagrams_Chap10Prob2();
        solution.groupAnagrams(arr);
        System.out.println("Solution array A: "+Arrays.toString(arr));
    }

    private void groupAnagrams(String[] arr) {
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for(String str:arr) {
            String sorted = sortChar(str);
            List<String> list = map.getOrDefault(sorted, new ArrayList<String>());
            list.add(str);
            map.put(sorted,list);
        }
        int i=0;
        for(String str : map.keySet()) {
            List<String> list = map.get(str);
            for(String word:list) {
                arr[i++]=word;
            }
        }
    }

    String sortChar(String str) {
        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);
        return String.copyValueOf(charArr);
    }
}
