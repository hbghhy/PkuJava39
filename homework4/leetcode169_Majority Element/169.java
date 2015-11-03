public class Solution {
    public int majorityElement(int[] nums) {
         int i,j,n,cnt;
		 n=nums.length;
		 if(nums.length==1)
			 return nums[0];
		 while(n>3)
		 {
		     cnt=0;
			 if(n%2==1)
			 {
				 for(i=0;i<n;i++)
				 {
					 if(nums[i]==nums[n-1])
						 cnt++; 
				 }
				 if(cnt>n/2)
					 return nums[n-1];
				 else 
					 n=n-1;
			 }
			 j=0;
			 for(i=0;i<n-1;i+=2)
			 {
				 if(nums[i]==nums[i+1])
				 {
					 nums[j]=nums[i];
					 j++;
				 }
			 }
			 n=j;
		 }
		 if(n==3)
		 {
			 if(nums[0]==nums[1])
				 return nums[0];
			 else
				 return nums[2];
		 }
		 else
			 return nums[0];
    }
}