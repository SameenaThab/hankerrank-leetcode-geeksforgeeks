//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/502/
class CompareVersion {
    public int compareVersion(String version1, String version2) {
        int n1 = version1.length();
        int n2 = version2.length();
        int i=0;
        int j=0;
        int sum1=0;
        int sum2=0;
        while(i<n1 || j <n2) {
            while(i<n1 && version1.charAt(i) != '.') {
                sum1 = sum1*10+(version1.charAt(i)-'0');
                i++;
            }
            while(j<n2 && version2.charAt(j) != '.') {
                sum2 = sum2*10+(version2.charAt(j)-'0');
                j++;
            }
            if(sum1>sum2)
                return 1;
            if(sum1<sum2)
                return -1;
            sum1=0;
            sum2=0;
             i++;
             j++;            
        }
        return 0;        
    }
}