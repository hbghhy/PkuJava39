public class Solution {
    public int removeDuplicates(int[] nums) {
        int curIndex=0;
        if(nums==null){
        	return 0;
        }
        if(nums.length==0){
        	return 0;
        }
        
        for(int i=1;i<nums.length;i++)
        {
        	if(nums[i-1]!=nums[i])
        	{
        		curIndex++;
        		nums[curIndex]=nums[i];
        	}
        }
        return curIndex+1;
    }
}