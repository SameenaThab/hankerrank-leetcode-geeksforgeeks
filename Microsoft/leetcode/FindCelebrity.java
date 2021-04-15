/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
//https://leetcode.com/problems/find-the-celebrity/
import java.util.*;
/* 
On the graph representation, a celebrity is a person who has exactly n - 1 directed edges going in (everybody knows them) and 0 edges going out (they know nobody).
*/

public class FindCelebrity extends Relation {
    public int findCelebritynotbetter(int n) {
      for(int i=0;i<n;i++) {
            if(isCeleb(i,n))
                return i;
      }
        return -1;
    }
    

    // Time: O(n^2)
    // Space = O(1)
    public boolean isCeleb(int i,int n) {
       for(int j=0;j<n;j++) {
          if(i != j) {
              if(knows(i,j) || !knows(j,i)) {
                  // bcoz to be celeb !knows(i,j) && knows(j,i)
                  return false;
              }
          }
        }
        return true;
    }



    /* 
    A knows B = true => A cant be a celeb, so B becomes celebrity Candidate
    A knows B = false => B cant be a celeb, so B cannot be celeb candidate
    Time : O(n)
    Space: O(1)
    */
    public int findCelebritybetter(int n) { 
        int celebCandidate = 0;
        for(int i=0;i<n;i++) {
            if(i!=celebCandidate && knows(celebCandidate,i)) {
                    celebCandidate = i;
            }
        }
        if(isCelebrity(celebCandidate,n)) {
            return celebCandidate;
        }
        return -1;
    }
    
    private boolean isCelebrity(int i,int n) {
        for (int j = 0; j < n; j++) {
            if (i == j) continue; // Don't ask if they know themselves.
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;        
    }

    /* 
    if knows is a expensive operation, we introduce memeorization
    Time: O(n);
    Space:(n);
    */

    private int numberOfPeople;
    private Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>(); 
    
    @Override
    public boolean knows(int a, int b) {
        if (!cache.containsKey(new Pair(a, b))) {
            cache.put(new Pair(a, b), super.knows(a, b));
        }
        return cache.get(new Pair(a, b));
    }
    
    public int findCelebrity(int n) {
        numberOfPeople = n;
        int celebrityCandidate = 0;
        for (int i = 0; i < n; i++) {
            if (knows(celebrityCandidate, i)) {
                celebrityCandidate = i;
            }
        }
        if (isCelebrity(celebrityCandidate)) {
            return celebrityCandidate;
        }
        return -1;
    }
    
    private boolean isCelebrity(int i) {
        for (int j = 0; j < numberOfPeople; j++) {
            if (i == j) continue; // Don't ask if they know themselves.
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
}