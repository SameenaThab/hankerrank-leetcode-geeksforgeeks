import java.util.*;


/* https://www.interviewbit.com/problems/maximal-string/ */
public class ThreeStackSingleArray_Chap3Prob1 {
    int[] arr;
    int top1;
    int limit1;
    int top2;
    int limit2;
    int top3;
    int limit3;
    public static void main(String[] args) {
        ThreeStackSingleArray_Chap3Prob1 sol = new ThreeStackSingleArray_Chap3Prob1();
        sol.arr = new int[300];
        sol.top1 = -1;
        sol.top2 = 99;
        sol.top3 = 199;
        sol.limit1 = 100;
        sol.limit2 = 200;
        sol.limit3 = 300;
        System.out.println("test empty");
        System.out.println(sol.isEmpty(1));
        System.out.println(sol.isEmpty(2));
        System.out.println(sol.isEmpty(3));
        System.out.println("test push");
        sol.push(9,1);
        sol.push(90,2);
        sol.push(900,3);
        System.out.println("test empty");
        System.out.println(sol.isEmpty(1));
        System.out.println(sol.isEmpty(2));
        System.out.println(sol.isEmpty(3));
        System.out.println("test peek");
        System.out.println(sol.peek(1));
        System.out.println(sol.peek(2));
        System.out.println(sol.peek(3));
        System.out.println("test pop");
        System.out.println(sol.pop(1));
        System.out.println(sol.pop(2));
        System.out.println(sol.pop(3));
        System.out.println("test empty");
        System.out.println(sol.isEmpty(1));
        System.out.println(sol.isEmpty(2));
        System.out.println(sol.isEmpty(3));
        for(int i=0;i<=100;i++) {
            sol.push(i,1);
            sol.push(i,2);
            sol.push(i,3);           
        }

    }

    public void push(int value,int stack) {
        if(stack == 1) {
            if(top1+1 >=limit1) {
                System.out.println("Stack "+stack+" is full");
            }
            else {
                arr[++top1] = value;
            }
        }
        else if(stack == 2) {
            if(top2+1 >=limit2) {
                System.out.println("Stack "+stack+" is full");
            }
            else {
                arr[++top2] = value;
            }
        }
        else {
            if(top3+1 >=limit3) {
                System.out.println("Stack "+stack+" is full");
            }
            else {
                arr[++top3] = value;
            }
        }
    }

    public int pop(int stack) {
        if(stack == 1) {
            if(top1 == -1) {
                System.out.println("Stack "+stack+" is empty");
                return -1;
            }
            else {
                int value = arr[top1];
                top1--;
                return value;
            }
        }
        else if(stack == 2) {
            if(top2 == -1) {
                System.out.println("Stack "+stack+" is empty");
                return -1;
            }
            else {
                int value = arr[top2];
                top2--;
                return value;
            }
        }
        else {
            if(top3 == -1) {
                System.out.println("Stack "+stack+" is empty");
                return -1;
            }
            else {
                int value = arr[top3];
                top3--;
                return value;
            }
        }
    }


    public int peek(int stack) {
        if(stack == 1) {
            if(top1 == -1) {
                System.out.println("Stack "+stack+" is empty");
                return -1;
            }
            else {
                return arr[top1];
            }
        }
        else if(stack == 2) {
            if(top2 == -1) {
                System.out.println("Stack "+stack+" is empty");
                return -1;
            }
            else {
                return arr[top2];
            }
        }
        else {
            if(top3 == -1) {
                System.out.println("Stack "+stack+" is empty");
                return -1;
            }
            else {
                return arr[top3];
            }
        }
    }

    public boolean isEmpty(int stack) {
        if(stack == 1) {
            return top1==-1;
        }
        else if(stack == 2) {
            return top2==99;
        }
        else {
            return top3==199;
        }        
    }
}