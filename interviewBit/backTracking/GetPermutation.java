import java.util.*;

/* 
https://www.interviewbit.com/problems/kth-permutation-sequence/
Ignore the editorial where it says first position of kth sequence is k/(n-1)!+1 integer for k 0 based index
It is k/(n-1)! , where k is 0 index based and nums are also 0 index based
 */
public class GetPermutation {

    public static void main(String[] args) {
        GetPermutation sol = new GetPermutation();

        // System.out.println("hello");
        System.out.println("Solution: "+sol.getPermutation(3, 4));
        System.out.println("Solution: "+sol.getPermutation(3, 6));

    }

    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<Integer>();
        for(int i=1;i<=n;i++){
            nums.add(i);
        }
        List<Integer> facts = new ArrayList<Integer>();
        int fact = 1;
        facts.add(fact);
        for(int i=1;i<=11;i++) {
            fact*=i;
            facts.add(fact);
        }
        for(int i=12;i<=n;i++) {
            facts.add(Integer.MAX_VALUE);
        }

        // System.out.println(nums);
        // System.out.println(facts);
        if(k>facts.get(n))
            return ""; // since only n! combinations exists
        //make k 0 based index
        return getPermutation(nums, facts, k-1);
    }

    String getPermutation(List<Integer> nums,List<Integer> facts,int k) {
        int n = nums.size();
        if(n == 0) return "";
        // System.out.println("n: "+n);
        int fact = facts.get(n-1);
        // System.out.println("fact: "+fact);
        int firstPosition = k/fact;
        int firstPositionInteger = nums.get(firstPosition);
        nums.remove(firstPosition);
        k%=fact;
        return firstPositionInteger+getPermutation(nums, facts, k);
    }

}