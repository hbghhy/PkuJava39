public class Solution {
    public boolean isPalindrome(int x) {
        int i,j,iter;
    	   int high,low;
    	   if(x<0)
    		   return false;
    	   for(i=1,j=1;x/i>=10;i*=10,j++);
    	   j=j/2;
    	   iter=1;
    	   while(iter<=j)
    	   {
    		   high=x/i;
    		   low=x%10;
    		   if(high==low)
    		   {
    			   x=x%i/10;
    			   i=i/100;
    			   iter++;
    		   }
    		   else
    			   return false;
    	   }
    	   return true;
    }
}