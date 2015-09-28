public class Solution {
    public String reverseWords(String s) {
        StringBuilder ans=new StringBuilder();
        String[] h=s.split(" ");
        int l=h.length;
        boolean first=true;
        for(int i=l-1;i>=0;i--)
        {
            if(h[i].length()>0)
            {
                if(first)
                {
                    first=false;
                    ans.append(h[i]);
                }
                else
                {
                    ans.append(" ");
                    ans.append(h[i]);
                    
                }
                
            }
        }
        return ans.toString();
    }
}