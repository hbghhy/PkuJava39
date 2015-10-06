public class Solution {
    public String addBinary(String a, String b) {
        int i=a.length()-1;
		 int j=b.length()-1;
		 int cnum;
		 int flag;
		 String c="";
		 if(a==""||b=="")
		 {
			 if(a.length()>b.length())
				 return a;
			 else if(a.length()<b.length())
				 return b;
			 else
				 return null;
		 }
	
		// else if(a==null&&b!=null)
		 //if(a!=null&&b!=null)
		 else
		 {
			 cnum=(a.charAt(i)-'0'+b.charAt(j)-'0')%2;
			 c=String.valueOf(cnum);
			 flag=(a.charAt(i)-'0'+b.charAt(j)-'0')==2?1:0;	
			 while(i>0&&j>0)
			 {
				 i--;
				 j--;
				 cnum=(a.charAt(i)-'0'+b.charAt(j)-'0'+flag)%2;
				 c+=String.valueOf(cnum);
				// flag=(a.charAt(i)-'0'+b.charAt(j)-'0'+flag)==2?1:0;
				 flag=(a.charAt(i)-'0'+b.charAt(j)-'0'+flag)>=2?1:0;
			 }
			 while(i==0&&j>0)
			 {
				 j--;
				 cnum=(b.charAt(j)-'0'+flag)%2;
				 flag=(b.charAt(j)-'0'+flag)==2?1:0;
				 c+=String.valueOf(cnum);
			 }
			 while(i>0&&j==0)
			 {
				 i--;
				 cnum=(a.charAt(i)-'0'+flag)%2;
				 flag=(a.charAt(i)-'0'+flag)==2?1:0;
				 c+=String.valueOf(cnum);
			 }
			 if(flag==1)
			 {
				 c+=String.valueOf(flag);
			 }
			// c+=String.valueOf(flag);
			 StringBuffer sb = new StringBuffer(c.length());  
			 for(i=(c.length()-1);i>=0;i--)
			 {  
			      sb.append(c.charAt(i));  
			 }  
	        return sb.toString();
		 }	           
        
    }
}