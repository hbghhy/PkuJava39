public class Solution {
    public String convert(String s, int numRows) {
        int i,j;
        String str="";
        if(numRows==1)
            return s;
        int iter=2*numRows-2;
        for(i=0;i<numRows;i++)
        {
        	for(j=i;j<s.length();j+=iter)
        	{
        		if(i==0||i==numRows-1)
        		{
        			str+=s.charAt(j);
        		}
        		else
        		{
        			str+=s.charAt(j);
        			if(j+iter-2*i<s.length())
        				str+=s.charAt(j+iter-2*i);
        		}
        	}
        }
        return str;
    }
}