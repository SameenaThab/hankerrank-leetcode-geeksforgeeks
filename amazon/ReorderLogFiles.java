class ReorderLogFiles {
    //https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/2974/
    public String[] reorderLogFiles(String[] logs) {
     Arrays.sort(logs,new Comparator<String>() {
       @Override
         public int compare(String log1,String log2) {
             String[] split1 = log1.split(" ",2); //index 0 will be just identifier and index 2 will other part of log
            String[] split2 = log2.split(" ",2);
             boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
             boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
             if(!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]); // if rest of logs are same then compare identifier
             }            
             return isDigit1?isDigit2?0:+1:-1;// the digit is of least order. If both digits , then same order
         }
     });
        return logs;
    }
}