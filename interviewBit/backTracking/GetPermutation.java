import java.util.*;

/* 
https://www.interviewbit.com/problems/kth-permutation-sequence/
Lets first make k 0 based.
Let us first look at what our first number should be.
Number of sequences possible with 1 in first position : (n-1)!
Number of sequences possible with 2 in first position : (n-1)!
Number of sequences possible with 3 in first position : (n-1)!

Hence, the number at our first position should be k / (n-1)! th integer.

1. "123"
2. "132"

3. "213"
4. "231"

5. "312"
6. "321"

n = 3 , k = 4, make k 0 based, therefore k = 3, position=(k=3)/(3-1)! = 1
 In {1,2,3} , k =1 => value 2
 remove 2 from set {1,3} k=%(3-1)! = 3%2 = 1
 position = (k=1)/1! = 1, value = 3 in set {1,3}
k%1 = 0 {1} position = 0/(1-1)! = 0/1 = 0
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
            facts.add(Integer.MAX_VALUE); // factorial 12 exceeds MAX_INT
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