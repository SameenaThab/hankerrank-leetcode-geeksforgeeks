import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class SnakeLadder {

    /* https://www.interviewbit.com/problems/snake-ladder-problem/ */

    Map<Integer,Integer> snakesAndLadders;

    /* 
     A = [  [32, 62]
        [42, 68]
        [12, 98]
     ]
 B = [  [95, 13]
        [97, 25]
        [93, 37]
        [79, 27]
        [75, 19]
        [49, 47]
        [67, 17]

         A = [  [8, 52]
        [6, 80]
        [26, 42]
        [2, 72]
     ]
 B = [  [51, 19]
        [39, 11]
        [37, 29]
        [81, 3]
        [59, 5]
        [79, 23]
        [53, 7]
        [43, 33]
        [77, 21] 
    */
    
     public static void main(String[] args) {

        SnakeLadder sol = new SnakeLadder();

        ArrayList<ArrayList<Integer>> ladders = new ArrayList<ArrayList<Integer>>();

        ladders.add(new ArrayList<Integer>(Arrays.asList(8, 52)));
        ladders.add(new ArrayList<Integer>(Arrays.asList(6, 80)));
        ladders.add(new ArrayList<Integer>(Arrays.asList(26, 42)));
        ladders.add(new ArrayList<Integer>(Arrays.asList(2, 72)));

        ArrayList<ArrayList<Integer>> snakes = new ArrayList<ArrayList<Integer>>();

        snakes.add(new ArrayList<Integer>(Arrays.asList(51, 19)));
        snakes.add(new ArrayList<Integer>(Arrays.asList(39, 11)));
        snakes.add(new ArrayList<Integer>(Arrays.asList(37, 29)));
        snakes.add(new ArrayList<Integer>(Arrays.asList(81, 3)));
        snakes.add(new ArrayList<Integer>(Arrays.asList(59, 5)));
        snakes.add(new ArrayList<Integer>(Arrays.asList(79, 23)));
        snakes.add(new ArrayList<Integer>(Arrays.asList(53, 7))); 
        snakes.add(new ArrayList<Integer>(Arrays.asList(43, 33)));
        snakes.add(new ArrayList<Integer>(Arrays.asList(77, 21)));         
        System.out.println("Solution: "+sol.snakeLadder(ladders,snakes));

    }

    public int snakeLadder(ArrayList<ArrayList<Integer>> ladders, ArrayList<ArrayList<Integer>> snakes) {
        
        this.snakesAndLadders = new HashMap<Integer,Integer>();
        
        for(ArrayList<Integer> ladder:ladders) {
            Integer base = ladder.get(0);
            Integer top  = ladder.get(1);
            snakesAndLadders.put(base,top);
        }

        for(ArrayList<Integer> snake:snakes) {
            Integer mouth = snake.get(0);
            Integer tail  = snake.get(1);
            snakesAndLadders.put(mouth,tail);
        }

        System.out.println("snakes adn ladderS: "+ snakesAndLadders);
        return bfs();
        // Set<Integer> visited = new HashSet<Integer>();
        // return dfs(1,visited);
    }

    int bfs() {
        Queue<Integer> queue = new LinkedList<Integer>();
        Map<Integer,Integer> dist = new HashMap<Integer,Integer>();
        queue.add(1);
        dist.put(1,0);
        while(!queue.isEmpty()) {
            int sq = queue.poll();
            // System.out.println("sq: "+(sq)+" dist: "+dist.get(sq));
            if(sq == 100)
                return dist.get(sq);
            for(int i=1;i<=6;i++) {
                int next = snakesAndLadders.containsKey(sq+i)?snakesAndLadders.get(sq+i):sq+i;
                if(next<=100 && !dist.containsKey(next)) {
                    dist.put(next,dist.get(sq)+1);
                    queue.add(next);
                }
            }
        }
        return -1;
    }

/* 
DFS will not work becoz
for 1 -> we mark 2 as visited , 2 marks 3, 3 marks 4 and so on
So when 2 cannot go to 6 
even backtracking results in infited loop .
uncoment and check
*/
    // int dfs(int sq,Set<Integer> visited) {
        
    //     System.out.println("sq: "+sq);
    //     // visited.add(sq);
    //     if(sq == 100) {
    //         return 0;
    //     }
    //     int path = 0;
    //     if(ladderSet.containsKey(sq)) {
    //         path = dfs(ladderSet.get(sq),visited);
    //     } else {
    //         int sub = 100;
    //         for(int i=1;i<=6;i++) {
    //             System.out.println("sq+i: "+(sq+i));
    //             int next = snakesAndLadders.containsKey(sq+i)?snakesAndLadders.get(sq+i):sq+i;
    //             if(next<=100 && !visited.contains(next)) {
    //                 visited.add(next);
    //                 int dist = dfs(next,visited);
    //                 System.out.println("next: "+next+" dist: "+dist);
    //                 sub = Math.min(sub,dfs(next,visited));
    //                 // visited.remove(next);
    //             }
    //         }
    //         path = sub+1;
    //     }
    //     return path;
    // }

}