import java.util.*;

public class SmallestMultipleZerosOnes {

    /* Visualize problem as tree 
                    1
            10              11
        100     101     110     111
    Each node is a binary. Use BFS to travel each node
    For a node if value%n = 0 , then the node becomes result.

    Each state represent the reminder of binary value
    eg: for n =3
    1 -> rem = 1
        10 -> rem = 1  (Not added to the queue as rem/state 1 already exists for value = 1)
            Since "1 already represents rem.state 1" we will not add 10 to the queue
            Because if I have already visited a state, why would I visit it again? 
            It already stores the smallest string which achieves that state 
            And if I visit it again with a new string it will surely have more characters than already stored string.
        11 -> rem = 2
            110 -> rem = 2 -> not added as state already exists for 11 
            111 -> rem = 0 -> "Result"
    eg: for n = 55
    1 -> rem = 1
        10 -> rem = 10
            100 -> rem = 45
            101 -> rem = 46
        11 -> rem = 11
            110 -> rem = 0 -> result
    Therefor No. of states will be n. And Time Complexity is O(n)
    Since all numbers are 0 and 1, to get to next number,
        we multiple previous state/rem with 10 to go to append 0 and 
        we multiple previous state/rem with 10 and add 1 to go to append 1
        Number = Quotient*Divisor+reminder
        Nextnumbers/branches = {(Quotient*Divisor+reminder)*10 , (Quotient*Divisor+reminder)*10+1}
        As we are dealing with smallest multiple and reminder's range -> [0,n] All the quotients will be 0's
        therefore nextnumbers/branches = {reminder*10 , reminder*10+1} Imp*: for repeated states this is not the case bcoz quotient will not be 0.
    */

    public static void main(String[] args) {
        SmallestMultipleZerosOnes sol = new SmallestMultipleZerosOnes();
        System.out.println("Solution: "+sol.multiple(3));
    }
    
    class Node {
        int rem;
        Node prev;
        boolean appendOne;
        Node(int rem,Node prev,boolean appendOne) {
            this.rem = rem;
            this.prev = prev;
            this.appendOne = appendOne;
        }
    }

    public String multiple(int n) {
        Queue<Node> q = new LinkedList<Node>();
        boolean[] visited = new boolean[n];
        q.add(new Node(1%n,null,true)); // true because last digit is one
        visited[1%n] = true;
        Node resultNode = null;
        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(curr.rem == 0) {
                resultNode = curr;
                break;
            }
            int child1Rem = (curr.rem*10)%n;
            int child2Rem = (curr.rem*10+1)%n;
            if(!visited[child1Rem])
                visited[child1Rem] = true;
                q.add(new Node(child1Rem,curr,false));
            if(!visited[child2Rem]) {
                visited[child2Rem] = true;
                q.add(new Node(child2Rem,curr,true));
            }
        }
        StringBuilder sb = new StringBuilder("");
        while(resultNode != null) {
            if(resultNode.appendOne)
                sb.append("1");
            else
                sb.append("0");
            resultNode = resultNode.prev;
        }
        return sb.reverse().toString();
    }    

    /* Not optimal */
    // public static int mod(String t, int N) {
    //     int r = 0;
    //     for(int i = 0; i < t.length(); i++)
    //     {
    //         r = r * 10 + (t.charAt(i) - '0');
    //         r %= N;
    //     }
    //     return r;
    // }
    
    // public String multiple(int n) {
    //     Queue<String> q = new LinkedList<String>();
    //     Set<String> visitedSet = new HashSet<String>();
    //     q.add("1");
    //     visitedSet.add("1");
    //     while(!q.isEmpty()) {
    //         String curr = q.poll();
    //         int rem = mod(curr,n);
    //         if(rem == 0)
    //             return curr;
    //         if(!visitedSet.contains(curr+"1")) {
    //             visitedSet.add(curr+"1");
    //             q.add(curr+"1");
    //         }
    //         if(!visitedSet.contains(curr+"0")) {
    //             visitedSet.add(curr+"0");
    //             q.add(curr+"0");
    //         }
    //     }
    //     return "";
    // }
}