import java.util.*;

public class Permutations {

    public static void main(String[] args) {
        Permutations sol = new Permutations();
        System.out.println("Solution: "+sol.permute(new ArrayList<Integer>(Arrays.asList(1,2,3))));
    }

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> comb = new ArrayList<Integer>();
        LinkedList<Integer> list2 = new LinkedList<Integer>(list);
        // permuteBT(result,comb,list);
        //Efficient and no temp list needed.
        permuteBTOptimal(result,list,0,list.size());
        return result;
    }

    private void permuteBTOptimal(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int left, int right) {
        if(left == right) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i=left;i<right;i++) {
            swap(list,left,i);
            permuteBTOptimal(result, list, left+1, right);
            swap(list,left,i);
        }
    }

    private void swap(ArrayList<Integer> list, int x, int y) {
        int temp = list.get(x);
        list.set(x,list.get(y));
        list.set(y,temp);
    }

    private void permuteBT(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> comb, ArrayList<Integer> list) {
        int size = list.size();
        // System.out.println("size: "+size);
        if(size == 0) {
            // System.out.println("size: "+size);
            result.add(new ArrayList<Integer>(comb));
            return;
        }
        for(int i=0;i<size;i++) {
            // System.out.println("size: "+size+" realsize: "+list.size());
            int curr = list.get(i);
            comb.add(curr);
            ArrayList<Integer> temp = new ArrayList<Integer>(list);
            temp.remove(i);
            // System.out.println("temp: "+temp);
            permuteBT(result,comb,temp);
            comb.remove(comb.size()-1);    
        }
    }
}