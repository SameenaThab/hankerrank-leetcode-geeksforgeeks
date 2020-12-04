import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class  RecursiveMultiply_Chap8Prob5 {

    public static void main(String[] args) {
        RecursiveMultiply_Chap8Prob5 sol = new RecursiveMultiply_Chap8Prob5();

        System.out.println(sol.multiply(9,10));

        System.out.println(sol.product(30,35));
    }

    // stupid solution
    public int multiply(int x,int y) {
        if(y==0)
            return 0;
        else {
            return x+multiply(x, y-1);
        }
    }

    // divide the smaller number by 2 and add the results
    // product(30,35) = 2*product(15,35)
    // if smaller number is odd then
    // product(31,35) = product(15,35)+product(16,35) = 2*product(15,35)+35

    public int product(int x,int y) {
        int smaller = x<y?x:y;
        int bigger = x>y?x:y;
        int[] mem = new int[smaller+1];
        return recursive(smaller,bigger,mem);
    }

    public int recursive(int smaller,int bigger,int[] mem) {
        if(smaller == 0)
            return 1;
        if(smaller == 1)
            return bigger;
        if(mem[smaller] != 0)
            return mem[smaller];
        int half = smaller>>1;
        int halfProduct = recursive(half,bigger,mem);
        mem[half] = halfProduct;
        if(smaller%2 == 0)
            return 2*halfProduct;
        else    
            return 2*halfProduct+bigger;
    }

}
