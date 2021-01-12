import java.util.*;

public class LetterPhone {

    public static void main(String[] args) {
        LetterPhone sol = new LetterPhone();
        System.out.println("Solution: "+sol.letterCombinations("23"));
    }
    public ArrayList<String> letterCombinations(String digits) {
        Map<Character,List<Character>> map = new HashMap<Character,List<Character>>();
        map.put('0',new ArrayList<Character>(Arrays.asList('0')));
        map.put('1',new ArrayList<Character>(Arrays.asList('1')));
        map.put('2',new ArrayList<Character>(Arrays.asList('a','b','c')));
        map.put('3',new ArrayList<Character>(Arrays.asList('d','e','f')));
        map.put('4',new ArrayList<Character>(Arrays.asList('g','h','i')));
        map.put('5',new ArrayList<Character>(Arrays.asList('j','k','l')));
        map.put('6',new ArrayList<Character>(Arrays.asList('m','n','o')));
        map.put('7',new ArrayList<Character>(Arrays.asList('p','q','r','s')));
        map.put('8',new ArrayList<Character>(Arrays.asList('t','u','v')));
        map.put('9',new ArrayList<Character>(Arrays.asList('w','x','y','z')));
        ArrayList<String> result = new ArrayList<String>();
        letterCombinationsBT(result,digits,map,0,"");
        return result;
    }

    private void letterCombinationsBT(ArrayList<String> result, String digits, Map<Character, List<Character>> map,
            int index, String comb) {
        if(index == digits.length()) {
            result.add(comb);
            return;
        }
        char curr = digits.charAt(index);
        for(Character ch:map.get(curr)) {
            comb += ch;
            letterCombinationsBT(result,digits,map,index+1,comb);
            comb = comb.substring(0,comb.length()-1);
        }
    }
}