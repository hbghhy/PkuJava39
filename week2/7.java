public class Solution {
    public int reverse(int x) {
        long ans=0;
        int plus=(x>=0?1:-1),k;
        if(x<0) x=-x;
        while(x>0)
        {
            k=x%10;
            x/=10;
            ans=ans*10+k;
            if(plus>0)
            {
                if(ans>Integer.MAX_VALUE) return 0;
            }
            if(-ans<Integer.MIN_VALUE) return 0;
        }
        return plus*(int)ans;
    }
}