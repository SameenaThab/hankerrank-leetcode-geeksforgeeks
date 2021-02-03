import java.util.*;
/*
 https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/submissions/
 
 We use bitvector method to find if the string has unique characters check solution ../DuplicateCharacter_BitVector.java
 We initialize a list combinations with empty string
 we iterate thru all the the strings
 if the string is unique, we iterate thru combinations list
 for each combination, we concatenate currString and comb
 We check if concatenated string is unique,
 if unique we add it to list , for future strings to form combinations
 we update maxlength for all combinations
 we initialize empty string to combinations, so that curr string itself is counted
 eg: {un,iq,ue}
 curr = un -> combinations ={ "","un"}
 curr = iq -> combinations ={"","un","iq","uniq"}
if keep all the combinations bcoz, we never know future currstring can make unique max string with anyone of them
for eg: "abcdefgh"  itself is a unique string
 */
class ConcatUniqueCharacters {

    public static void main(String[] args) {
        List<String> arr = new ArrayList<String>(Arrays.asList("ab","cd","cde","cdef","efg","fgh","abxyz"));
        ConcatUniqueCharacters solution = new ConcatUniqueCharacters();
        System.out.println("Solution array A: "+solution.maxLength(arr));
        System.out.println("Solution array A: "+solution.maxLength2(arr));
    }

    public int maxLength(List<String> arr) {
        List<String> combinations = new ArrayList<String>();
        int max = 0;
        combinations.add("");
        for(int i=0;i<arr.size();i++) {
            String curr = arr.get(i);
            if(isUnique(curr)) { // if word itself hasnt unique characters, then it will not make unique concatenation
                int n = combinations.size();
                for(int j=0;j<n;j++){
                    String newComb = curr+combinations.get(j);
                    if(isUnique(newComb)) {
                        combinations.add(newComb);
                        max = Math.max(max,newComb.length());
                    }
                }
            }
        }
        return max;
    }

    public int maxLength2(List<String> arr) {
        List<BitSet> bitValues = new ArrayList<BitSet>();
        int max = 0; 
        bitValues.add(new BitSet(26));
        for(int i=0;i<arr.size();i++) {
            String curr = arr.get(i);
            BitSet currbits = bits(curr);
            if(currbits.cardinality() == curr.length()) { // if word itself hasnt unique characters, then it will not make unique concatenation
                int n = bitValues.size();
                for(int j=0;j<n;j++){
                    if(!currbits.intersects(bitValues.get(j))) {
                        BitSet temp = (BitSet)currbits.clone();
                        temp.or(bitValues.get(j));
                        // System.out.println("temp: "+temp);
                        bitValues.add(temp);
                        max = Math.max(max,temp.cardinality());
                    }
                }
            }
        }
        return max;
    }

    private BitSet bits(String curr) {
        BitSet bitset = new BitSet(26); 
        for(char ch:curr.toCharArray()) {
            int index = ch-'a';
            bitset.set(index);
        }
        return bitset;
    }

    private boolean isUnique(String curr) {
        int counter = 0;
        for(char ch:curr.toCharArray()) {
            int index = ch-'a';
            if((counter & (1<<index)) > 0)
                return false;
            counter |= (1<<index);
        }
        return true;
    }
}