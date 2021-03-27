/* given an array like [-2,3,5,-9], return 1,0 or -1 if the product of all elements in the array is positive, 0 or negative respectively. */
class ProductSign {
    int productSign(int[] arr) {
        int prod = 1;
        for(int ele:arr){
            if(ele == 0)
                return 0; 
            prod*=ele<0?-1:1;
        }
        return prod;
    }

    public static void main(String[] args) {
        ProductSign sol = new ProductSign();
        System.out.println(sol.productSign(new int[]{-1,3,5,-9}));
        System.out.println(sol.productSign(new int[]{-1,0,5,-9}));
        System.out.println(sol.productSign(new int[]{-1,-2,5,-9}));
    }
}