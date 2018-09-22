/*
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
    
    public static double[] getMedians(int[] a)
    {
        PriorityQueue<Integer> minheap=new PriorityQueue<Integer>(1000,new Comparator<Integer>()
                                                {
                                                    public int compare(Integer a,Integer b)
                                                    {
                                                        return b-a;
                                                    }
                                                });
        PriorityQueue<Integer> maxheap=new PriorityQueue<Integer>();
        double[] medians=new double[a.length];
        for(int i=0;i<a.length;i++)
        {
            addNumber(a[i],minheap,maxheap);
            rebalance(minheap,maxheap);
            medians[i]=findmedian(minheap,maxheap);
        }
      return medians;
    }
    
    public static void addNumber(int i,PriorityQueue<Integer> minheap,PriorityQueue<Integer> maxheap)
    {
            if(minheap.size()==0 || i<minheap.peek())
                minheap.add(i);
            else
                maxheap.add(i);
    }
    
    public static void rebalance(PriorityQueue<Integer> minheap,PriorityQueue<Integer> maxheap)
    {
        PriorityQueue<Integer> biggerheap=minheap.size()>maxheap.size()?minheap:maxheap;
        PriorityQueue<Integer> smallerheap=minheap.size()>maxheap.size()?maxheap:minheap;

        if(biggerheap.size()-smallerheap.size()>=2)
        {
            smallerheap.add(biggerheap.poll());
        }
    }
    
    public static double findmedian(PriorityQueue<Integer> minheap,PriorityQueue<Integer> maxheap)
    {
        PriorityQueue<Integer> biggerheap=minheap.size()>maxheap.size()?minheap:maxheap;
        PriorityQueue<Integer> smallerheap=minheap.size()>maxheap.size()?maxheap:minheap;
        if(biggerheap.size()==smallerheap.size())
            return (double)((biggerheap.peek()+smallerheap.peek())/2.0);
        else
            return biggerheap.peek();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        Arrays.fill(a,Integer.MAX_VALUE);
        for(int i=0; i < n; i++){
            a[i] = in.nextInt();
        }
        double[] meds=getMedians(a);
        for(double d:meds)
        {
            System.out.println(d);
        }
        
    }
}
