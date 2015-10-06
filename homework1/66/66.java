public class Solution {
    public int[] plusOne(int[] digits) {
        int[] ans=new int[digits.length+1];
		int flag=1;
		int sum=0;
//		ans[digits.length-1]=digits[digits.length-1]+1;
//		flag=digits[digits.length-1]/10;
//		int i=digits.length-2;
//		while(flag==1&&i>=0)
//		{
//			ans[i]=digits[i]+flag;
//			flag=ans[i]/10;
//		}
/*		for(int i=digits.length-1;i>=0;i--)
		{
			ans[i]=digits[i]+flag;
			flag=ans[i]/10;
		}*/
		for(int i=digits.length-1;i>=0;i--)
		{
			//ans[i+1]=digits[i]+flag;
			sum=digits[i]+flag;
			ans[i+1]=sum%10;
			flag=sum/10;
		}
		if(flag==1)
		{
			ans[0]=1;
			return ans;
		}
		else
			{
			int []ans2=new int[digits.length];
			for(int j=0;j<digits.length;j++)
			{
				ans2[j]=ans[j+1];
			}
			return ans2;  
			}
    }
}