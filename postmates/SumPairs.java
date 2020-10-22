
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SumPairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[]{8,4,3,8,6};
        int[] b = new int[]{1,9,2,7,10};
        Map<Integer,Set<Integer>> map = new HashMap<Integer,Set<Integer>>();
        for(int i=0;i<a.length;i++) {
            Set<Integer> set = map.getOrDefault(a[i],new HashSet<Integer>());
            set.add(i);
            map.put(a[i],set);
        }
        System.out.println("Enter query");
        int q = scanner.nextInt();
        if(q == 0) {
            System.out.println("Enter i");
            int i = scanner.nextInt(); 
            System.out.println("Enter num");
            int num = scanner.nextInt();      
            b[i]=num;     
        }
        else {
            System.out.println("Enter sum");
            int sum = scanner.nextInt();  
            ArrayList<Integer[]> result = findSumPairs(map,b,sum);
            System.out.println("Array a");
            for(int num:a){
                System.out.print(num+" ");
            }
            System.out.println();
            System.out.println("Array b");
            for(int num:b){
                System.out.print(num+" ");
            }
            System.out.println();
            for(Integer[] arr:result) {
                System.out.println(arr[0]+" "+arr[1]);
            }
        }
   }

   public static ArrayList<Integer[]> findSumPairs(Map<Integer,Set<Integer>> map,int[] b, int sum) {
    ArrayList<Integer[]> pairs = new ArrayList<Integer[]>();
    for(int i=0;i<b.length;i++) {
        int rem=sum-b[i];
        System.out.println("rem: "+rem);
        if(map.containsKey(rem)) {
            for(Integer k:map.get(rem)) {
                Integer[] pair = new Integer[]{k,i};
                pairs.add(pair);
            }
        }
    }
    return pairs;
 }

}