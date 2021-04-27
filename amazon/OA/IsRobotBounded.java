import java.util.Scanner;
//https://algo.monster/problems/amazon_oa_robot_in_circle
/* 
This problem is more about math proof than coding.

Consider the location of the robot after one iteration. There are two cases:

The robot is back to the origin. In this case, it's obvious that the robot will stay at the origin after any number of runs.
The robot is not at the origin.
For case 2, let's consider where the robot is facing after one iteration. The robot starts facing north, and after one iteration it could face:

North. Since it's facing the same direction after one iteration, it'll get further and further away from origin in the next iteration. And therefore its movement will not be bounded by a circle.
South. The robot reverses its direction. The distance it traveled in the current movement will be cancelled by the next movement since they are of opposite directions. Therefore, the robot goes back to the origin every two iterations.
East. If the robot ends up facing east after the iteration 1, it will be facing south after iteration 2, west after iteration 3 and north again after iteration 4. The distance it traveled in north direction cancels that of south, and distance it traveled in the east direction cancels that of west. So the robot is back to origin after 4 iterations.
West. It's the opposite situation as east but the result is the same - the robot goes back to the origin.
Therefore, we can simulate the robot movement after one iteration and return true if the robot's coordinate is back to origin or it faces a direction that is not north.
*/
class IsRobotBounded {
    public static boolean isRobotBounded(String movements) {
        int x=0;
        int y=0;
        int dirX=0;
        int dirY=1;
        for(char ch:movements.toCharArray()){
            if(ch == 'S') {
                x+=dirX;
                y+=dirY;
            } else if(ch == 'R') {
                int temp = dirX;
                dirX = dirY;
                dirY = -temp;
            } else {
                int temp = dirX;
                dirX = -dirY;
                dirY = temp; 
            }
        }
        // to check not facing north !(dirX == 0 && dirY == 1)
        return (x == 0 && y == 0) || !(dirX == 0 && dirY == 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String movements = scanner.nextLine();
        scanner.close();
        boolean res = isRobotBounded(movements);
        System.out.println(res);
    }
}
