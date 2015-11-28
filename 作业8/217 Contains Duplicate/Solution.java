public class Solution {
    public boolean containsDuplicate(int[] nums) {
        boolean ret=false;
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<nums.length;i++)
		{
			if(!set.contains(new Integer(nums[i]))){
				set.add(new Integer(nums[i]));
			}else{
				return true;
			}
		}
        return ret;
    }
}