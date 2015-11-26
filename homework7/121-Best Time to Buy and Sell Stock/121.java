public class Solution {
    public int maxProfit(int[] prices) {
        int max=0;
        int low,dif;
        low=Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++)
        {
            if(prices[i]<low)
                low=prices[i];
            else
            {
                dif=prices[i]-low;
                if(max<dif)
                    max=dif;
            }
        }
        return max;
        
    }
}