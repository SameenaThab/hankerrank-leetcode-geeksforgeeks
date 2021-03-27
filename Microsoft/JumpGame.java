import java.util.*;

/* 
https://algo.monster/problems/jump_game
You are given an array of non-negative integers arr and a start index. When you are at an index i, you can move left or right by arr[i]. Your task is to figure out if you can reach value 0.

Example 1:
Input: arr = [3, 4, 2, 3, 0, 3, 1, 2, 1], start = 7
Output: true
Explanation:
left -> left -> right

Example 2:
Input: arr = [3, 2, 1, 3, 0, 3, 1, 2, 1], start = 2
Output: false
*/
class JumpGame {
    boolean jumpGameBFS(int[] arr, int start) {
        if(arr[start]==0)
            return true;
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] visited = new int[arr.length];
        queue.add(start);
        visited[start] = 1;
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if(arr[curr] == 0)
                return true;
            int right = curr+arr[curr];
            int left = curr-arr[curr];
            if(left>=0 && visited[left] == 0) {
                visited[left] = 1;
                queue.add(left);
            }
            if(right<arr.length && visited[right] == 0) {
                visited[right] = 1;
                queue.add(right);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JumpGame sol = new JumpGame();
        System.out.println("Should be true: "+sol.jumpGameBFS(new int[]{3, 4, 2, 3, 0, 3, 1, 2, 1}, 7));
        System.out.println("Should be false: "+sol.jumpGameBFS(new int[]{3, 2, 1, 3, 0, 3, 1, 2, 1}, 2));
    }
}