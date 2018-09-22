import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*A prime is a natural number greater than  that has no positive divisors other than  and itself. Given  integers, determine the primality of each integer and print whether it is Prime or Not prime on a new line.*/

public class Primality {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++){
            int n = in.nextInt();
            if(prime(n))
                System.out.println("Prime");
            else
                System.out.println("Not prime");
        }
    }
    
    public static boolean prime(int num)
    {
        if(num==0||num==1)
            return false;
        int n=(int)Math.sqrt((double)num);
        for(int i=2;i<=n;i++)
        {
            if(num%i==0)
                return false;
        }
        return true;
    }
}
