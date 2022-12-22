import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//chapter 1 Question 7

public class Chap1Prob7 {

	static boolean matrixRotation(int[][] mat)
	{
		int n=mat.length;
		if(n == 0 || mat.length != mat[0].length)
			return false;
		for(int layer = 0;layer < n/2;layer++)
		{
			int first = layer;
			int last = n-1-layer;
			for(int i=first;i<last;i++)
			{
				int offset = i-first;
				int top = mat[first][i];
				mat[first][i] = mat[last-offset][first];
				mat[last-offset][first] = mat[last][last-offset];
				mat[last][last-offset] = mat[i][last];
				mat[i][last] = top;
			}
		}
		return true;
	}

    public static void main(String[] args)
    {
    	int[][] mat = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    	System.out.print(matrixRotation(mat));
	}
}