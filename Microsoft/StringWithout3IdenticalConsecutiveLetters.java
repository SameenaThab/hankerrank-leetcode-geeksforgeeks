//https://algo.monster/problems/string_without_3_identical_consecutive_letters
class StringWithout3IdenticalConsecutiveLetters {
    public static String filterString(String s) {
        int s_len = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        sb.append(s.charAt(1));
        for (int i = 2; i < s_len; ++i) {
            // if (s.charAt(i) == s.charAt(i - 1) && s.charAt(i) = s.charAt(i - 2)) {
            //     continue;
            // } else {
            //     sb.append(s.charAt(i));
            // }
            // above commented code can be writtena s below
            if (s.charAt(i) != s.charAt(i - 1) || s.charAt(i) != s.charAt(i - 2)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringWithout3IdenticalConsecutiveLetters sol = new StringWithout3IdenticalConsecutiveLetters();
        System.out.println("Solution for eedaaad: "+sol.filterString("eedaaad"));
        System.out.println("Solution for eedaaad: "+sol.filterString("xxxtxxx"));
        System.out.println("Solution for eedaaad: "+sol.filterString("uuuuxaaaaxum"));
    }
}
