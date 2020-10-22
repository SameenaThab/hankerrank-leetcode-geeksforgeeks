// https://leetcode.com/discuss/interview-question/506181/postmates-oa-2020-close-strings

import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CloseStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter String1");     
        String st1 = scanner.nextLine();
        System.out.println("Enter String2");     
        String st2 = scanner.nextLine();
        System.out.println(closeStrings2(st1,st2));
   }

   public static boolean closeStrings2(String st1,String st2) {
    if(st1.length() != st2.length())
        return false;
    Map<Character,Integer> map1 = new HashMap<Character,Integer>();
    Map<Character,Integer> map2 = new HashMap<Character,Integer>();
    for(char ch:st1.toCharArray()) {
        map1.put(ch,map1.getOrDefault(ch, 0)+1);
    }
    for(char ch:st2.toCharArray()) {
         if(!map1.containsKey(ch))
             return false;
         map2.put(ch,map2.getOrDefault(ch, 0)+1);
    }
    for(char ch1:st1.toCharArray()) {
        if(map1.get(ch1) != map2.get(ch1)) {
            for(HashMap.Entry<Character,Integer> entry : map1.entrySet()) {
                if(entry.getValue() == map2.get(ch1)) {
                    int temp = map1.get(ch1);
                    map1.put(ch1,entry.getValue());
                    map1.put(entry.getKey(),temp);
                }
            }
        }

        if(map1.get(ch1)!=map2.get(ch1))
            return false;
    }
    return true;
 }

   public static boolean closeStrings(String st1,String st2) {
       if(st1.length() != st2.length())
           return false;
       Map<Character,Integer> map1 = new HashMap<Character,Integer>();
       Map<Character,Integer> map2 = new HashMap<Character,Integer>();
       for(char ch:st1.toCharArray()) {
           map1.put(ch,map1.getOrDefault(ch, 0)+1);
       }
       for(char ch:st2.toCharArray()) {
            if(!map1.containsKey(ch))
                return false;
            map2.put(ch,map2.getOrDefault(ch, 0)+1);
       }
    //    if(map1.keySet().equals(map2.keySet())){
    //        System.out.println("came here");
    //        return false;
    //    }

       List<Integer> list1 = new ArrayList<>(map1.values());
       List<Integer> list2 = new ArrayList<>(map2.values());
       Collections.sort(list1);
       Collections.sort(list2); 
       if(!list1.equals(list2))
            return false;
        return true;
    }

}