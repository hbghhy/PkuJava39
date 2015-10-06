public class Solution {
    public int myAtoi(String str) {
        str=str.trim();
		String ans="";
		int ret;
		int flag;
		int index=0;
		double num;
		try{
		if(str=="")
			return 0;
		else if(str.charAt(index)=='+')
		{
			index++;
			flag=1;
		}
		else if(str.charAt(index)=='-')
		{
			index++;
			flag=0;
		}
		else if(str.charAt(0)>='0'&&str.charAt(0)<='9')
		{
			flag=1;
		}
		else
			return 0;
		while(index<str.length()&&str.charAt(index)>='0'&&str.charAt(index)<='9')
		{
			ans+=String.valueOf(str.charAt(index));
			index++;
		}
		num=Double.parseDouble(ans);
		if(flag==1)
		{
			if(num>=Integer.MAX_VALUE)
				return Integer.MAX_VALUE;
			else
			{
				ret=(int)num;
				return ret;
			}			
		}
		else
		{
			if(num*(-1)<=Integer.MIN_VALUE)
				return Integer.MIN_VALUE;
			else
			{
				ret=(int)num;
				return ret*(-1);
			}			
			
		}
		}
		catch(Exception e)
		{
		    return 0;
		}
        
    }
}