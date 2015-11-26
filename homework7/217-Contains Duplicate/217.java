public class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet <Integer>hm=new  HashSet <Integer>();
        for(int i=0;i<nums.length;i++)
        {
            if(!hm.contains(nums[i]))
                hm.add(nums[i]);
            else
                return true;
        }
        return false;
        
    }
}