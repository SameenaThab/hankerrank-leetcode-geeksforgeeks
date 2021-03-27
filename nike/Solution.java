import java.util.LinkedList;

public class Solution {
    /*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
    and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.
    Example Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807
    for 0 to l1.len
        l1.get(i)+l2.get(i)+cary
    */

    public static void main(String... args) {
        LinkedList<Integer> first = new LinkedList<>();
        first.add(3);
        first.add(8);
        first.add(6);
        first.add(2);
        first.add(4);
        LinkedList<Integer> second = new LinkedList<>();
        second.add(9);
        second.add(9);
        second.add(9);
        LinkedList<Integer> sum = getSum(first, second);
        System.out.print("reuslt: "+sum);
    }

    private static LinkedList<Integer> getSum(LinkedList<Integer> first, LinkedList<Integer> second) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        LinkedList<Integer> larger; 
        LinkedList<Integer> smaller; 
        if(first.size() >= second.size()) {
            larger = first;
            smaller = second;
        } else {
            larger = second;
            smaller = first;
        }
        int i=0;
        int cary = 0;
        for(i=0;i<smaller.size();i++) {
            int sum = cary +larger.get(i)+smaller.get(i);
            result.add(sum%10);
            cary = sum>10? 1:0;
        }
        while(i<larger.size()) {
            int sum = cary +larger.get(i);
            result.add(sum%10);
            cary = sum>10? 1:0;  
            i++;          
        }

        return result;
    }
}