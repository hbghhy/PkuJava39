public class Solution {
    public int removeDuplicates(int[] nums) {
        int index=1;
        if(nums.length==0||nums.length==1)
            return nums.length;
        for(int i=1;i<nums.length;i++)
        {
            while(i<nums.length&&nums[i]==nums[i-1])
            {
                i++;
            }
            if(i==nums.length)
                break;
            else
            {
                nums[index]=nums[i];
                index++;
            }
        }
        return index;
        
    }
}