import java.util.*;

public class GrayCodeSeq {

    /* https://www.interviewbit.com/problems/gray-code/ */

    public static void main(String[] args) {
        GrayCodeSeq sol = new GrayCodeSeq();

        System.out.println("Solution: "+sol.grayCode(3));
        System.out.println("Solution: "+sol.grayCodeOptimal(3));
    }

    public ArrayList<Integer> grayCode(int a) {
        int[] arr = new int[a];
        Set<Integer> visited = new HashSet<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        backTrack(arr,result,visited);
        return result;
    }

    /* reverse of  Gray code seq is also gray code seq
    Note the following :

    Let G(n) represent a gray code of n bits.
    Note that reverse of G(n) is also a valid sequence.
    Let R(n) denote the reverse of G(n).

    Note that we can construct
    G(n+1) as the following :
    0G(n)
    1R(n)

    Where 0G(n) means all elements of G(n) prefixed with 0 bit and 1R(n) means all elements of R(n) prefixed with 1.
    Note that last element of G(n) and first element of R(n) is same. So the above sequence is valid.

    Example G(2) to G(3) :
    0 00
    0 01
    0 11
    0 10
    1 10
    1 11
    1 01
    1 00
    */
    public ArrayList<Integer> grayCodeOptimal(int a) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(a == 0)
            return result;
        result.add(0);
        result.add(1);
        if(a == 1)
            return result;
        while(a-- > 1) {
            int n = result.size();
            for(int i=n-1;i>=0;i--) {
                result.add(n+result.get(i));
            }
        }
        return result;
    }

    public void backTrack(int[] arr,ArrayList<Integer> result,Set<Integer> visited) {
        // System.out.println("n: "+n);
        System.out.println(Arrays.toString(arr));
        int binValue = binary(arr);
        result.add(binValue);
        visited.add(binValue);
        for(int i=arr.length-1;i>=0;i--) {
            //flip bit
            arr[i] = arr[i] == 0? 1 : 0;
            binValue = binary(arr);
            if(!visited.contains(binValue))
            backTrack(arr, result,visited);
            //swap back
            arr[i] = arr[i] == 0? 1 : 0;
        }
    }

    public int binary(int[] arr) {
        int val = 0;
        int mul = 1;
        for(int i=arr.length-1;i>=0;i--) {
            val+=arr[i]*mul;
            mul*=2;
        }
        return val;
    }
}
