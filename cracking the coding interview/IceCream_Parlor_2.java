/*
Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool together money dollars for ice cream. 
On any given day, the parlor offers a line of n flavors. 
Each flavor,i , is numbered sequentially with a unique ID number from 1 to n and has a cost, costi, associated with it.
Given the value of money and the cost of each flavor for t trips to the Ice Cream Parlor, 
help Sunny and Johnny choose two distinct flavors such that they spend their entire pool of money during each visit. 
For each trip to the parlor, print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as two space-separated integers on a new line. 
You must print the smaller ID first and the larger ID second.
*/
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class IceCream_Parlor_2 {

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
        LinkedHashMap<Integer,Integer> hm = new LinkedHashMap<Integer,Integer>();
        for(int i=0;i<cost.length;i++)
        {
            if(hm.containsKey(money-cost[i]))
               {System.out.println(hm.get(money-cost[i])+" "+(i+1)); return;}
            else
               hm.put(cost[i],i+1);
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}
