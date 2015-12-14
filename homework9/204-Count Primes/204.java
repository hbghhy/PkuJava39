public class Solution {
    public int countPrimes(int n) {
        if(n<=2)
        {
            return 0;
        }
        int[] isPrime=new int[n];
        int ans=0;
        for(int i=2;i<n;i++)
        {
            if(isPrime[i]==0)
            {
                ans++;
                if(n/i>=i)
                {
                     for(int j=i*i;j<n;j+=i)
                    {
                        isPrime[j]=1;
                    }
                }
            }
            
        }
        return ans;
    }
}