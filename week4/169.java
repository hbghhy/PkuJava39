public class Solution {
    public int majorityElement(int[] nums) {
        int count=1;
        int main=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]==main)
            {
                count++;
            }
            else
            {
                if(count==0)
                {
                    count++;
                    main=nums[i];
                }
                else
                {
                    count--;
                }
            }
        }
        return main;
    }
}