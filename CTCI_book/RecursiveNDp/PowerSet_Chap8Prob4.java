import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class  PowerSet_Chap8Prob4 {

    Set<Integer> set;

    public static void main(String[] args) {
        PowerSet_Chap8Prob4 sol = new PowerSet_Chap8Prob4();
        List<Integer> list = Arrays.asList(1, 2, 3);
        Set<Integer> set = new HashSet<Integer>(list);
        sol.set = set;
        ArrayList<ArrayList<Integer>> result = sol.powerSet(list,list.size()-1);
        for(ArrayList<Integer> lt: result) {
            System.out.println(lt);
        }
        ArrayList<ArrayList<Integer>> result2 = sol.powerSet(list);
        for(ArrayList<Integer> lt: result2) {
            System.out.println(lt);
        }
    }

    // emptySet - {}
    // {a0} -> {},{a0} -> powerSet(emptySet)*a0 union powerSet(emptySet)
    // {a0,a1} -> {},{a0},{a1},{a0,a1} -> powerSet({a0})*a1 union powerSet({a1})

    public ArrayList<ArrayList<Integer>> powerSet(List<Integer> set,int size) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        //base case
        if(size == -1) {
            System.out.println("Once");
            result.add(new ArrayList<Integer>()); // add empty set
        } else {
            ArrayList<ArrayList<Integer>> subresult = powerSet(set,size-1);
            int lastInteger = set.get(size);
            System.out.println("lastInteger: "+lastInteger);
            result.addAll(subresult);
            ArrayList<ArrayList<Integer>> extraSets = new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> list: subresult) {
                ArrayList<Integer> subset = new ArrayList<Integer>();
                subset.addAll(list);
                subset.add(lastInteger);
                extraSets.add(subset);
            }
            result.addAll(extraSets);
        }
        return result;
    }

    // {1,2}
    // 2^size = 4 subsets
    // binary -> set
    // 00 -> {}
    // 01 -> {2}
    // 10 -> {1}
    // 11 -> {1,2}

    public ArrayList<ArrayList<Integer>> powerSet(List<Integer> set) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int power = 1<<set.size(); // leftShift n times gives 2^n 
        for(int k = 0;k<power;k++) {
            ArrayList<Integer> subset = convertToSet(k,set);
            result.add(subset);
        }
        return result;
    }

    public ArrayList<Integer> convertToSet(int binary,List<Integer> set) {
        int index = 0;
        ArrayList<Integer> subset = new ArrayList<Integer>() ;
        for(int i=binary;i>0;i>>=1)  // rightshift by 1 divides by 2; 
        {
            if( (i&1) ==1) {
                subset.add(set.get(index));
            }
            index++;
        }
        return subset;
    }

}
