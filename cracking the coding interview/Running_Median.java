/*
https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem

The median of a dataset of integers is the midpoint value of the dataset for which an equal number of integers are less than and greater than the value. To find the median, you must first sort your dataset of integers in non-decreasing order, then:

If your dataset contains an odd number of elements, the median is the middle element of the sorted sample. In the sorted dataset {1,2,3} ,2 is the median.
If your dataset contains an even number of elements, the median is the average of the two middle elements of the sorted sample. In the sorted dataset {1,2,3,4}, 2.5 is the median.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Running_Median {
    
    private static final Scanner scanner = new Scanner(System.in);

    private static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(100000);

    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(100000,new Comparator<Integer>() {
        public int compare(Integer a,Integer b) {
            return b-a;
        }
    });

    public static void addItem(int num) {
        //First and all small number in maxHeap
        if(maxHeap.size() == 0 || num<maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            //all Larger number in minHeap
            minHeap.add(num);
        }

    }

    //Goal is to maintain smaller numbers in maxHeap so that top of maxHeap is largest small number
    //  and all other larger number in minHeap so that top of minHeap is smallest large number.
    // together both tops form median
    public static void rebalance() {
        PriorityQueue<Integer> bigHeap;
        PriorityQueue<Integer> smallHeap;
        if(maxHeap.size() < minHeap.size()) {
            smallHeap = maxHeap;
            bigHeap = minHeap;
        } else {
            smallHeap = minHeap;
            bigHeap = maxHeap;
        } 
        while(bigHeap.size()-smallHeap.size()>=2) {
            smallHeap.add(bigHeap.poll());
        }
    }

    public static double  median() {
        PriorityQueue<Integer> bigHeap;
        PriorityQueue<Integer> smallHeap;
        if(maxHeap.size() < minHeap.size()) {
            smallHeap = maxHeap;
            bigHeap = minHeap;
        } else {
            smallHeap = minHeap;
            bigHeap = maxHeap;
        } 
        if(smallHeap.size() == bigHeap.size()) {
            return (double)(smallHeap.peek()+bigHeap.peek())/2.0;
        } else {
            return (double)bigHeap.peek();
        }
    }


    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            int aItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            a[i] = aItem;
            addItem(a[i]);
            rebalance();
            System.out.println(median());        
        }
        scanner.close();
    }
}
