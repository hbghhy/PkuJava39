public class Solution {
    public boolean isPalindrome(String s) {
        int i,j;
         s=s.toLowerCase();
        /*i=0;
        j=s.length()-1;
        s=s.toLowerCase();
        while(i<=j)
        {
            if(s.charAt(i)>'a'&&s.charAt(i)<'z'&&s.charAt(j)>'a'&&s.charAt(j)<'z')
        	{
        		if(s.charAt(i)==s.charAt(j))
                {
                    i++;
                    j--;
                }
        		else
        			return false;
        	}
        	else 
        	{
        		if(s.charAt(i)<'a'||s.charAt(i)>'z')
        			i++;
        		if(s.charAt(j)<'a'||s.charAt(j)>'z')
        			j--;
        	} 
        }*/
        for(i=0,j=s.length()-1;i<j;)
        {
            if(s.charAt(i)<'0'||s.charAt(i)>'9'&&s.charAt(i)<'a'||s.charAt(i)>'z')
        		{i++;
        		continue;
        		}
        	if(s.charAt(j)<'0'||s.charAt(j)>'9'&&s.charAt(j)<'a'||s.charAt(j)>'z')
        	{
        	    j--;
        	    continue;
        	}
        		if(s.charAt(i)==s.charAt(j))
                {
                    i++;
                    j--;
                }
        		else
        			return false;
        }
        return true;  
    }
}