import java.util.*;
class RomanToInt {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    public static int romanToInt(String s) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("M",1000);
        map.put("CM",900);
        map.put("D",500);
        map.put("CD",400);
        map.put("C",100);
        map.put("XC",90);
        map.put("L",50);
        map.put("XL",40);
        map.put("X",10);
        map.put("IX",9);
        map.put("V",5);
        map.put("IV",4);
        map.put("I",1);
        int result = 0;
        int i=0;
        int n=s.length();
        while(i<n) {
            if(i<n-1 && map.get(s.substring(i,i+1))<map.get(s.substring(i+1,i+2))) {
                result = result+map.get(s.substring(i,i+2));
                i=i+2;
            }
            else {
                result = result+map.get(s.substring(i,i+1));
                i++;
            }
        }
        
        return result;
    }
}