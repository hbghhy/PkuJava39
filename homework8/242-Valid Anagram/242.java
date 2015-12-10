import java.util.Hashtable;
public class Solution {
    public boolean isAnagram(String s, String t) {
        Hashtable<String,Integer> ht=new Hashtable <String,Integer>();
        if(s==null&&t==null)
    		return true;
    	if(s.length()!=t.length())
    		return false;
        int count=0;
        for(int i=0;i<s.length();i++)
        {
        	if(!ht.containsKey(String.valueOf(s.charAt(i))))
        	{
        		ht.put(String.valueOf(s.charAt(i)), 1);
        	}
        	else
        	{
        		count=ht.get(String.valueOf(s.charAt(i)));
        		ht.remove(s.charAt(i));
        		ht.put(String.valueOf(s.charAt(i)), count+1);
        	}   		
        }
        for(int i=0;i<t.length();i++)
        {
        	if(!ht.containsKey(String.valueOf(t.charAt(i))))
        	{
        		return false;
        	}
        	else
        	{
        		count=ht.get(String.valueOf(t.charAt(i)));
        		ht.remove(String.valueOf(t.charAt(i)));
        		ht.put(String.valueOf(t.charAt(i)), count-1);
        	}   		
        }
        for(int m:ht.values())
        {
        	if(m!=0)
        		return false;
        }
        return true;
        
    }
}