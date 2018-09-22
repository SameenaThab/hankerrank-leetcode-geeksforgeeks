/*
https://www.hackerrank.com/challenges/ctci-fibonacci-numbers/problem
*/
import java.util.*;

public class Fibonacci {

    
    public static int fibonacci(int n) {
        return fibonacci(0,1,n,2) ;    
    }
    
    public static int fibonacci(int a,int b,int n,int i) {
        if(i==n)
            return a+b;
        else
            return fibonacci(b,a+b,n,i+1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
