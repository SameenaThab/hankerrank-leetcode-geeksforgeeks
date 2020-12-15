import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class  TowersOfHanoii_Chap8Prob6 {

    public static void main(String[] args) {
        TowersOfHanoii_Chap8Prob6 sol = new TowersOfHanoii_Chap8Prob6();
        Stack<Integer> origin = new Stack<Integer>();
        Stack<Integer> destination = new Stack<Integer>();
        Stack<Integer> buffer = new Stack<Integer>();
        for(int i=5;i>0;i--)
            origin.push(i);

        System.out.println(origin.peek());
        System.out.println("Origin is full before: "+Arrays.toString(origin.toArray()));
        sol.towersOfHanoii(5,origin,destination,buffer);
        System.out.println("Must be empty: "+Arrays.toString(origin.toArray()));
        System.out.println("Must be full: "+Arrays.toString(destination.toArray()));
        System.out.println(destination.peek());
    }

    void towersOfHanoii(int n,Stack<Integer> origin,Stack<Integer> destination,Stack<Integer> buffer) {
        if(n <= 0)
            return;
        towersOfHanoii(n-1,origin,buffer,destination);
        destination.push(origin.pop());
        towersOfHanoii(n-1,buffer, destination, origin);
    }

}
