import java.util.*;

/* 
Input 1:
    A = "/home/"
Output 1:
    "/home"

Input 2:
    A = "/a/./b/../../c/"
Output 2:
    "/c"
 */

 /* NOtes:
 initialize stack
 split path via "/"
 push when a directory occurs
 pop when ".." occurs
 continue for empty string and "." (splitting "/home//foo/" gives empty string after "home")
 *** Important *** 
 popping and concatenating element from stack to result string will give reverse path.
 Therefore iterate thru stack it starts from beginning not last.
  Also using string builder it takes less time to concatenate
 */
public class SimplifyDirectoryPath {

    public static void main(String[] args) {
        SimplifyDirectoryPath sol = new SimplifyDirectoryPath();

        System.out.println("Solution: "+sol.simplifyPath("/home//foo/"));
        System.out.println("Solution: "+sol.simplifyPath("/a/./b/../../c/"));

    }

    public String simplifyPath(String path) {

        if (path.isEmpty() || path.equals("/")) return "/";
        Stack<String> stack = new Stack<String>();
        Stack<String> revStack = new Stack<String>();
        String[] directories = path.split("/");
        for(String directory:directories) {
            if(directory.equals(".") || directory.isEmpty()) {
                continue;
            }
            else if(directory.equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else {
                stack.push(directory);
            }
        }
        StringBuilder absolutePath = new StringBuilder();
        // String absolutePath = new String();
        for (String name : stack) { //starts from bottom
            // absolutePath+="/";
            // absolutePath+=name;
            // System.out.println(name);
            absolutePath.append("/");
            absolutePath.append(name);
        }
        if (absolutePath.length() == 0) absolutePath.append("/");
        return absolutePath.toString();
    }

    public String simplifyPathWithQueue(String path) {
        Deque<String> queue = new LinkedList<String>();
        String[] directories = path.split("/");
        // System.out.println(Arrays.toString(directories));
        for(String directory:directories) {
            System.out.println(directory);
            if(directory.equals(".") || directory.isEmpty()) {
                // System.out.println(". and empty");
                continue;
            }
            else if(directory.equals("..")) {
                // System.out.println("In .. case");
                if(!queue.isEmpty()) {
                    queue.removeLast();
                    // System.out.println("popped: "+stack.pop());
                }
            }
            else {
                queue.addLast(directory);
            }
        }
        String absolutePath = "/";
        while(!queue.isEmpty()) {
            String value = queue.removeFirst();
            // System.out.println("top: "+value);
            absolutePath+=value;
            if(!queue.isEmpty())
                absolutePath+="/";
        }
        return absolutePath;
    }
}
