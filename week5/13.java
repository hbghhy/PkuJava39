public class Solution {
    int[] val={1,5,10,50,100,500,1000};
    char[] Roman={'I','V','X','L','C','D','M'};
    public int singleRoman(char c)
    {
        for(int i=0;i<Roman.length;i++)
        {
            if(c==Roman[i])
                return val[i];
        }
        return 0;
    }
    public int romanToInt(String s) {
        int ans=0,t1=singleRoman(s.charAt(0)),t2;
        ans=t1;
        for(int i=1;i<s.length();i++)
        {
            t2=singleRoman(s.charAt(i));
            ans+=t2;
            if(t2==5*t1 || t2==10*t1)
            {
                ans-=(t1<<1);
            }
            t1=t2;
        }
        return ans;
    }
}