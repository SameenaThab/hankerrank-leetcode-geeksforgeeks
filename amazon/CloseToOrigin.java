class CloseToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points,new Comparator<int[]>(){
            @Override
            public int compare(int[] a1,int[] a2){
                return dist(a1)-dist(a2);
            }
        });
        
        return Arrays.copyOfRange(points, 0, K);
    }
    
    public int dist(int[] a) {
        return a[0]*a[0]+a[1]*a[1];
    }
}