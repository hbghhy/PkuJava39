public class Solution {
    public int climbStairs(int n) {
        if(n<3) return n;
        int a=1,b=2,i;
        for(i=3;i<=n;i++)
        {
            b+=a;
            a=b-a;
        }
        return b;        
    }
}