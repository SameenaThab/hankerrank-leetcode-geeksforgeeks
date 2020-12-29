import java.util.*;

class Pair {
    int index;
    char value;
    Pair(int index,char value) {
        this.index = index;
        this.value = value;
    }
}

/* https://www.interviewbit.com/problems/maximal-string/ */
public class MaximalString {

    public static void main(String[] args) {
        MaximalString sol = new MaximalString();

        System.out.println("Solution: "+sol.solve("245", 2));
        System.out.println("Solution: "+sol.solve("2499", 2));

    }

    String max;
    void swap(char[] charArr,int i,int j) {
        char temp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = temp;
    }

    public String solve(String A, int B) {
        this.max=A;
        // generate(A.toCharArray(),B);
        //faster only max only are swapped
        generate(A.toCharArray(),B,0);
        return max;
    }

    void generate(char[] arr,int B) {
        if(B==0)
            return;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                swap(arr,i,j);
                // System.out.println("before swapped: "+String.valueOf(arr));
                // System.out.println("before max: "+max);
                max = String.valueOf(arr).compareTo(max) > 0? String.valueOf(arr) : max;
                // System.out.println("After swapped: "+String.valueOf(arr));
                // System.out.println("After max: "+max);
                generate(arr,B-1);
                swap(arr,j,i);
            }
        }
    }

    // With out backTracking this solution fails for duplicate numbers.
    /* 
    2499 B=2 is swapped to 9429 and 9924 -> but ans is 9924 
    */
    void generate(char[] arr,int B,int index) {
        if(B==0 || index==arr.length)
            return;
        char maxChar = arr[index];
        for(int i=index+1;i<arr.length;i++){
            if(maxChar<arr[i]) {
                maxChar=arr[i];
            }
        }
        // System.out.println("index: "+index+" maxIndex: "+maxIndex);
        if(maxChar != arr[index]) {
            B--;
        }
        for(int i=index+1;i<arr.length;i++) {
            if(maxChar == arr[i]) {
                swap(arr,index,i);
                max = String.valueOf(arr).compareTo(max) > 0? String.valueOf(arr) : max;
                generate(arr,B,index+1);
                swap(arr,i,index);
            }
        }
    }
    
}