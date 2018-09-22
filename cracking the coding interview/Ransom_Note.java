import java.util.*;

public class Ransom_Note {
    Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;
    
    public Ransom_Note(String magazine, String note) {
        String[] m_words=magazine.split(" ");
        String[] n_words=note.split(" ");
        magazineMap=new HashMap<String,Integer>();
        noteMap=new HashMap<String,Integer>();
        for(String s:m_words)
        {
         if(!magazineMap.containsKey(s))
             magazineMap.put(s,1);
            else
            {
                int i=magazineMap.get(s);
                magazineMap.put(s,i+1);
            }
        }
        
        for(String s:n_words)
        {
         if(!noteMap.containsKey(s))
            noteMap.put(s,1);
            else
            {
                int i=noteMap.get(s);
                noteMap.put(s,i+1);
            }
        }
    }
    
    public boolean solve() {
        
        for(String s:noteMap.keySet())
        {
            if(!magazineMap.containsKey(s) || magazineMap.get(s)<noteMap.get(s))
                return false;
        }
        
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        
        // Eat whitespace to beginning of next line
        scanner.nextLine();
        
        Ransom_Note s = new Ransom_Note(scanner.nextLine(), scanner.nextLine());
        scanner.close();
        
        boolean answer = s.solve();
        if(answer)
            System.out.println("Yes");
        else System.out.println("No");
      
    }
}
