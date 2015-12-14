public class Solution {
    public int trailingZeroes(int n) {
        if(n<5)
            return 0;
        int num=0;
        while(n>0)
        {
            num+=n/5;
            n/=5;
        }
        return num;
        
    }
}