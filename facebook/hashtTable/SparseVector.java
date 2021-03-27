import java.util.*;

/* 

A sparse vector is a vector that has mostly zero values, while a dense vector is a vector where most of the elements are non-zero.
It is inefficient to store a sparse vector as a one-dimensional array (Approach 1).
Instead, we can store the non-zero values and their corresponding indices in a dictionary, with the index being the key (Approach 2).
Alternatively, we can represent elements of a sparse vector as an array of pairs for each non-zero value. Each pair has an integer index and a numerical value (Approach 3).
App2 and App3 work for follow up question, when one vector is smaller in length
*/
class SparseVector {
    // non efficient approach
    class SparseVectorApp1 {
    
        public int[] vector;
        SparseVectorApp1(int[] nums) {
            vector = nums;
        }
        
        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVectorApp1 vec) {
            int sum=0;
            for(int i=0;i<vector.length;i++) {
                sum+=vector[i]*vec.vector[i];
            }
            return sum;
        }
    }

    class SparseVectorApp2 {
    
        /* 
        Note: 
        Leetcode comment: interviewer did not accept my hashmap solution. He told me that hashing/lookups, while on surface look efficient, for large sparse vectors, hashing function takes up bulk of the computation that we might be better off with the first method. Wihile proposing hashmap/set solutions, take a minute to think about the complexity hashing function.
        L= #(non-zero vlaues)
        Time complexity: O(n) for creating the Hash Map; O(L) for calculating the dot product.
        Space complexity: O(L) for creating the Hash Map, as we only store elements that are non-zero. O(1) for calculating the dot product.
        */
        public Map<Integer,Integer> map;
        SparseVectorApp2(int[] nums) {
            map = new HashMap<Integer,Integer>();
            for(int i=0;i<nums.length;i++) {
                if(nums[i] != 0)
                    map.put(i,nums[i]);
            }
        }
        
        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVectorApp2 vec) {
            int sum=0;
            for(int index:map.keySet()) {
                if(vec.map.containsKey(index)) {
                    sum+=map.get(index)*vec.map.get(index);
                }
            }
            return sum;
        }
    }



    class SparseVectorApp3 {
    
        /* 
        L= #(non-zero vlaues)
        Time complexity: O(n) for creating the <index, value> pair for non-zero values; O(L) for calculating the dot product.
        Space complexity: O(L) for creating the <index, value> pairs for non-zero values. O(1) for calculating the dot product.*/
        public List<int[]> pairs;
        SparseVectorApp3(int[] nums) {
            pairs = new ArrayList<int[]>();
            for(int i=0;i<nums.length;i++) {
                if(nums[i] != 0) {
                    pairs.add(new int[]{i,nums[i]});
                }
            }
        }
        
        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVectorApp3 vec) {
            int sum=0;
            int idx1=0,idx2=0;
            while(idx1 < pairs.size() && idx2 < vec.pairs.size()) {
                if(pairs.get(idx1)[0] == vec.pairs.get(idx2)[0]) {
                    sum+=pairs.get(idx1)[1]*vec.pairs.get(idx2)[1];
                    idx1++;
                    idx2++;
                }
                else if(pairs.get(idx1)[0]>vec.pairs.get(idx2)[0]) {
                    idx2++;
                }
                else {
                    idx1++;
                }
            }
            return sum;
        }

    }

    public void mainTest() {
        SparseVectorApp1 v1 = new SparseVectorApp1(new int[]{1,0,0,2,3});
        SparseVectorApp1 v2 = new SparseVectorApp1(new int[]{0,3,0,4,0});
        System.out.println(v1.dotProduct(v2));  
        
        SparseVectorApp2 v3 = new SparseVectorApp2(new int[]{1,0,0,2,3});
        SparseVectorApp2 v4 = new SparseVectorApp2(new int[]{0,3,0,4,0});
        System.out.println(v3.dotProduct(v4));      

        SparseVectorApp1 v5 = new SparseVectorApp1(new int[]{1,0,0,2,3});
        SparseVectorApp1 v6 = new SparseVectorApp1(new int[]{0,3,0,4,0});
        System.out.println(v5.dotProduct(v6));       
    }

    public static void main(String[] args) {
        SparseVector sol = new SparseVector();
        sol.mainTest();
    }
    // Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
}


