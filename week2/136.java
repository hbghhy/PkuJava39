public class Solution {
    public int singleNumber(int[] nums) {
        int ans=0,i;
        for(i=0;i<nums.length;i++)
        {
            ans^=nums[i];
        }
        return ans;
    }
}