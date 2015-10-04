public class Solution {
    public boolean isHappy(int n) {
        if(n<=0) return false;
        long a = n;	
        Set set = new HashSet();
        
        while(a<=Integer.MAX_VALUE) {
        	if(set.contains(String.valueOf(a))){
        		return false;
        	}else{
        		set.add(String.valueOf(a));
        	}
        	a=digitSquare(a);
        	if(a==1){
        		return true;
        	}
        }
        return false;
    }
    
    public static long digitSquare(long a){
		long sum=0;
		while(a!=0){
			sum+=Math.pow(a%10, 2);
			a/=10;
		}
		return sum;
	}
}