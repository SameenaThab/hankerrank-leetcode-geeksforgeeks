import java.io.File;
import java.util.Arrays;
 class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{34,56,2,9,0,5,3,13,54};
        // int[] arr = new int[]{56,34,9,2};
        int n = arr.length;
        int[] temp = Arrays.copyOf(arr,n);
        mergeSort(arr,0,n-1,temp);
        System.out.println();
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
    }


    // Arrays.copyOf only makes a copy of array does not change the actual array
    public static void mergeSort(int[] arr,int l,int r,int[] temp) {
        if(l>=r)
            return;
        int mid = (l+r)/2;
        // arr = mergeSort(arr,l,mid,temp);
        // arr = mergeSort(arr,mid+1,r,temp);
        mergeSort(arr,l,mid,temp);
        mergeSort(arr,mid+1,r,temp);
        int lp = l;
        int rp = mid+1;
        int lEnd = mid;
        int rEnd = r;
        int index = l;
        // System.out.println("recursion lp:"+lp+" "+" rp: "+rp+" lEnd: "+lEnd+" rEnd: "+rEnd);
        // System.out.println("recursion l:"+l+" "+" mid: "+mid+" r: "+r);
        // System.out.println("temp: ");
        // for(int i=0;i<temp.length;i++) {
        //     System.out.print(temp[i]+" ");
        // }
        // System.out.println();
        // for(int i=0;i<arr.length;i++) {
        //     System.out.print(arr[i]+" ");
        // }
        // System.out.println();
        while(lp <= lEnd && rp <= rEnd) {
            if(arr[lp] <= arr[rp]) {
                temp[index]=arr[lp];
                lp++;
            }
            else {
                temp[index] = arr[rp];
                rp++;
            }
            index++;
        }
        while(lp<=lEnd) {
            temp[index++]=arr[lp++];
        }
        while(rp<=rEnd) {
            temp[index++]=arr[rp++];
        }
        for(int i=0;i<temp.length;i++)
            arr[i]=temp[i];
        // arr=Arrays.copyOf(temp,temp.length);
        // System.out.println("recursion l:"+l+" "+" mid: "+mid+" r: "+r);
        // System.out.println("temp: ");
        // for(int i=0;i<temp.length;i++) {
        //     System.out.print(temp[i]+" ");
        // }
        // System.out.println();
        // for(int i=0;i<arr.length;i++) {
        //     System.out.print(arr[i]+" ");
        // }
        // System.out.println();
        // return arr;
        
    }
 
}