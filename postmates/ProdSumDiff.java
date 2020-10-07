import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ProdSumDiff {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter num");     
        int num = scanner.nextInt();
        System.out.println(prodSumDiff(num));
   }

    public static int prodSumDiff(int num) {
        int prod = 1;
        int sum = 0;
        int temp = num;
        if(num == 0)
            return 0;
        while(temp != 0) {
            int digit = temp%10;
            temp = temp/10;
            prod = prod*digit;
            sum = sum+digit;
        }
        return prod-sum;
    }

}