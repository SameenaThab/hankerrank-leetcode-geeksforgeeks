import java.util.*;
import java.lang.*;
import java.io.*;

//https://www.geeksforgeeks.org/find-paths-given-source-destination/

class ContinuousSeq {
	public static void main (String[] args) {
		int[] arr = {1, 3, 5, 23, 2};
		System.out.println(containsSeq(arr,7));
	}

	public static boolean containsSeq(int[] arr,int target)
	{
			return containsSeq(arr,target,0,0);
	}

	public static boolean containsSeq(int[] arr,int target,int sum,int index)
	{
		if(index>=arr.length)
			return false;
		if(sum+arr[index]==target)
			{
				System.out.println("sum:"+Integer.toString(sum)+"index:"+Integer.toString(index));
				return true;
			}
		else
			return containsSeq(arr,target,sum+arr[index],index+1)||containsSeq(arr,target,0,index+1);
	}

}