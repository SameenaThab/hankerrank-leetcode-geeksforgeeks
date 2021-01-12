import java.util.*;

public class PalindromePartition {

    public static void main(String[] args) {
        PalindromePartition sol = new PalindromePartition();
        System.out.println("Solution: "+sol.partition("aaa"));
    }

    public ArrayList<ArrayList<String>> partition(String word) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> subResult = new ArrayList<String>();
        partitionHelper(result,subResult,word);
        return result;
    }

    private void partitionHelper(ArrayList<ArrayList<String>> result, ArrayList<String> subResult, String word) {
        if(word.length()==0) {
            result.add(new ArrayList<String>(subResult));
            return;
        }
        int i=0;
        for(int j=1;j<=word.length();j++){
            if(palindrome(word.substring(i,j))) {
                String curr = word.substring(i,j);
                subResult.add(curr);
                String remain = word.substring(j);
                partitionHelper(result,subResult,remain);
                subResult.remove(subResult.size()-1);
            }
        }
    }

	private boolean palindrome(String word) {
        if(word.length() == 1)
            return true;
        int i = 0;
        int j = word.length()-1;
        while(i <= j) {
            if(word.charAt(i) != word.charAt(j))
                return false;
            i++;
            j--;
        }
		return true;
	}

    
}