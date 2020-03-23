class IntegerToRoman {
    public String intToRoman(int num) {
        Map<Integer,String> map = new HashMap<Integer,String>();
        Integer[] arr = new Integer[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        //List<Integer> list = Arrays.asList(arr);
        map.put(1000,"M");
        map.put(900,"CM");
        map.put(500,"D");
        map.put(400,"CD");
        map.put(100,"C");
        map.put(90,"XC");
        map.put(50,"L");
        map.put(40,"XL");
        map.put(10,"X");
        map.put(9,"IX");
        map.put(5,"V");
        map.put(4,"IV");
        map.put(1,"I");
        String result ="";
        int min = 0;
        while(num > 0) {
            int i = min;
            while(arr[i]>num) {
                i++;
            }
            min = i;
            if(num == arr[i]) {
                result = result+map.get(arr[i]);

                num = 0;
            }
            else
            {
                //System.out.println("i:"+list.get(i));
                int rem = num-arr[i];
                //System.out.println("rem:"+rem);
                result=result+map.get(arr[i]);
                num=rem;
            }   
        }
        return result;
    }
}