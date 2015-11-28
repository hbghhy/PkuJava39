public class Solution {
    public int titleToNumber(String s) {
        int ret=0;
		int weight=1;
		if(s==null) return 0;
		if(s.trim().length()==0) return 0;
		
		char[] arr=s.toCharArray();
		for(int i=arr.length-1;i>=0;i--)
		{
			ret+=(arr[i]-'A'+1)*weight;
			weight*=26;
		}
		return ret;
    }
}