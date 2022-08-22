import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
we have n/2 layer
For every layer 
top ->moves-> right
right->moves->bottom
bottom->moves->left
left->moves->top
*/

public class Chap1Prob8 {

	static void matrixRotation(int[][] matrix)
	{
		if(matrix.length != matrix[0].length) {
			System.out.println("Not a Square matrix");
			return;
		}
		int n = matrix.length;
		for(int layer = 0;layer<n/2;layer++) {
			int first_index = layer;
			int last_index = n-1-layer;
			for(int i=first_index;i<
				last_index;i++) {
				int offset = i-first_index;
				int temp = matrix[first_index][i]; // Top of layer - row is lowest in the layer and const  but column increments m[0,0],m[0,1],m[0,2]....
				//top is replaced by left
				matrix[first_index][i] = matrix[last_index-offset][first_index]; //left of layer - column is lowest in the layer and const but row decrements from last_index to first
				//left is replaced by bottom
				matrix[last_index-offset][first_index] = matrix[last_index][last_index-offset]; //left of layer - row is largest in the layer and const but column decrements from last_index to first
				//bottom is replaced by right
				matrix[last_index][last_index-offset] = matrix[i][last_index]; //left of layer - column is largest in the layer and const but row increment from last_index to first
				//right is replaced by top
				matrix[i][last_index]=temp;
			}
		}
	}

    public static void main(String[] args)
    {
    	int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    	matrixRotation(matrix);
    	for(int[] row :matrix) {
	    	System.out.println(Arrays.toString(row));
    	}
	}
}