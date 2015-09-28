public class Solution {
    public int myAtoi(String str) {
        long ans=0;
        str=str.trim();
         int plus=1,l=str.length(),i=0,c0=(int)'0';
        //while(i<l && str.charAt(i)!='+' && str.charAt(i)!='-' && (str.charAt(i)<'0'|| str.charAt(i)>'9')) i++;
        if(i==l) return 0;
        if(str.charAt(i)=='-') {plus=-1;i++;}
        else if(str.charAt(i)=='+') i++;
        else if(str.charAt(i)<'0'|| str.charAt(i)>'9') return 0;
        if(plus==1)
        {
            while(i<l)
            {
                if(str.charAt(i)<'0'|| str.charAt(i)>'9') break;
                else 
                {           
                    ans*=10;
                    ans+=(int)(str.charAt(i))-c0;
                    if(ans>Integer.MAX_VALUE ) return Integer.MAX_VALUE;
                    i++;
                }
            }            
        }
        else if(plus==-1)
        {
            while(i<l)
            {
                if(str.charAt(i)<'0'|| str.charAt(i)>'9') break;
                else 
                {           
                    ans*=10;
                    ans-=(int)(str.charAt(i))-c0;
                    if(ans<Integer.MIN_VALUE ) return Integer.MIN_VALUE ;
                    i++;
                }
            }               
        }
        /*while(i<l)
        {
            if(str.charAt(i)<'0'|| str.charAt(i)>'9') break;
            else 
            {           
                ans*=10;
                ans+=(int)(str.charAt(i))-c0;
                if(ans>Integer.MAX_VALUE ) return Integer.MAX_VALUE;
                i++;
            }
        }*/
        //ans=ans*plus;
        //if(ans>Integer.MAX_VALUE ) return Integer.MAX_VALUE ;
        //if(ans<Integer.MIN_VALUE ) return Integer.MIN_VALUE ;
        return (int)ans;
        
    }
}