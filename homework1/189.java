public class Solution {
    public void reverse(int[] nums,int start,int end)
    {
        int i=start,j=end;
        while(i<j)
        {
            nums[i]^=nums[j];
            nums[j]^=nums[i];
            nums[i]^=nums[j];
            i++;j--;
        }
        return;
    }
    public void rotate(int[] nums, int k) {
        int l=nums.length;
        k=k%l;
        reverse(nums,0,l-k-1);
        reverse(nums,l-k,l-1);
        reverse(nums,0,l-1);
        return;
    }
}