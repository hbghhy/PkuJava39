public class Solution {
    public int reverse(int x) {
        String s="";
        int start;
        if(x<0)
        {
          s+="-"; //Math.abs(x) 若x<Integer.MIN_VALUE,则Math.abs(x),还是x,不能起作用
          start=1;
        }
        else
          start=0;
        String str=String.valueOf(x);
        int i=str.length()-1;
        while(i>=start)
        {
            //if(str.charAt(i)!='-')
             	s+=str.charAt(i);
        	i--;
        }
        double ans=Double.parseDouble(s);
        if(ans<Integer.MIN_VALUE||ans>Integer.MAX_VALUE)
        {
        	return 0;
        }
        int ret=Integer.parseInt(s);
        return ret;
    }
}