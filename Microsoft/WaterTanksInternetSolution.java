import java.util.*;

public class WaterTanksInternetSolution {
    
    public static int minWaterTanks(String s){
            
        int[] cache = new int[s.length()];
        Arrays.fill(cache, -2);
        
        dfs(0,s,cache);
        
        return cache[0];
    }
    
    // dfs tries to check placement from currIdx to the
    // end of the street, where currIdx is always unsafe
    // it gives minimum number of cameras required for currIdx to end of street
    public static int dfs(int currIdx, String s, int[] cache){
                
        // no tanks required after street ends
        if(currIdx>= s.length()){
            return 0;
        }
        
        if(cache[currIdx] != -2){
            return cache[currIdx];
        }
        
        char ch = s.charAt(currIdx);
        
        // If both of our possible recursive branches return -1 (impossible)
        // the current call is also impossible (-1)
        
        if(ch == '-'){
            // place a tank here
            // so next place will always be safe
            int placeTank = dfs(currIdx+2, s, cache);
    
            // possible from next pos
            if(placeTank != -1)
                placeTank += 1; // 1 tank is placed
    
            
            // don't place a tank, so check
            // from next place
            int dontPlaceTank = dfs(currIdx+1, s, cache);
            
            if(placeTank == -1 && dontPlaceTank == -1)
                cache[currIdx] = -1;
            else if(placeTank == -1)
                cache[currIdx] = dontPlaceTank;
            else if(dontPlaceTank == -1)
                cache[currIdx] = placeTank;
            else
                cache[currIdx] = Math.min(placeTank, dontPlaceTank);
            
            
        }
        else{
            
            // if we are at a house, the previous place must have been empty
            // we have to try to place a tank at the next index.
            // But if next place is not '-', no possible,therefore -1
            if(currIdx+1>=s.length() || s.charAt(currIdx+1) != '-'){
                cache[currIdx] = -1;
            }
            else{ // we have HT? situation where we are at house, next is tank and next to next
                  // is something we dont know yet, but its definitely safe, so we can skip checking from it
                int placeTankAtNext = dfs(currIdx+3, s, cache);
                
                if(placeTankAtNext != -1) // if it was possible from the branch
                    placeTankAtNext += 1;
                
                cache[currIdx] = placeTankAtNext;
            }
        }
        
        return cache[currIdx];
        
    }
    
    public static void main(String[] args) {
        
        System.out.println(minWaterTanks("-H-HH--"));
        System.out.println(minWaterTanks("H"));
        System.out.println(minWaterTanks("HH-HH"));
        System.out.println(minWaterTanks("-H-H-H-H-H"));
        System.out.println(minWaterTanks("-"));
    }
}