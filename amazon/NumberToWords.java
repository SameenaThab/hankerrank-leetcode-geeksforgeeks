//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/481/
class NumberToWords {
    public String numberToWords(int num) {
        if(num == 0)
            return "Zero";
        String result = "";
        int billion = num/1000000000;
        int million = (num-billion*1000000000)/1000000;
        int thousand = (num-million*1000000-billion*1000000000)/1000;
        int hundred = (num-million*1000000-billion*1000000000-1000*thousand);
        //System.out.println(hundred);
        if(billion != 0)
            result = three(billion)+" Billion";
        if(million!=0) {
            if(!result.isEmpty())
                result = result+" ";  
            result = result+three(million)+" Million";
        }
        if(thousand!=0) {
            if(!result.isEmpty())
                result = result+" ";  
            result = result+three(thousand)+" Thousand";
        }
        if(hundred != 0) {
            if(!result.isEmpty())
                result = result+" ";  
            result = result+three(hundred); 
        }   
        return result;
    }
    
    public String one(int num) {
        switch(num) {          
            case 0: return "Zero";
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";   
            default: return "";
        }
    }
    
    public String lessThanTwenty(int num) {
        switch(num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";   
            default: return "";              
        }
    }
    
    public String ten(int num) {
        switch(num) {
        case 2: return "Twenty";
        case 3: return "Thirty";
        case 4: return "Forty";
        case 5: return "Fifty";
        case 6: return "Sixty";
        case 7: return "Seventy";
        case 8: return "Eighty";
        case 9: return "Ninety";     
        default: return "";           
        }
    }
    
    public String two(int num) {
        //System.out.println(num);
        if(num == 0)
            return "";
        else if(num<10)
            return one(num);
        else if(num<20)
            return lessThanTwenty(num);
        int ten = num/10;
        int res = num-ten*10;
        if(res!=0)
            return ten(ten)+" "+one(res);
        else
            return ten(ten);
    }
    
    public String three(int num) {
        if(num == 0)
            return "";
        int hundred = num/100;
        int res = num-hundred*100;
        if(hundred*res != 0)
            return one(hundred)+" Hundred "+two(res);
        else if(hundred == 0 && res != 0)
            return two(res);
        else if(hundred != 0 && res == 0)
            return one(hundred)+ " Hundred";
        else 
            return "";
    }
}