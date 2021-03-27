/*
https://molchevskyi.medium.com/best-solutions-for-microsoft-interview-tasks-min-deletions-to-obtain-string-in-right-format-37a3365ce348

Let’s use Dynamic Programming approach: Assume the minimum deletions to format s[0..i] (index 0 to index i in string s) is computed and we keep numbers of deletions for range s[0..i] in variable int min_dels, then the minimum deletions to format s[0..i+1] is:
if(s[i+1] == ‘A’) {
There are two options: either to include this ‘A’ or exclude.
(1) If ‘A’ included then all B’s before it should be deleted by definition of the task where A must be always before ‘B’.
(2) If ‘A’ excluded then increment min_dels for range s[0..i] to one, that is add the ‘A’ which we going to exclude/delete to the number of deleted items
min_dels[0..i+1] = Math.min(num_Bs, min_dels[0..i]+1); // num_Bs is the total number of Bs in s[0..i]
}
else {
Since B is at the end there is no need to exclude this B
min_dels[0..i+1] = min_dels[0..i];
}
Here records like min_dels[0..i+1] mean not arrays but value of int min_dels calculated on range [0..i+1]
 */

 class MinDeletions {
    int minDeletions(String str) {
        int minDel = 0;
        int numB = 0;
        //“BAAABAB”
        for(char ch:str.toCharArray()) {
            if(ch == 'A') {
                minDel = Math.min(numB,minDel+1);
            } else {
                numB++;
            }
        }
        return minDel;
    }

    public static void main(String[] args) {
        MinDeletions sol = new MinDeletions();
        System.out.println("Should be 0: "+sol.minDeletions("AAABBB"));
        /* 
        Given S = “BAAABAB”, the function should return 2.
        We can obtain “AAABB” by deleting the first occurrence of ‘B’ 
        and the last occurrence of A’.
         */
        System.out.println("Should be 2: "+sol.minDeletions("BAAABAB"));
        /* 
        Given S = “BBABAA”, the function should return 3.
        We can delete all occurrences of A’ or all occurrences of ‘B’
        */
        System.out.println("Should be 3: "+sol.minDeletions("BBABAA"));
        System.out.println("Should be 0: "+sol.minDeletions("AAA"));
        System.out.println("Should be 0: "+sol.minDeletions("BBB"));
    }
 }