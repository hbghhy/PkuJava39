public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
         int i,j,p,q,sum;
		 List<List<Integer>> list=new ArrayList<List<Integer>>();
		 if(nums.length<4)
		    return list;
		 Arrays.sort(nums);
		 for(p=0;p<nums.length-3;p++)
		 {
		     if(p!=0&&nums[p-1]==nums[p])
		            continue;
		     for(q=p+1;q<nums.length-2;q++)
		     {
		         if(nums[q-1]==nums[q]&&q!=p+1)
		            continue;
		    	 i=q+1;
		    	 j=nums.length-1;
				 sum=target-nums[p]-nums[q];
				 while(i<j)
				 {
					 if(nums[i]+nums[j]<sum)
						 i++;
					 else if(nums[i]+nums[j]>sum)
						 j--;
					 else
					 {
						 List<Integer> ls=new ArrayList<Integer>();
						 ls.add(nums[p]);
						 ls.add(nums[q]);
						 ls.add(nums[i]);
						 ls.add(nums[j]);
						 list.add(ls);
						 i++;j--;
						 while(i<j&&nums[i]==nums[i-1])
						   i++;
						 while(i<j&&nums[j]==nums[j+1])
						   j--;
					 }
				 }
		     }
		     
		 }
		 return list; 
        
    }
}