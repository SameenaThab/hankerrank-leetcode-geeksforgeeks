class WaterTanksMySolutionTimeHeavy {
    int tankHouse(String str) {
        int countH=0;
        int countBlanks=0;
        char[] arr = str.toCharArray();
        if(impossible(arr))
            return -1;
        return recurse(arr,0,0);
    }

    private int recurse(char[] arr, int i, int count) {
        // System.out.println(arr);
        if(filled(arr)){
            // System.out.println("here");
            return count;
        }
        if(i == arr.length)
            return Integer.MAX_VALUE;
        if(arr[i]=='-') {
            int notReplaced = recurse(arr, i+1, count);
            arr[i]='T';
            int replaced = recurse(arr,i+1,count+1);
            arr[i]='-';
            return Math.min(replaced,notReplaced);
        } else {
            return recurse(arr, i+1, count);
        }
    }

    private boolean filled(char[] arr) {
        for(int i=0;i<arr.length;i++) {
            if(arr[i]=='T' || arr[i] == '-')
                continue;
            else if(arr[i]=='H'&& ((i-1>=0 && arr[i-1]=='T') || (i+1<arr.length && arr[i+1]=='T'))) {
                continue;
            } else {
                return false;
            }     
        }
        return true;
    }

    private boolean impossible(char[] arr) {
        for(int i=0;i<arr.length;i++) {
            boolean con1 = arr[i]=='H'&& ((i-1>=0 && arr[i-1]=='H') && (i+1<arr.length && arr[i+1]=='H'));
            boolean con2 = arr[i]=='H'&& (i-1<0) && (i+1<arr.length && arr[i+1]=='H');
            boolean con3 = arr[i]=='H'&& ((i-1>=0 && arr[i-1]=='H') && (i+1>=arr.length));
            if(con1 || con2 ||con3)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TankHouse sol = new TankHouse();
        System.out.println(sol.tankHouse("H-H"));
        System.out.println(sol.tankHouse("H---H"));
        System.out.println(sol.tankHouse("HH-HH"));
        System.out.println(sol.tankHouse("-H-H-H-H-H-"));
        System.out.println(sol.tankHouse("-H-HH--"));
    }
    
}