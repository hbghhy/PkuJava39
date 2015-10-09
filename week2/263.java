public class Solution {
    public boolean isUgly(int num) {
        while(num>=5 && num%5==0)
        {
            num/=5;
        }
        while(num>=3 && num%3==0)
        {
            num/=3;
        }
        while(num>=2 && (num&1)==0)
        {
            num>>=1;
        }
        return (num==1);
    }
}