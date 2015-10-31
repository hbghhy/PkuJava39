public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int i,j,k,sum;
		 List<List<Integer>> list=new ArrayList<List<Integer>>();
		 if(nums.length<3)
		    return list;
		 Arrays.sort(nums);
		 for(k=0;nums[k]<=0&&k<nums.length-2;k++)
		 {
		     if(k!=0&&nums[k-1]==nums[k])
		            continue;
		     i=k+1;
		     j=nums.length-1;
			 sum=-nums[k];
			 while(i<j)
			 {
				 if(nums[i]+nums[j]<sum)
					 i++;
				 else if(nums[i]+nums[j]>sum)
					 j--;
				 else
				 {
					 List<Integer> ls=new ArrayList<Integer>();
					 ls.add(nums[k]);
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
		 return list; 
    }
}