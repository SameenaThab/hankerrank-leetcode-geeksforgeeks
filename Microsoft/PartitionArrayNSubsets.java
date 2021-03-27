import java.util.*;

class PartitionArrayNSubsets {
    
    /* 
    Initialize sum array of length n
    Initialize result List of length n
    WE initialize a PriorityQueue(min HEap) with comparator with compares via sum values
    Add 0 to n-1 in minHeap
    Since all values in sum array is 0,all values are same in minHeap
    Input: arr = [1, 2, 3, 4, 5], N = 3
    Explantation:  sum = [0,0,0] pq = [0,1,2]
    traverse array from largest to smallest( we know array is sorted)
    i = 4 for arr
    i=4, pq.poll() -> returns 0 sum=[5,0,0] pq-> [1,2,0](bcoz now sum[0] is largest , therfore is at the end of minHeap),i.e., largest
    i=3, pq.poll() -> returns 1 sum=[5,4,0] pq->[2,1,0] (sum[1]<sum[0])
    i=2, pq.poll() -> returns 2 sum=[5,4,3] pq->[2,1,0] (sum[2]<sum[1]<sum[0])
    i=1, pq.poll() -> returns 2 sum=[5,4,3+2] pq->[1,2,0] (sum[2]<sum[1]=sum[0])
    i=0, pq.poll() -> returns 1 sum =[5,4+1,3+2] pq->[1,2,0] (sum[2]=sum[1]=sum[0])
    */
    public static List<List<Integer>> part(int[] T, int n) {
       // WRITE YOUR BRILLIANT CODE HERE
      int[] sums = new int[n];
      PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
          public int compare(Integer a, Integer b) {
              return sums[a.intValue()] - sums[b.intValue()];
          }
      });
      List<List<Integer>> result = new ArrayList<>();
      for (int i = 0; i < n; i++) {
          result.add(new ArrayList<>());
          pq.add(i);
      }
      for (int i = T.length - 1; i >= 0; i--) {
          int c = pq.poll();
          System.out.println("c: "+c);
          result.get(c).add(T[i]);
          sums[c] += T[i];
          pq.add(c);
      }
      return result;
    }

    public static void main(String[] args) {
        System.out.println(PartitionArrayNSubsets.part(new int[]{1,2,3,4,5},3));
    }

}