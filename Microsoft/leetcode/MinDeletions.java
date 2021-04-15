import java.util.*;
class MinDeletions {
    //https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/submissions/
    //https://molchevskyi.medium.com/best-solutions-for-microsoft-interview-tasks-min-deletions-to-make-frequency-of-each-letter-unique-16adb3ec694e
    public int minDeletions(String s) {
        int[] freq = new int[26];
        for(char ch :s.toCharArray()){
            freq[ch-'a']++;
        }
        // most freq on the top
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i=0;i<26;i++){
            if(freq[i]>0) {
                pq.add(freq[i]);
            }
        }
        int count = 0;
        while(!pq.isEmpty()) {
            int curr = pq.poll();
            // if there is a duplicate frequency it will be next as pq is sorted
            if(!pq.isEmpty()) {
                if(pq.peek() == curr) {
                    // increment deletions as duplicate
                    count++;
                    // if there is a duplicate frequency just decrease the freq by 1 and add it back. if there is a duplicate it will be dealt later
                    if(curr > 1) {
                        // bcoz 1-1 will 0 and we dont want one in queue
                        pq.add(curr-1);
                    }
                }
            }
        }
        return count;
    }
}