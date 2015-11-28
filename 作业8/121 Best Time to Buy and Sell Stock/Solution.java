public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null) return 0;
        if(prices.length == 0) return 0;
        int low = prices[0];
        int ret = 0;
        for(int i=1; i<prices.length; i++){
          if(prices[i] < low) low = prices[i];
          else if(prices[i] - low > ret) ret = prices[i] - low;
        }
        return ret;
    }
}