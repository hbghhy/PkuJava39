public class Solution {
     public int[] twoSum(int[] nums, int target) {
	       	List<int[]> al=Arrays.asList(nums);
	         int i,j;
	         int[]ans=new int[2];
	         for(i=0;i<nums.length;i++)
	         {
	             //Arrays.binarySearch(nums, target-nums[i])只能用于排序好的数组Arrays.sort(nums)
//	            result = Arrays.binarySearch(nums, target-nums[i]);
//	  			if(result>0)
//	  			{
//	  				for(j=i+1;j<n;j++)
//	         		{
//	  					if(nums[j]==target-nums[i])
//	  						{
//	  						ans[0]=i+1;
//	  						ans[1]=j+1;
//	  	  	 				System.out.println(j);
//	  	  	 				break;
//	  						}	
//	         		}		
//	  			}
	        	if(al.contains(target-nums[i]));
	        	{
	        		for(j=i+1;j<nums.length;j++)
		         		{
		  					if(nums[j]==target-nums[i])
		  						{
		  						ans[0]=i+1;
		  						ans[1]=j+1;
		  	  	 				System.out.println(j);
		  	  	 				break;
		  						}	
		         		}		
	        		
	        	}
	         
	         }
	         return ans;
	     }
    
}