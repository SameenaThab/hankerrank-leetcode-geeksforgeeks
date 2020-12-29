import java.util.*;

public class Combinations {

    public static void main(String[] args) {
        Combinations sol = new Combinations();
        System.out.println("Solution: "+sol.combine(4,2));

    }

    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> subset = new ArrayList<Integer>();
        if(n < k) 
            return result;
        combineHelper(result,subset,1,n,k);
        return result;
    }

    public void combineHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> subset, int index, int n, int k) {
        if(k==0) {
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        for(int i=index;i<=n;i++) {
            subset.add(i);
            combineHelper(result, subset, i+1, n, k-1);
            subset.remove(subset.size()-1);
        }
    }
}