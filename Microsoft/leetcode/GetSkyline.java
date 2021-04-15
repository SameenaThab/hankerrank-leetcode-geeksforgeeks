import java.util.*;
/* 
Solution Template

The problem is a classical example of divide and conquer approach, and typically implemented exactly the same way as merge sort algorithm.
Let's follow here a solution template for divide and conquer problems :

Define the base case(s).
Split the problem into subproblems and solve them recursively.
Merge the subproblems solutions into the problem solution.

Algorithm
getSkyline for n buildings :
If n == 0 : return an empty list.
If n == 1 : return the skyline for one building (it's straightforward).
leftSkyline = getSkyline for the first n/2 buildings.
rightSkyline = getSkyline for the last n/2 buildings.
Merge leftSkyline and rightSkyline.
Now let's discuss each step in more details.

Base Cases
The first base case is an empty buildings list. Then the skyline is an empty list, too.
The second base case is the only one building in the list, when the skyline construction is quite straightforward.

How to split the problem
The idea is the same as for merge sort : 
at each step split the list exactly in two parts : 
from 0 to n/2 and from n/2 to n, and then construct the skylines recursively for each part.

How to merge two skylines
The algorithm for merge function is quite straightforward and based on the same merge sort logic : 
the height of an output skyline is always a maximum between the left and right skylines.

Let's use here two pointers pR and pL to track the current element index in both skylines, and three integers leftY, rightY, and currY to track the current height for the left skyline, right skyline and the merged skyline.
mergeSkylines (left, right) :
currY = leftY = rightY = 0
While we're in the region where both skylines are present (pR < nR and pL < nL) :
    Pick up the element with the smallest x coordinate. If it's an element from the left skyline, move pL and update leftY. If it's an element from the right skyline, move pR and update rightY.
    Compute the largest height at the current point : maxY = max(leftY, rightY).
    Update an output skyline by (x, maxY) point, if maxY is not equal to currY.
While there are still elements in the left skyline (pL < nL), process them following the same logic as above.
While there are still elements in the right skyline (pR < nR), process them following the same logic as above.
Return output skyline.


*/
class GetSkyline {
  /**
   *  Divide-and-conquer algorithm to solve skyline problem,
   *  which is similar with the merge sort algorithm.
   */
  public List<List<Integer>> getSkyline(int[][] buildings) {
    int n = buildings.length;
    List<List<Integer>> output = new ArrayList<List<Integer>>();

    // The base cases
    if (n == 0) return output;
    if (n == 1) {
      int xStart = buildings[0][0];
      int xEnd = buildings[0][1];
      int y = buildings[0][2];

      output.add(new ArrayList<Integer>() {{add(xStart); add(y); }});
      output.add(new ArrayList<Integer>() {{add(xEnd); add(0); }});
      return output;
    }

    // If there is more than one building,
    // recursively divide the input into two subproblems.
    List<List<Integer>> leftSkyline, rightSkyline;
    leftSkyline = getSkyline(Arrays.copyOfRange(buildings, 0, n / 2));
    rightSkyline = getSkyline(Arrays.copyOfRange(buildings, n / 2, n));

    // Merge the results of subproblem together.
    return mergeSkylines(leftSkyline, rightSkyline);
  }

  /**
   *  Merge two skylines together.
   */
  public List<List<Integer>> mergeSkylines(List<List<Integer>> left, List<List<Integer>> right) {
    int nL = left.size(), nR = right.size();
    int pL = 0, pR = 0;
    int currY = 0, leftY = 0, rightY = 0;
    int x, maxY;
    List<List<Integer>> output = new ArrayList<List<Integer>>();

    // while we're in the region where both skylines are present
    while ((pL < nL) && (pR < nR)) {
      List<Integer> pointL = left.get(pL);
      List<Integer> pointR = right.get(pR);
      // pick up the smallest x
      if (pointL.get(0) < pointR.get(0)) {
        x = pointL.get(0);
        leftY = pointL.get(1);
        pL++;
      }
      else {
        x = pointR.get(0);
        rightY = pointR.get(1);
        pR++;
      }
      // max height (i.e. y) between both skylines
      maxY = Math.max(leftY, rightY);
      // update output if there is a skyline change
      if (currY != maxY) {
        updateOutput(output, x, maxY);
        currY = maxY;
      }
    }

    // there is only left skyline
    appendSkyline(output, left, pL, nL, currY);

    // there is only right skyline
    appendSkyline(output, right, pR, nR, currY);

    return output;
  }

  /**
   * Update the final output with the new element.
   */
  public void updateOutput(List<List<Integer>> output, int x, int y) {
    // if skyline change is not vertical -
    // add the new point
    if (output.isEmpty() || output.get(output.size() - 1).get(0) != x)
      output.add(new ArrayList<Integer>() {{add(x); add(y); }});
      // if skyline change is vertical -
      // update the last point
    else {
      output.get(output.size() - 1).set(1, y);
    }
  }

  /**
   *  Append the rest of the skyline elements with indice (p, n)
   *  to the final output.
   */
  public void appendSkyline(List<List<Integer>> output, List<List<Integer>> skyline,
                            int p, int n, int currY) {
    while (p < n) {
      List<Integer> point = skyline.get(p);
      int x = point.get(0);
      int y = point.get(1);
      p++;

      // update output
      // if there is a skyline change
      if (currY != y) {
        updateOutput(output, x, y);
        currY = y;
      }
    }
  }
}