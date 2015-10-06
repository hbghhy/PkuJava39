public class Solution {
    public void sortColors(int[] nums) {
      /*  int iter=0;
        int i,j;
		 for( i=0;i<nums.length-1;i++)
		 {
			// for(int iter=0;iter<3;iter++)
			// {
			    if(nums[i]==iter)
				 	 continue;
				 for(j=nums.length-1;j>i;j--)
				 {
					 //if(nums[i]==iter)
					//	 break;
					
						 if(nums[j]==iter)
						 {
							// nums[i]=iter;
							 nums[j]=nums[i];	
							 nums[i]=iter;
							 break;
						 }
					
				 }
				 if(j==i)
				    i--;
				 iter=(iter+1)%3;
			*/	//理解错了题意，我是让0，1，2间隔排序排成0，1，2，0，1，2，1的样子
			
		int i = -1;
        int j = -1;
        int k = -1;
        int n=nums.length;
        for(int p = 0; p < n; p ++)
        {
            //根据第i个数字，挪动0~i-1串。
            if(nums[p] == 0)
            {
                nums[++k] = 2;    //2往后挪
                nums[++j] = 1;    //1往后挪
                nums[++i] = 0;    //0往后挪
            }
            else if(nums[p] == 1)
            {
                nums[++k] = 2;
                nums[++j] = 1;
            }
            else
                nums[++k] = 2;
        }
		 
    }
}