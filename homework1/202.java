public class Solution {
    public int fun(int n)
    {
        int ans=0,k;
        while(n>0)
        {
            k=n%10;
            ans+=k*k;
            n/=10;
        }
        return ans;
    }
    public boolean isHappy(int n) {
        if(n==1) return true;
        if(n==4 || n==0) return false;
        return isHappy(fun(n));
    }
}