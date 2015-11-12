public class Solution {
    public String intToRoman(int num) {
        int []a=new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
		 String []s=new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		 StringBuilder sb=new StringBuilder("");
		 for(int i=0;i<a.length;i++)
		 {
			 while(num>=a[i])
			 {
				 num=num-a[i];
				 sb.append(s[i]);
			 }
		 }
		 return sb.toString();
    }
}