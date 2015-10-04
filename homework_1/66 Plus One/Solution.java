public class Solution {
    public int[] plusOne(int[] digits) {
        int c=1;
        int v=0;
        int sum=0;
        for(int i=digits.length-1;i>=0;i--){
        	sum=digits[i]+c;
        	v=sum%10;
        	c=sum/10;
        	digits[i]=v;
        }
        if(c>0){
            int[] newDigits = new int[digits.length+1];
            newDigits[0]=c;
            for(int i=0;i<digits.length-1;i++){
                newDigits[i+1]=digits[i];
            }
            return newDigits;
        }else{
            return digits;
        }
    }
}