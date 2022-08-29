import java.util.*;

class Practise {

    public static void main(String[] args) {
        HashMap<String,Integer> mem = new HashMap<String,Integer>(); 
        System.out.println(countEval("1|1&0",true,mem));
    }

    public static int countEval(String str,boolean result,HashMap<String,Integer> mem) {
        if(str.length()==0)
            return 0;
        if(mem.containsKey(result+str))
            return mem.get(str);
        if(str.length()==1) {
            return strToBool(str)==result?1:0;
        }
        int ways=0;
        for(int i=1;i<str.length();i+=2) {
            String left  = str.substring(0,i);
            String right = str.substring(i+1);
            int leftTrue = countEval(left,true,mem);
            int leftFalse = countEval(left,false,mem);
            int rightTrue = countEval(right,true,mem);
            int rightFalse = countEval(right,false,mem);
            char c=str.charAt(i);
            int total = (leftTrue+leftFalse)*(rightTrue+rightFalse);
            int totalTrue = 0;
            if(c=='&') {
                totalTrue = leftTrue*rightTrue;
            } else if(c=='^') {
                totalTrue = leftTrue*rightFalse+leftFalse*rightTrue;
            } else {
                totalTrue = leftTrue*rightFalse+leftFalse*rightTrue+leftTrue*rightTrue;
            }
            int subWays = result?totalTrue:total-totalTrue;
            ways+=subWays;
        }
        mem.put(result+str,ways);
        return mem.get(result+str);
    }

    public static boolean strToBool(String str) {
        return str.equals("1")?true:false;
    }
}