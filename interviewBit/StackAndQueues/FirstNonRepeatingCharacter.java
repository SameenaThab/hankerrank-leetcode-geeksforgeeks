import java.util.*;

public class FirstNonRepeatingCharacter {

    public static void main(String[] args) {
        FirstNonRepeatingCharacter sol = new FirstNonRepeatingCharacter();
        // String A = "abadbc";
        String A = "abcabc";
        System.out.println("Solution: "+sol.solve(A));
    }

    public String solve(String A) {
        Queue<Character> queue = new LinkedList<Character>();
        int[] frequencies = new int[26];
        StringBuilder result = new StringBuilder();
        for(char ch : A.toCharArray()) {
            frequencies[ch-'a']++;
            queue.add(ch);
            while(!queue.isEmpty() && frequencies[queue.peek()-'a'] > 1)
                queue.poll();
            result.append(queue.isEmpty()? '#' : queue.peek());
        }
        return result.toString();
    }
}