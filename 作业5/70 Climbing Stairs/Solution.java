public class Solution {
    public int climbStairs(int n) {
        if(n==1){
        	return 1;
        }else if(n==2){
        	return 2;
        }else{
        	int p=1,q=2;
            int sum=0;
            for(int i=1;i<=n-2;i++){
            	sum=p+q;
            	p=q;
            	q=sum;
            }
            return sum;
        }
    }
}